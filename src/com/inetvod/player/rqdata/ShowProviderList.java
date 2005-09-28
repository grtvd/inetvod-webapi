/**
 * Copyright � 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

import java.util.ArrayList;
import java.util.Iterator;

public class ShowProviderList extends ArrayList<ShowProvider>
{
	/* Construction Methods */
	public ShowProviderList()
	{
	}

	public ShowProviderList(com.inetvod.common.dbdata.ShowProviderList showProviderList)
	{
		Iterator iterator = showProviderList.iterator();
		com.inetvod.common.dbdata.ShowProvider showProvider;

		while(iterator.hasNext())
		{
			showProvider = (com.inetvod.common.dbdata.ShowProvider)iterator.next();
			add(new ShowProvider(showProvider));
		}
	}
}
