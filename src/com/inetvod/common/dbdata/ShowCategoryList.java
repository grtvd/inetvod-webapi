/**
 * Copyright � 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Types;

public class ShowCategoryList extends ArrayList
{
	/* Constuction Methods */
	public ShowCategoryList()
	{
	}

	public static ShowCategoryList findByShowName(String partialName) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[1];

		params[0] = new DatabaseProcParam(Types.VARCHAR, partialName);

		return (ShowCategoryList)ShowCategory.getDatabaseAdaptor().selectManyByProc("ShowCategory_Search", params);
	}

	public static ShowCategoryList findByShowID(ShowID showID) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[1];

		params[0] = new DatabaseProcParam(Types.VARCHAR, showID.toString());

		return (ShowCategoryList)ShowCategory.getDatabaseAdaptor().selectManyByProc("ShowCategory_GetByShowID", params);
	}

	public static ShowCategoryList findByCategoryIDList(CategoryIDList categoryIDList) throws Exception
	{
		ShowCategoryList showCategoryList = new ShowCategoryList();
		Iterator iter = categoryIDList.iterator();
		CategoryID categoryID;
		DatabaseProcParam params[];

		while(iter.hasNext())
		{
			categoryID = (CategoryID)iter.next();
			params = new DatabaseProcParam[1];

			params[0] = new DatabaseProcParam(Types.VARCHAR, categoryID.toString());

			showCategoryList.merge((ShowCategoryList)ShowCategory.getDatabaseAdaptor().selectManyByProc("ShowCategory_GetByCategoryID", params));
		}

		return showCategoryList;
	}

	public static ShowCategoryList findByRentedShowMemberID(MemberID memberID) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[1];

		params[0] = new DatabaseProcParam(Types.VARCHAR, memberID.toString());

		return (ShowCategoryList)ShowCategory.getDatabaseAdaptor().selectManyByProc("ShowCategory_GetByRentedShowMemberID", params);
	}

	/* Group Methods */

	/**
	 * Returns a sub-set of items from this list that have the specified ShowID
	 * @param showID
	 * @return
	 */
	public ShowCategoryList findItemsByShowID(ShowID showID)
	{
		ShowCategoryList showCategoryList = new ShowCategoryList();
		ShowCategory showCategory;
		Iterator iter = iterator();

		while(iter.hasNext())
		{
			showCategory = (ShowCategory)iter.next();
			if(showCategory.getShowID().equals(showID))
				showCategoryList.add(showCategory);
		}

		return showCategoryList;
	}

	/**
	 * Returns a sub-set of items from this list that have the specified CategoryID
	 * @param categoryIDList
	 * @return
	 */
	public ShowCategoryList findItemsByCategoryIDList(CategoryIDList categoryIDList)
	{
		ShowCategoryList showCategoryList = new ShowCategoryList();
		ShowCategory showCategory;
		Iterator iter = iterator();

		while(iter.hasNext())
		{
			showCategory = (ShowCategory)iter.next();
			if(categoryIDList.contains(showCategory.getCategoryID()))
				showCategoryList.add(showCategory);
		}

		return showCategoryList;
	}

	/**
	 * Returns a list of CategoryID from the items in this list.
	 * @return
	 */
	public CategoryIDList getCategoryIDList()
	{
		CategoryIDList categoryIDList = new CategoryIDList();
		ShowCategory showCategory;
		Iterator iter = iterator();

		while(iter.hasNext())
		{
			showCategory = (ShowCategory)iter.next();
			categoryIDList.add(showCategory.getCategoryID());
		}

		return categoryIDList;
	}

	/* Item Methods */
	public ShowCategory getItem(int index)
	{
		return (ShowCategory)get(index);
	}

	public int indexOf(ShowCategoryID showCategoryID)
	{
		for(int i = 0; i < size(); i++)
			if(getItem(i).getShowCategoryID().equals(showCategoryID))
				return i;

		return -1;
	}

	public ShowCategory get(ShowCategoryID showCategoryID) throws Exception
	{
		int pos = indexOf(showCategoryID);
		if(pos >= 0)
			return getItem(pos);

		throw new Exception("ShowCategory not found");
	}

	public ShowCategory find(ShowCategoryID showCategoryID)
	{
		int pos = indexOf(showCategoryID);
		if(pos >= 0)
			return getItem(pos);

		return null;
	}

	/* Group Methods */
	public void merge(ShowCategoryList showCategoryList)
	{
		Iterator iter = showCategoryList.iterator();
		ShowCategory showCategory;

		while(iter.hasNext())
		{
			showCategory = (ShowCategory)iter.next();
			if(find(showCategory.getShowCategoryID()) == null)
				add(showCategory);
		}
	}
}