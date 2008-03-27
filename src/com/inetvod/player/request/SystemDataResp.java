/**
 * Copyright © 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.CategoryList;
import com.inetvod.player.rqdata.ProviderList;
import com.inetvod.player.rqdata.RatingList;

public class SystemDataResp implements Writeable
{
	public ProviderList ProviderList;
	public CategoryList CategoryList;
	public RatingList RatingList;

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeList("Provider", ProviderList);
		writer.writeList("Category", CategoryList);
		writer.writeList("Rating", RatingList);
	}
}
