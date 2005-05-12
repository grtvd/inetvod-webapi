/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

import java.util.ArrayList;
import java.util.Iterator;

public class MemberProviderList extends ArrayList
{
	public MemberProviderList(com.inetvod.common.dbdata.MemberProviderList memberProviderList)
	{
		Iterator iter = memberProviderList.iterator();

		while(iter.hasNext())
			add(new MemberProvider((com.inetvod.common.dbdata.MemberProvider)iter.next()));
	}
}
