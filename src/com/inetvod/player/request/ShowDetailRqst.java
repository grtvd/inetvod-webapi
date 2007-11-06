/**
 * Copyright © 2004-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ShowID;
import com.inetvod.common.dbdata.Player;
import com.inetvod.common.dbdata.PlayerManager;
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

	/* Implementation */
	protected boolean areGuestsAllowedForRequest() { return true; }

	public Writeable fulfillRequest() throws Exception
	{
		ShowDetailResp response;
		Player player = PlayerManager.getThe().getPlayer(fMemberSession.getPlayerID());
		Show show;
		ShowProviderList showProviderList;
		ShowCategoryList showCategoryList;

		response = new ShowDetailResp();
		show = Show.get(fShowID);
		showProviderList = new ShowProviderList(com.inetvod.common.dbdata.ShowProviderList.findByShowIDAvailable(fShowID)
			.findItemsByPlayer(player));
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
