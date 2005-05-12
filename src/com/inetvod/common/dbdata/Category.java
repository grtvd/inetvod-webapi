package com.inetvod.common.dbdata;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.DataExists;

/**
 * Created by IntelliJ IDEA.
 * User: Bob
 * Date: Jul 7, 2004
 * Time: 9:52:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Category extends DatabaseObject
{
	/* Properties */
	CategoryID fCategoryID;
	String fName;

	private static DatabaseAdaptor fDatabaseAdaptor = DatabaseAdaptor.newInstance(Category.class, CategoryList.class, 2);
	public static DatabaseAdaptor getDatabaseAdaptor() { return fDatabaseAdaptor; }

	/* Getters and Setters */


	/* Constuction Methods */
	public Category(DataReader reader) throws Exception
	{
		super(reader);
		readFrom(reader);
	}

	protected static Category load(CategoryID categoryID, DataExists exists) throws Exception
	{
		return (Category)fDatabaseAdaptor.selectByKey(categoryID, exists);
	}

	public static Category get(CategoryID categoryID) throws Exception
	{
		return load(categoryID, DataExists.MustExist);
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fCategoryID = (CategoryID)reader.readDataID("CategoryID", CategoryID.MaxLength, CategoryID.CtorString);
		fName = reader.readString("Name", 64);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("CategoryID", fCategoryID, CategoryID.MaxLength);
		writer.writeString("Name", fName, 64);
	}
}
