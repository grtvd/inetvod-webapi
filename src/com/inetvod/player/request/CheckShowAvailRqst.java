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
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.ShowCost;
import com.inetvod.common.data.ShowFormat;
import com.inetvod.common.data.ShowID;
import com.inetvod.common.data.ProviderConnectionType;
import com.inetvod.common.data.ShowCostType;
import com.inetvod.common.dbdata.ShowProvider;
import com.inetvod.common.dbdata.ProviderConnection;
import com.inetvod.common.dbdata.ShowProviderList;
import com.inetvod.common.dbdata.PlayerManager;
import com.inetvod.common.dbdata.Player;
import com.inetvod.player.rqdata.StatusCode;
import com.inetvod.providerClient.ProviderRequestor;
import com.inetvod.providerClient.rqdata.ProviderStatusCode;

public class CheckShowAvailRqst extends SessionRequestable
{
	/* Fields */
	private ShowID fShowID;
	private ProviderID fProviderID;
	private ShowCost fShowCost;

	/* Constuction Methods */
	public CheckShowAvailRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		ShowCost updatedShowCost = fShowCost;

		// Fetch Show as offered by Provider
		Player player = PlayerManager.getThe().getPlayer(fMemberSession.getPlayerID());
		ShowProvider showProvider = ShowProviderList.findByShowIDProviderIDAvailable(fShowID, fProviderID)
			.findItemsByShowCost(fShowCost).findFirstByPlayerMimeType(player);
		if(showProvider == null)
		{
			fStatusCode = StatusCode.sc_GeneralError;
			return null;
		}
		ProviderConnection providerConnection = ProviderConnection.get(showProvider.getProviderConnectionID());

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

			//TODO: determine correct format for player
			ShowFormat showFormat = new ShowFormat(null, MediaEncoding.WMV9, MediaContainer.ASF, (short)600,
				(short)480, (short)30, (short)750);

			// Send request to Provider
			updatedShowCost = providerRequestor.checkShowAvail(showProvider.getProviderShowID(), showFormat,
				fShowCost);

			ProviderStatusCode providerStatusCode = providerRequestor.getStatusCode();
			if(!ProviderStatusCode.sc_Success.equals(providerStatusCode)
				|| (updatedShowCost == null))
			{
				if(ProviderStatusCode.sc_ShowNoAccess.equals(providerStatusCode))
					fStatusCode = StatusCode.sc_ShowNoAccess;
				else if(ProviderStatusCode.sc_ShowLevelInsufficient.equals(providerStatusCode))
					fStatusCode = StatusCode.sc_ShowLevelInsufficient;
				else if(ProviderStatusCode.sc_InvalidMemberUserID.equals(providerStatusCode))
					fStatusCode = StatusCode.sc_InvalidProviderUserIDPassword;
				else
					fStatusCode = StatusCode.sc_NoProviderResponse;
				return null;
			}
		}
		else
		{
			// Sanity check, Show cost should be free
			if((updatedShowCost == null) || !ShowCostType.Free.equals(updatedShowCost.getShowCostType()))
			{
				fStatusCode = StatusCode.sc_GeneralError;
				return null;
			}
		}

		CheckShowAvailResp response = CheckShowAvailResp.newInstance(updatedShowCost);
		fStatusCode = StatusCode.sc_Success;

		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fShowID = reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
		fShowCost = reader.readObject("ShowCost", ShowCost.CtorDataReader);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeObject("ShowCost", fShowCost);
	}
}
