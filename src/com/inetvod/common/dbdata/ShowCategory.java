package com.inetvod.common.dbdata;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;

/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
public class ShowCategory extends  DatabaseObject
{
	/* Fields */
	protected ShowCategoryID fShowCategoryID;
	protected ShowID fShowID;
	protected CategoryID fCategoryID;

	private static DatabaseAdaptor fDatabaseAdaptor = DatabaseAdaptor.newInstance(ShowCategory.class, ShowCategoryList.class, 3);
	public static DatabaseAdaptor getDatabaseAdaptor() { return fDatabaseAdaptor; }

	/* Getters and Setters */
	public ShowCategoryID getShowCategoryID() { return fShowCategoryID; }
	public ShowID getShowID() { return fShowID; }
	public CategoryID getCategoryID() { return fCategoryID; }

	/* Constuction Methods */
	public ShowCategory(DataReader reader) throws Exception
	{
		super(reader);
		readFrom(reader);
	}

	/* Streamable Members */
	public void readFrom(DataReader reader) throws Exception
	{
		fShowCategoryID = (ShowCategoryID)reader.readDataID("ShowCategoryID", ShowCategoryID.MaxLength, ShowCategoryID.CtorString);
		fShowID = (ShowID)reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fCategoryID = (CategoryID)reader.readDataID("CategoryID", CategoryID.MaxLength, CategoryID.CtorString);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		//TODO: needs to be implemented
	}

}
