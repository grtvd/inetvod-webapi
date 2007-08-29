/**
 * Copyright © 2004-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.CompUtil;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.LicenseMethod;
import com.inetvod.common.data.MediaContainer;
import com.inetvod.common.data.MediaEncoding;
import com.inetvod.common.data.ProviderConnectionType;
import com.inetvod.common.data.RentedShowID;
import com.inetvod.common.data.ShowFormat;
import com.inetvod.common.data.ShowFormatID;
import com.inetvod.common.dbdata.MemberProvider;
import com.inetvod.common.dbdata.Player;
import com.inetvod.common.dbdata.PlayerManager;
import com.inetvod.common.dbdata.ProviderConnection;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.ShowProvider;
import com.inetvod.common.dbdata.ShowProviderList;
import com.inetvod.player.rqdata.License;
import com.inetvod.player.rqdata.StatusCode;
import com.inetvod.providerClient.ProviderRequestor;
import com.inetvod.providerClient.rqdata.ProviderStatusCode;

public class WatchShowRqst extends SessionRequestable
{
	/* Fields */
	protected RentedShowID fRentedShowID;

	/* Constuction Methods */
	public WatchShowRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		License license;

		// Get the rented show
		RentedShow rentedShow = RentedShow.get(fRentedShowID);
		ProviderConnection providerConnection = ProviderConnection.get(rentedShow.getProviderConnectionID());

		// Is a ProviderAPI connection?
		if(ProviderConnectionType.ProviderAPI.equals(providerConnection.getProviderConnectionType()))
		{
			MemberProvider memberProvider = MemberProvider.findByMemberIDProviderID(fMemberID, rentedShow.getProviderID());
			ProviderRequestor providerRequestor = ProviderRequestor.newInstance(providerConnection, memberProvider);

			// Confirm Provider's server can be communicated with
			if(!providerRequestor.pingServer())
			{
				fStatusCode = StatusCode.sc_NoProviderResponse;
				return null;
			}

			// Fetch Show as offered by Provider
			Player player = PlayerManager.getThe().getPlayer(fMemberSession.getPlayerID());
			//TODO: Get specific ShowProvider by using RentedShow.getShowFormat
			ShowProvider showProvider = ShowProviderList.findByShowIDProviderID(rentedShow.getShowID(),
				rentedShow.getProviderID()).findFirstByPlayerMimeType(player);
			if(showProvider == null)
			{
				fStatusCode = StatusCode.sc_GeneralError;
				return null;
			}

			//TODO: determine correct format for player
			ShowFormat showFormat = new ShowFormat(new ShowFormatID("0f7db069-c104-40d9-8df7-b5042ab17082"), MediaEncoding.WMV2, MediaContainer.ASF, (short)600,
				(short)480, (short)30, (short)750);

			// Send request to Provider
			com.inetvod.providerClient.request.WatchShowResp providerWatchShowResp = providerRequestor.watchShow(
				showProvider.getProviderShowID(), fPlayerIPAddress, showFormat);

			ProviderStatusCode providerStatusCode = providerRequestor.getStatusCode();
			if(!ProviderStatusCode.sc_Success.equals(providerStatusCode) || (providerWatchShowResp == null))
			{
				if(ProviderStatusCode.sc_ShowRentExpired.equals(providerStatusCode))
					fStatusCode = StatusCode.sc_ShowRentExpired;
				else
					fStatusCode = StatusCode.sc_UnknownProviderResponse;

				return null;
			}

			// Update rented show
			if(!CompUtil.areEqual(rentedShow.getAvailableUntil(), providerWatchShowResp.getAvailableUntil()))
			{
				rentedShow.setAvailableUntil(providerWatchShowResp.getAvailableUntil());
				rentedShow.update();
			}

			license = new License();
			license.setShowURL(providerWatchShowResp.getLicense().getShowURL());
			if(LicenseMethod.LicenseServer.equals(providerWatchShowResp.getLicense().getLicenseMethod()))
			{
				license.setLicenseMethod(LicenseMethod.LicenseServer);
				license.setLicenseURL(providerWatchShowResp.getLicense().getLicenseURL());
				license.setContentID(showProvider.getProviderShowID().toString());
				license.setUserID(memberProvider.getUserID());
				license.setPassword(memberProvider.getPassword());
			}
			else
			{
				license.setLicenseMethod(LicenseMethod.URLOnly);
			}
		}
		else
		{
			license = new License();
			license.setLicenseMethod(LicenseMethod.URLOnly);
			license.setShowURL(rentedShow.getShowURL());
		}

		// Return response to player
		WatchShowResp response = new WatchShowResp();
		response.setLicense(license);

		fStatusCode = StatusCode.sc_Success;
		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fRentedShowID = reader.readDataID("RentedShowID", RentedShowID.MaxLength, RentedShowID.CtorString);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RentedShowID", fRentedShowID, RentedShowID.MaxLength);
	}
}
