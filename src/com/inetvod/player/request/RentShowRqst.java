/**
 * Copyright © 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import java.util.Date;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Logger;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.CreditCard;
import com.inetvod.common.data.LicenseMethod;
import com.inetvod.common.data.ProviderConnectionType;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.ShowCost;
import com.inetvod.common.data.ShowCostType;
import com.inetvod.common.data.ShowID;
import com.inetvod.common.dbdata.MemberAccount;
import com.inetvod.common.dbdata.MemberProvider;
import com.inetvod.common.dbdata.Player;
import com.inetvod.common.dbdata.PlayerManager;
import com.inetvod.common.dbdata.ProviderConnection;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.RentedShowHistory;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowProvider;
import com.inetvod.common.dbdata.ShowProviderList;
import com.inetvod.player.rqdata.License;
import com.inetvod.player.rqdata.StatusCode;
import com.inetvod.providerClient.ProviderRequestor;
import com.inetvod.providerClient.rqdata.Payment;
import com.inetvod.providerClient.rqdata.PaymentType;
import com.inetvod.providerClient.rqdata.ProviderStatusCode;

public class RentShowRqst extends SessionRequestable
{
	/* Fields */
	private ShowID fShowID;
	private ProviderID fProviderID;
	private ShowCost fApprovedCost;

	/* Constuction Methods */
	public RentShowRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		String showURL;
		License license;
		Date rentedOn = new Date();
		Date availableUntil = null;

		// Fetch Show as offered by Provider
		Player player = PlayerManager.getThe().getPlayer(fMemberSession.getPlayerID());
		ShowProviderList showProviderList = ShowProviderList.findByShowIDProviderIDAvailable(fShowID, fProviderID)
			.findItemsByShowCost(fApprovedCost);
		// Fetch first ShowProvider supported by Player
		ShowProvider showProvider = showProviderList.findFirstByPlayer(player);
		if(showProvider == null)
		{
			fStatusCode = StatusCode.sc_GeneralError;
			return null;
		}
		ProviderConnection providerConnection = ProviderConnection.get(showProvider.getProviderConnectionID());

		// Is a ProviderAPI connection?
		if(ProviderConnectionType.ProviderAPI.equals(providerConnection.getProviderConnectionType()))
		{
			MemberProvider memberProvider = MemberProvider.findByMemberIDProviderID(fMemberID, fProviderID);
			ProviderRequestor providerRequestor = ProviderRequestor.newInstance(providerConnection, memberProvider);

			// Determine payment
			Payment payment = null;
			PaymentType paymentType = PaymentType.ChargeAccount;
			boolean ccOnFile = false;

			if(ShowCostType.PayPerView.equals(fApprovedCost.getShowCostType()))
			{
				// Does Member have credit card on file
				MemberAccount memberAccount = MemberAccount.find(fMemberID);
				if((memberAccount != null) && (memberAccount.getCreditCard() != null))
				{
					CreditCard creditCard = memberAccount.getCreditCard().clone();
					creditCard.setStoreEncrypted(false);
					payment = Payment.newInstance(PaymentType.CreditCard, creditCard);
					paymentType = PaymentType.CreditCard;
					ccOnFile = true;
				}
				else
					payment = Payment.newInstance(PaymentType.ChargeAccount, null);
			}

			// Confirm Provider's server can be communicated with
			if(!providerRequestor.pingServer())
			{
				fStatusCode = StatusCode.sc_NoProviderResponse;
				return null;
			}

			// Send request to Provider
			com.inetvod.providerClient.request.RentShowResp providerRentShowResp = providerRequestor.rentShow(
				showProvider.getProviderShowID(), fPlayerIPAddress, showProvider.getShowFormat(), fApprovedCost,
				payment);

			ProviderStatusCode providerStatusCode = providerRequestor.getStatusCode();
			if(!ProviderStatusCode.sc_Success.equals(providerStatusCode) || (providerRentShowResp == null))
			{
				if(PaymentType.ChargeAccount.equals(paymentType))
				{
					if(ProviderStatusCode.sc_ShowNoAccess.equals(providerStatusCode))
						fStatusCode = StatusCode.sc_ShowNoAccess;
					else if(ProviderStatusCode.sc_ShowLevelInsufficient.equals(providerStatusCode))
						fStatusCode = StatusCode.sc_ShowLevelInsufficient;
					else if(ProviderStatusCode.sc_ShowPaymentDenied.equals(providerStatusCode))
					{
						if(ccOnFile)
							fStatusCode = StatusCode.sc_ShowPaymentDenied;
						else
							fStatusCode = StatusCode.sc_CreditCardNotOnFile;
					}
					else
						fStatusCode = StatusCode.sc_UnknownProviderResponse;
				}
				else
				{
					if(ProviderStatusCode.sc_ShowPaymentDenied.equals(providerStatusCode))
					{
						if(ccOnFile)
							fStatusCode = StatusCode.sc_CreditCardDenied;
						else
							fStatusCode = StatusCode.sc_CreditCardNotOnFile;
					}
					else
						fStatusCode = StatusCode.sc_UnknownProviderResponse;
				}

				return null;
			}

			showURL = providerRentShowResp.getLicense().getShowURL();

			license = new License();
			license.setShowURL(showURL);
			if(LicenseMethod.LicenseServer.equals(providerRentShowResp.getLicense().getLicenseMethod()))
			{
				license.setLicenseMethod(LicenseMethod.LicenseServer);
				license.setLicenseURL(providerRentShowResp.getLicense().getLicenseURL());
				license.setContentID(showProvider.getProviderShowID().toString());
				license.setUserID(memberProvider.getUserID());
				license.setPassword(memberProvider.getPassword());
			}
			else
			{
				license.setLicenseMethod(LicenseMethod.URLOnly);
			}

			availableUntil = providerRentShowResp.getAvailableUntil();
		}
		else
		{
			// Sanity check, Show cost should be free
			if((fApprovedCost == null) || !ShowCostType.Free.equals(fApprovedCost.getShowCostType()))
			{
				fStatusCode = StatusCode.sc_GeneralError;
				return null;
			}

			showURL = showProvider.getShowURL();
			license = new License();
			license.setLicenseMethod(LicenseMethod.URLOnly);
			license.setShowURL(showURL);
		}

		// Save the RentedShow record
		RentedShow rentedShow = RentedShow.newInstance(fMemberID, fShowID, fProviderID, showProvider.getShowProviderID());

		rentedShow.setShowURL(showURL);
		//TODO: save LicenseMethod, LicenseServer

		rentedShow.setShowCost(fApprovedCost);
		rentedShow.setRentedOn(rentedOn);
		rentedShow.setAvailableUntil(availableUntil);
		rentedShow.update();

		// Save to rental history
		try
		{
			Show show = Show.get(fShowID);
			RentedShowHistory rentedShowHistory = RentedShowHistory.newInstance(getMember(), show, showProvider, rentedShow,
				player, fPlayerIPAddress);
			rentedShowHistory.update();
		}
		catch(Exception e)
		{
			// Don't fail rental if history save fails
			Logger.logErr(this, "fulfillRequest", String.format("Unable to save RentedShowHistory, MemberID(%s), ShowID(%s), ProviderID(%s)",
				fMemberID, fShowID, fProviderID), e);
		}

		// Return response to player
		RentShowResp response = new RentShowResp();
		response.setRentedShowID(rentedShow.getRentedShowID());
		response.setLicense(license);
		fStatusCode = StatusCode.sc_Success;

		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fShowID = reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
		fApprovedCost = reader.readObject("ApprovedCost", ShowCost.CtorDataReader);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeObject("ApprovedCost", fApprovedCost);
	}
}
