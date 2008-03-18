/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.CategoryID;

public class Category implements Writeable
{
	/* Fields */
	private CategoryID fCategoryID;
	private String fName;

	/* Construction */
	public Category(com.inetvod.common.dbdata.Category provider)
	{
		fCategoryID = provider.getCategoryID();
		fName = provider.getName();
	}

	/* Implementation */
	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("CategoryID", fCategoryID, CategoryID.MaxLength);
		writer.writeString("Name", fName, com.inetvod.common.dbdata.Category.NameMaxLength);
	}
}
