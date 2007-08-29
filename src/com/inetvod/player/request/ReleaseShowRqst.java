/**
 * Copyright © 2004-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.MediaContainer;
import com.inetvod.common.data.MediaEncoding;
import com.inetvod.common.data.ProviderConnectionType;
import com.inetvod.common.data.RentedShowID;
import com.inetvod.common.data.ShowFormat;
import com.inetvod.common.data.ShowFormatID;
import com.inetvod.common.dbdata.Player;
import com.inetvod.common.dbdata.PlayerManager;
import com.inetvod.common.dbdata.ProviderConnection;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.ShowProvider;
import com.inetvod.common.dbdata.ShowProviderList;
import com.inetvod.player.rqdata.StatusCode;
import com.inetvod.providerClient.ProviderRequestor;
import com.inetvod.providerClient.rqdata.ProviderStatusCode;

public class ReleaseShowRqst extends SessionRequestable
{
	/* Fields */
	protected RentedShowID fRentedShowID;

	/* Constuction Methods */
	public ReleaseShowRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		// Get the rented show
		RentedShow rentedShow = RentedShow.get(fRentedShowID);

		// Get the provider connectin
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
			boolean success = providerRequestor.releaseShow(showProvider.getProviderShowID(), showFormat);

			ProviderStatusCode providerStatusCode = providerRequestor.getStatusCode();
			if(!ProviderStatusCode.sc_Success.equals(providerStatusCode) || !success)
			{
				fStatusCode = StatusCode.sc_UnknownProviderResponse;
				return null;
			}
		}

		// Delete rented show
		rentedShow.delete();

		// Return response to player
		ReleaseShowResp response = new ReleaseShowResp();

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
