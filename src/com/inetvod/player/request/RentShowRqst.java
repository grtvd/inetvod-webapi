/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import java.util.Date;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.MediaContainer;
import com.inetvod.common.data.MediaEncoding;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.ShowCost;
import com.inetvod.common.data.ShowCostType;
import com.inetvod.common.data.ShowFormat;
import com.inetvod.common.data.ShowID;
import com.inetvod.common.data.CreditCard;
import com.inetvod.common.data.Address;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.ShowProvider;
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
		ProviderRequestor providerRequestor = ProviderRequestor.newInstance(fProviderID, fMemberID);

		// Determine payment
		Payment payment = null;
		PaymentType paymentType = PaymentType.ChargeAccount;
		boolean ccOnFile = false;

		if(ShowCostType.PayPerView.equals(fApprovedCost.getShowCostType()))
		{
			//TODO: does Member have credit card on file
//			CreditCard creditCard = MemberCreditCard.get(fMemberID).getCreditCard();
			if(true)
			{
				//TODO: remove temp code
				CreditCard creditCard = new CreditCard();
				creditCard.setNameOnCC("Joe Buck");
				creditCard.setCCType("Visa");
				creditCard.setCCNumber("1111-2222-3333-4444");
				creditCard.setCCSIC("123");
				creditCard.setExpireDate("05-99");
				Address address = new Address();
				address.setAddrStreet1("123 Main St.");
				address.setCity("Anytown");
				address.setState("AT");
				address.setPostalCode("11111");
				creditCard.setBillingAddress(address);
				//TODO: remove temp code

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

		Date rentedOn = new Date();

		// Fetch Show as offered by Provider
		ShowProvider showProvider = ShowProvider.getByShowIDProviderID(fShowID, fProviderID);

		//TODO: determine correct format for player
		ShowFormat showFormat = new ShowFormat();
		showFormat.setMediaEncoding(MediaEncoding.WMV9);
		showFormat.setMediaContainer(MediaContainer.ASF);
		showFormat.setHorzResolution((short)600);
		showFormat.setVertResolution((short)480);
		showFormat.setFramesPerSecond((short)30);
		showFormat.setBitRate((short)750);

		// Send request to Provider
		com.inetvod.providerClient.request.RentShowResp providerRentShowResp = providerRequestor.rentShow(
			showProvider.getProviderShowID(), "127.0.0.1", showFormat, fApprovedCost, payment);

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

		// Save the RentedShow record
		RentedShow rentedShow = RentedShow.newInstance(fMemberID, fShowID, fProviderID);

		rentedShow.setShowURL(providerRentShowResp.getLicense().getShowURL());
		//rentedShow.setShowURL("http://api.inetvod.com/mce/videos/TestVideo.wmv");
		//TODO: save LicenseMethod, LicenseServer

		rentedShow.setShowCost(fApprovedCost);
		rentedShow.setRentedOn(rentedOn);
		rentedShow.setAvailableUntil(providerRentShowResp.getAvailableUntil());
		rentedShow.update();

		// Return response to player
		RentShowResp response = new RentShowResp();
		response.setRentedShowID(rentedShow.getRentedShowID());
		response.setLicense(providerRentShowResp.getLicense());
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
