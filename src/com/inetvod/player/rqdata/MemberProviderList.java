/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import java.util.ArrayList;
import java.util.Iterator;

public class MemberProviderList extends ArrayList<MemberProvider>
{
	public MemberProviderList(com.inetvod.common.dbdata.MemberProviderList memberProviderList)
	{
		Iterator iter = memberProviderList.iterator();

		while(iter.hasNext())
			add(new MemberProvider((com.inetvod.common.dbdata.MemberProvider)iter.next()));
	}
}
