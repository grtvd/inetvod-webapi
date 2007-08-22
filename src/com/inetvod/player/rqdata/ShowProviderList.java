/**
 * Copyright © 2004-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import java.util.ArrayList;
import java.util.HashMap;

import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.ShowCostList;

public class ShowProviderList extends ArrayList<ShowProvider>
{
	/* Construction Methods */
	public ShowProviderList()
	{
	}

	public ShowProviderList(com.inetvod.common.dbdata.ShowProviderList showProviderList)
	{
		HashMap<ProviderID, com.inetvod.common.dbdata.ShowProviderList> providerMap = showProviderList.splitByProviderID();
		for(ProviderID providerID : providerMap.keySet())
		{
			ShowCostList showCostList = providerMap.get(providerID).combineShowCostList();
			showCostList.sort();
			add(new ShowProvider(providerID, showCostList));
		}
	}
}
