/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import java.util.ArrayList;
import java.util.Collection;

public class CategoryList extends ArrayList<Category>
{
	public static CategoryList newInstance(Collection<? extends com.inetvod.common.dbdata.Category> categoryList)
	{
		CategoryList thisCategoryList = new CategoryList();

		thisCategoryList.addAllCategoryList(categoryList);

		return thisCategoryList;
	}

	/* Implementation */
	public void addAllCategoryList(Collection<? extends com.inetvod.common.dbdata.Category> categoryList)
	{
		for(com.inetvod.common.dbdata.Category category : categoryList)
			add(new Category(category));
	}
}
