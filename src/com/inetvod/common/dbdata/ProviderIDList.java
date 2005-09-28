/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.inetvod.common.core.CtorUtil;

public class ProviderIDList extends ArrayList<ProviderID>
{
	public static final Constructor Ctor = CtorUtil.getCtorDefault(ProviderIDList.class);

	/* Item Methods */
	public ProviderID getItem(int index)
	{
		return (ProviderID)get(index);
	}

	public int indexOf(ProviderID providerID)
	{
		for(int i = 0; i < size(); i++)
			if(getItem(i).equals(providerID))
				return i;

		return -1;
	}

	public boolean contains(ProviderID providerID)
	{
		return (indexOf(providerID) >= 0);
	}
}