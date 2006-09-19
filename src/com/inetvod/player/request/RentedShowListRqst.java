/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.Player;
import com.inetvod.common.dbdata.PlayerManager;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.RentedShowList;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowList;
import com.inetvod.common.dbdata.ShowProviderList;
import com.inetvod.player.rqdata.RentedShowSearch;
import com.inetvod.player.rqdata.StatusCode;

public class RentedShowListRqst extends SessionRequestable
{
	/* Constuction Methods */
	public RentedShowListRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		RentedShowListResp response;
		RentedShowList rentedShowList;
		ShowList showList;
		ShowProviderList showProviderList;
		ShowProviderList thisShowProviderList;
		Show show;
		Player player;

		response = new RentedShowListResp();

		rentedShowList = RentedShowList.findByMemberID(fMemberID);
		showList = ShowList.findByRentedShowMemberID(fMemberID);
		showProviderList = ShowProviderList.findByRentedShowMemberID(fMemberID);
		player = PlayerManager.getThe().getPlayer(fMemberSession.getPlayerID());

		for(RentedShow rentedShow : rentedShowList)
		{
			show = showList.get(rentedShow.getShowID());
			thisShowProviderList = showProviderList.findItemsByShowIDProviderID(rentedShow.getShowID(),
				rentedShow.getProviderID()).findItemsByPlayerMimeType(player);

			if(thisShowProviderList.size() > 0)
				response.getRentedShowSearchList().add(new RentedShowSearch(rentedShow, show));
		}

		fStatusCode = StatusCode.sc_Success;
		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		// No Fields
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		// No Fields
	}
}
