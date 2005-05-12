/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.DataExists;

public class Provider extends DatabaseObject
{
	/* Properties */
	ProviderID fProviderID;
	String fName;

	private static DatabaseAdaptor fDatabaseAdaptor = DatabaseAdaptor.newInstance(Provider.class, ProviderList.class, 2);
	public static DatabaseAdaptor getDatabaseAdaptor() { return fDatabaseAdaptor; }

	/* Getters and Setters */
	public ProviderID getProviderID() { return fProviderID; }
	public String getName() { return fName; }

	/* Constuction Methods */
	public Provider(DataReader reader) throws Exception
	{
		super(reader);
		readFrom(reader);
	}

	protected static Provider load(ProviderID providerID, DataExists exists) throws Exception
	{
		return (Provider)fDatabaseAdaptor.selectByKey(providerID, exists);
	}

	public static Provider get(ProviderID providerID) throws Exception
	{
		return load(providerID, DataExists.MustExist);
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fProviderID = (ProviderID)reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
		fName = reader.readString("Name", 64);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeString("Name", fName, 64);
	}
}
