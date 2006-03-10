/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.CompUtil;
import com.inetvod.common.data.RentedShowID;
import com.inetvod.common.data.ProviderConnectionType;
import com.inetvod.common.data.License;
import com.inetvod.common.data.LicenseMethod;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.ShowProvider;
import com.inetvod.common.dbdata.ProviderConnection;
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
			ProviderRequestor providerRequestor = ProviderRequestor.newInstance(providerConnection, fMemberID);

			// Confirm Provider's server can be communicated with
			if(!providerRequestor.pingServer())
			{
				fStatusCode = StatusCode.sc_NoProviderResponse;
				return null;
			}

			// Fetch Show as offered by Provider
			ShowProvider showProvider = ShowProvider.getByShowIDProviderID(rentedShow.getShowID(), rentedShow.getProviderID());

			// Send request to Provider
			com.inetvod.providerClient.request.WatchShowResp providerWatchShowResp = providerRequestor.watchShow(
				showProvider.getProviderShowID(), "127.0.0.1");

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

			license = providerWatchShowResp.getLicense();
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
