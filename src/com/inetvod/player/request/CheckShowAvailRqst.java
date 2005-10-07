/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.ProviderID;
import com.inetvod.common.dbdata.ShowCostList;
import com.inetvod.common.dbdata.ShowID;
import com.inetvod.common.dbdata.ShowProvider;
import com.inetvod.player.rqdata.StatusCode;

public class CheckShowAvailRqst extends SessionRequestable
{
	/* Fields */
	protected ShowID fShowID;
	protected ProviderID fProviderID;

	/* Constuction Methods */
	public CheckShowAvailRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		CheckShowAvailResp response;
		ShowProvider showProvider;

		response = new CheckShowAvailResp();

		//TODO: fetch this from Provider API
		showProvider = ShowProvider.getByShowIDProviderID(fShowID, fProviderID);
		ShowCostList showCostList = new ShowCostList();	//TODO: get list from provider
		showCostList.add(showProvider.getShowCost());
		response.ShowCostList = showCostList;

		fStatusCode = StatusCode.sc_Success;
		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fShowID = reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
	}
}
