/**
 * Copyright © 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import java.util.HashMap;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.CategoryID;
import com.inetvod.common.data.CategoryIDList;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.ProviderIDList;
import com.inetvod.common.data.RatingID;
import com.inetvod.common.data.RatingIDList;
import com.inetvod.common.data.ShowID;
import com.inetvod.common.dbdata.Player;
import com.inetvod.common.dbdata.PlayerManager;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowCategoryList;
import com.inetvod.common.dbdata.ShowList;
import com.inetvod.common.dbdata.ShowProviderList;
import com.inetvod.player.rqdata.ShowSearch;
import com.inetvod.player.rqdata.StatusCode;

public class ShowSearchRqst extends SessionRequestable
{
	/* Constants */
	private static final int SearchMaxLength = 64;

	/* Fields */
	private String fSearch;

	private ProviderIDList fProviderIDList = new ProviderIDList();
	private CategoryIDList fCategoryIDList = new CategoryIDList();
	private RatingIDList fRatingIDList = new RatingIDList();

	private Short fMaxResults;

	/* Constuction Methods */
	public ShowSearchRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	protected boolean areGuestsAllowedForRequest() { return true; }

	public Writeable fulfillRequest() throws Exception
	{
		ShowSearchResp response;
		ShowSearch showSearch;
		ShowList showList;
		ShowProviderList showProviderList;
		ShowProviderList thisShowProviderList;
		ShowCategoryList showCategoryList = null;
		boolean includeAdult;
		Player player;
		short numFound = 0;

		includeAdult = fMemberSession.getShowAdult();

		response = new ShowSearchResp();
		response.ReachedMax = false;

		if(fMaxResults == null)
		{
			fStatusCode = StatusCode.sc_ShowSearch_NeedCriteiia;
			return response;
		}

		if(fSearch != null)
		{
			showList = ShowList.findByName(fSearch);

			showProviderList = ShowProviderList.findByShowNameAvailable(fSearch);
			if(fProviderIDList.size() > 0)
				showProviderList = showProviderList.findItemsByProviderIDList(fProviderIDList);

			if(fCategoryIDList.size() > 0)
			{
				showCategoryList = ShowCategoryList.findByShowName(fSearch);
				showCategoryList = showCategoryList.findItemsByCategoryIDList(fCategoryIDList);
			}
		}
		else if(fProviderIDList.size() > 0)
		{
			showList = ShowList.findByProviderIDList(fProviderIDList);

			showProviderList = ShowProviderList.findByProviderIDListAvailable(fProviderIDList);

			if(fCategoryIDList.size() > 0)
				showCategoryList = ShowCategoryList.findByCategoryIDList(fCategoryIDList);
		}
		else if(fCategoryIDList.size() > 0)
		{
			showList = ShowList.findByCategoryIDList(fCategoryIDList);

			showProviderList = ShowProviderList.findByCategoryIDListAvailable(fCategoryIDList);
			if(fProviderIDList.size() > 0)
				showProviderList = showProviderList.findItemsByProviderIDList(fProviderIDList);
		}
		else
		{
			fStatusCode = StatusCode.sc_ShowSearch_NeedCriteiia;
			return response;
		}

		player = PlayerManager.getThe().getPlayer(fMemberSession.getPlayerID());
		showProviderList = showProviderList.findItemsByPlayer(player);
		HashMap<ShowID, ShowProviderList> showShowProviderMap = showProviderList.splitByShowID();

		for(Show show : showList)
		{
			if(!includeAdult && show.getIsAdult())
				continue;

			thisShowProviderList = showShowProviderMap.get(show.getShowID());
			if((thisShowProviderList == null) || (thisShowProviderList.size() == 0))
				continue;

			if(showCategoryList != null)
				if(showCategoryList.findItemsByShowID(show.getShowID()).size() == 0)
					continue;

			if(fRatingIDList.size() > 0)
				if(!fRatingIDList.contains(show.getRatingID()))
					continue;

			// Test max results BEFORE adding to response list so response.ReachedMax is only set to true if the max
			// would have been exceeded (not just reached).
			numFound++;
			if(numFound > fMaxResults)
			{
				response.ReachedMax = true;
				break;
			}

			showSearch = new ShowSearch(show);
			response.ShowSearchList.add(showSearch);
			//TODO: short providers by lowest ShowCost (Free content first, then subscription, then pay-per-view)
			showSearch.getShowProviderList().addAll(new com.inetvod.player.rqdata.ShowProviderList(thisShowProviderList));
		}

		fStatusCode = StatusCode.sc_Success;
		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fSearch = reader.readString("Search", SearchMaxLength);

		fProviderIDList = reader.readStringList("ProviderID", ProviderID.MaxLength, ProviderIDList.Ctor,
			ProviderID.CtorString);
		fCategoryIDList = reader.readStringList("CategoryID", CategoryID.MaxLength, CategoryIDList.Ctor,
			CategoryID.CtorString);
		fRatingIDList = reader.readStringList("RatingID", RatingID.MaxLength, RatingIDList.Ctor, RatingID.CtorString);

		fMaxResults = reader.readShort("MaxResults");
		//TODO: ResultsPage
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("Search", fSearch, SearchMaxLength);

		writer.writeStringList("ProviderID", fProviderIDList, ProviderID.MaxLength);
		writer.writeStringList("CategoryID", fCategoryIDList, CategoryID.MaxLength);
		writer.writeStringList("RatingID", fRatingIDList, RatingID.MaxLength);

		writer.writeShort("MaxResults", fMaxResults);
	}
}
