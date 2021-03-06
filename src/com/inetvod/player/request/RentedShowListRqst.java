/**
 * Copyright � 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import java.util.HashSet;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.RatingID;
import com.inetvod.common.dbdata.Player;
import com.inetvod.common.dbdata.PlayerManager;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.RentedShowList;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowList;
import com.inetvod.common.dbdata.ShowProvider;
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
		ShowProvider showProvider;
		Show show;
		Player player;
		boolean includeAdult;
		HashSet<RatingID> includeRatingIDSet;

		response = new RentedShowListResp();

		rentedShowList = RentedShowList.findByMemberID(fMemberID);
		if(rentedShowList.size() > 0)
		{
			includeAdult = fMemberSession.getShowAdult();
			if(!includeAdult)
				includeRatingIDSet = fMemberSession.getIncludeRatingIDList().getHashSet();
			else
				includeRatingIDSet = new HashSet<RatingID>();

			showList = ShowList.findByRentedShowMemberID(fMemberID);
			showProviderList = ShowProviderList.findByRentedShowMemberID(fMemberID);
			player = PlayerManager.getThe().getPlayer(fMemberSession.getPlayerID());

			for(RentedShow rentedShow : rentedShowList)
			{
				show = showList.get(rentedShow.getShowID());
				showProvider = showProviderList.get(rentedShow.getShowProviderID());

				if(player.supportsFormat(showProvider.getShowFormat(), showProvider.getShowFormatMime())
						&& (includeAdult || !show.getIsAdult())
						&& (includeAdult || includeRatingIDSet.contains((show.getRatingID() != null)
							? show.getRatingID() : RatingID.NotRated)))
					response.getRentedShowSearchList().add(new RentedShowSearch(rentedShow, show));
			}
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
