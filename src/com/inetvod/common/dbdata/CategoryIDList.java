/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.CtorUtil;

import java.util.ArrayList;
import java.lang.reflect.Constructor;

public class CategoryIDList extends ArrayList
{
	public static final Constructor Ctor = CtorUtil.getCtorDefault(CategoryIDList.class);

	/* Item Methods */
	public CategoryID getItem(int index)
	{
		return (CategoryID)super.get(index);
	}

	public int indexOf(CategoryID categoryID)
	{
		for(int i = 0; i < size(); i++)
			if(getItem(i).equals(categoryID))
				return i;

		return -1;
	}

	public boolean contains(CategoryID categoryID)
	{
		return (indexOf(categoryID) >= 0);
	}
}