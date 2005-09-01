/**
 * Copyright � 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.dbdata.ShowID;
import com.inetvod.common.dbdata.ProviderID;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowProvider;
import com.inetvod.common.dbdata.ShowCategoryList;
import com.inetvod.player.rqdata.ShowDetail;

public class ShowDetailRqst extends SessionRequestable
{
	/* Fields */
	protected ShowID fShowID;
	protected ProviderID fProviderID;

	/* Constuction Methods */
	public ShowDetailRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		ShowDetailResp response;
		Show show;
		ShowProvider showProvider;
		ShowCategoryList showCategoryList;

		response = new ShowDetailResp();
		show = Show.get(fShowID);
		showProvider = ShowProvider.getByShowIDProviderID(fShowID, fProviderID);
		showCategoryList = ShowCategoryList.findByShowID(fShowID);
		response.ShowDetail = new ShowDetail(show, showProvider, showCategoryList);

		fStatusCode = StatusCode.sc_Success;
		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fShowID = (ShowID)reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fProviderID = (ProviderID)reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
	}
}