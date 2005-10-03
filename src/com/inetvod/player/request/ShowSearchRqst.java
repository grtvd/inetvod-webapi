/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import java.util.Iterator;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.CategoryID;
import com.inetvod.common.dbdata.CategoryIDList;
import com.inetvod.common.dbdata.ProviderID;
import com.inetvod.common.dbdata.ProviderIDList;
import com.inetvod.common.dbdata.RatingID;
import com.inetvod.common.dbdata.RatingIDList;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowCategoryList;
import com.inetvod.common.dbdata.ShowList;
import com.inetvod.common.dbdata.ShowProviderList;
import com.inetvod.player.rqdata.ShowSearch;

public class ShowSearchRqst extends SessionRequestable
{
	/* Fields */
	protected String fPartialName;

	protected ProviderIDList fProviderIDList = new ProviderIDList();
	protected CategoryIDList fCategoryIDList = new CategoryIDList();
	protected RatingIDList fRatingIDList = new RatingIDList();

	protected Short fMaxResults;

	/* Constuction Methods */
	public ShowSearchRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		ShowSearchResp response;
		ShowSearch showSearch;
		Show show;
		ShowList showList;
		ShowProviderList showProviderList;
		ShowProviderList thisShowProviderList;
		ShowCategoryList showCategoryList = null;
		Iterator iterator;
		short numFound = 0;

		response = new ShowSearchResp();
		response.ReachedMax = false;

		if(fPartialName != null)
		{
			showList = ShowList.findByName(fPartialName);

			showProviderList = ShowProviderList.findByShowName(fPartialName);
			if(fProviderIDList.size() > 0)
				showProviderList = showProviderList.findItemsByProviderIDList(fProviderIDList);

			if(fCategoryIDList.size() > 0)
			{
				showCategoryList = ShowCategoryList.findByShowName(fPartialName);
				showCategoryList = showCategoryList.findItemsByCategoryIDList(fCategoryIDList);
			}
		}
		else if(fProviderIDList.size() > 0)
		{
			showList = ShowList.findByProviderIDList(fProviderIDList);

			showProviderList = ShowProviderList.findByProviderIDList(fProviderIDList);

			if(fCategoryIDList.size() > 0)
				showCategoryList = ShowCategoryList.findByCategoryIDList(fCategoryIDList);
		}
		else if(fCategoryIDList.size() > 0)
		{
			showList = ShowList.findByCategoryIDList(fCategoryIDList);

			showProviderList = ShowProviderList.findByCategoryIDList(fCategoryIDList);
			if(fProviderIDList.size() > 0)
				showProviderList = showProviderList.findItemsByProviderIDList(fProviderIDList);
		}
		else
		{
			fStatusCode = StatusCode.sc_ShowSearch_NeedCriteiia;
			return response;
		}

		iterator = showList.iterator();
		while(iterator.hasNext())
		{
			show = (Show)iterator.next();

			if(numFound > fMaxResults)
			{
				response.ReachedMax = true;
				break;
			}
			numFound++;

			thisShowProviderList = showProviderList.findItemsByShowID(show.getShowID());
			if(thisShowProviderList.size() == 0)
				continue;

			if(showCategoryList != null)
				if(showCategoryList.findItemsByShowID(show.getShowID()).size() == 0)
					continue;

			if(fRatingIDList.size() > 0)
				if(!fRatingIDList.contains(show.getRatingID()))
					continue;

			showSearch = new ShowSearch(show);
			response.ShowSearchList.add(showSearch);
			showSearch.getShowProviderList().addAll(new com.inetvod.player.rqdata.ShowProviderList(thisShowProviderList));
		}

		fStatusCode = StatusCode.sc_Success;
		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fPartialName = reader.readString("PartialName", Show.NameMaxLength);

		fProviderIDList = reader.readStringList("ProviderID", ProviderID.MaxLength, ProviderIDList.Ctor,
			ProviderID.CtorString);
		fCategoryIDList = reader.readStringList("CategoryID", CategoryID.MaxLength, CategoryIDList.Ctor,
			CategoryID.CtorString);
		fRatingIDList = reader.readStringList("RatingID", RatingID.MaxLength, RatingIDList.Ctor, RatingID.CtorString);

		fMaxResults = reader.readShort("MaxResults");
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("PartialName", fPartialName, Show.NameMaxLength);

		writer.writeStringList("ProviderID", fProviderIDList, ProviderID.MaxLength);
		writer.writeStringList("CategoryID", fCategoryIDList, CategoryID.MaxLength);
		writer.writeStringList("RatingID", fRatingIDList, RatingID.MaxLength);

		writer.writeShort("MaxResults", fMaxResults);
	}
}
