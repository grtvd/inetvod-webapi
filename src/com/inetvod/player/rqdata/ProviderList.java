/**
 * Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import java.util.ArrayList;
import java.util.Collection;

public class ProviderList extends ArrayList<Provider>
{
	/* Construction */
	public static ProviderList newInstance(Collection<? extends com.inetvod.common.dbdata.Provider> providerList)
	{
		ProviderList thisProviderList = new ProviderList();

		thisProviderList.addAllProviderList(providerList);

		return thisProviderList;
	}

	/* Implementation */
	public void addAllProviderList(Collection<? extends com.inetvod.common.dbdata.Provider> providerList)
	{
		for(com.inetvod.common.dbdata.Provider provider : providerList)
			add(new Provider(provider));
	}
}
