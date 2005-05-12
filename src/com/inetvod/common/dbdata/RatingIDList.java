/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.CtorUtil;

import java.util.ArrayList;
import java.lang.reflect.Constructor;

public class RatingIDList extends ArrayList
{
	public static final Constructor Ctor = CtorUtil.getCtorDefault(RatingIDList.class);

	/* Item Methods */
	public RatingID getItem(int index)
	{
		return (RatingID)super.get(index);
	}

	public int indexOf(RatingID ratingID)
	{
		for(int i = 0; i < size(); i++)
			if(getItem(i).equals(ratingID))
				return i;

		return -1;
	}

	public boolean contains(RatingID ratingID)
	{
		return (indexOf(ratingID) >= 0);
	}
}