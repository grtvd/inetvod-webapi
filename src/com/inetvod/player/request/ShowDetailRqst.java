/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ShowID;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowCategoryList;
import com.inetvod.player.rqdata.ShowDetail;
import com.inetvod.player.rqdata.ShowProviderList;
import com.inetvod.player.rqdata.StatusCode;

public class ShowDetailRqst extends SessionRequestable
{
	/* Fields */
	protected ShowID fShowID;

	/* Constuction Methods */
	public ShowDetailRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		ShowDetailResp response;
		Show show;
		ShowProviderList showProviderList;
		ShowCategoryList showCategoryList;

		response = new ShowDetailResp();
		show = Show.get(fShowID);
		showProviderList = new ShowProviderList(com.inetvod.common.dbdata.ShowProviderList.findByShowID(fShowID));
		showCategoryList = ShowCategoryList.findByShowID(fShowID);
		response.ShowDetail = new ShowDetail(show, showProviderList, showCategoryList);

		fStatusCode = StatusCode.sc_Success;
		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fShowID = reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
	}
}
