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
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.ShowProvider;
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
		// Get the rented show
		RentedShow rentedShow = RentedShow.get(fRentedShowID);

		ProviderRequestor providerRequestor = ProviderRequestor.newInstance(rentedShow.getProviderID(), fMemberID);

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

		// Return response to player
		WatchShowResp response = new WatchShowResp();
		response.setLicense(providerWatchShowResp.getLicense());

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
