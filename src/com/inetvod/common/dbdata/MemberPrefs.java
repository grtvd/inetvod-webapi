/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.DataExists;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.player.rqdata.IncludeAdult;

import java.util.ArrayList;

public class MemberPrefs extends DatabaseObject
{
	/* Constants */
	public static final int NumFields = 1;

	/* Properties */
	protected MemberID fMemberID;
	protected IncludeAdult fIncludeAdult;

	private static DatabaseAdaptor fDatabaseAdaptor = DatabaseAdaptor.newInstance(MemberPrefs.class, ArrayList.class, NumFields);
	public static DatabaseAdaptor getDatabaseAdaptor() { return fDatabaseAdaptor; }

	/* Getters and Setters */
	public MemberID getMemberID() { return fMemberID; }

	public IncludeAdult getIncludeAdult() { return fIncludeAdult; }
	public void setIncludeAdult(IncludeAdult includeAdult) { fIncludeAdult = includeAdult; }

	/* Constuction Methods */
	protected MemberPrefs(MemberID memberID)
	{
		super(true);
		fMemberID = memberID;
		fIncludeAdult = IncludeAdult.Never;
	}

	public MemberPrefs(DataReader reader) throws Exception
	{
		super(reader);
		readFrom(reader);
	}

	public static MemberPrefs newInstance(MemberID memberID)
	{
		return new MemberPrefs(memberID);
	}

	protected static MemberPrefs load(MemberID memberID, DataExists exists) throws Exception
	{
		return (MemberPrefs)fDatabaseAdaptor.selectByKey(memberID, exists);
	}

	public static MemberPrefs getCreate(MemberID memberID) throws Exception
	{
		MemberPrefs memberPrefs = load(memberID, DataExists.MayNotExist);

		if(memberPrefs == null)
			memberPrefs = newInstance(memberID);

		return memberPrefs;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fMemberID = (MemberID)reader.readDataID("MemberID", MemberID.MaxLength, MemberID.CtorString);
		fIncludeAdult = IncludeAdult.convertFromString(reader.readString("IncludeAdult", IncludeAdult.MaxLength));
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("MemberID", fMemberID, MemberID.MaxLength);
		writer.writeString("IncludeAdult", IncludeAdult.convertToString(fIncludeAdult), IncludeAdult.MaxLength);
	}

	public void update() throws Exception
	{
		fDatabaseAdaptor.update(this);
	}

	public void delete() throws Exception
	{
		fDatabaseAdaptor.delete(fMemberID);
	}

	static public void delete(MemberID memberID) throws Exception
	{
		fDatabaseAdaptor.delete(memberID);
	}
}
