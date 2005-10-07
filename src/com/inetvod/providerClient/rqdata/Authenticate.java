/**
 * Copyright � 2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.providerClient.rqdata;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.Writeable;

public class Authenticate implements Readable, Writeable
{
	/* Constants */
	public static final Constructor<Authenticate> CtorDataFiler = DataReader.getCtor(Authenticate.class);

	private static final int AdminUserIDMaxLength = 64;
	private static final int AdminPasswordMaxLength = 32;
	private static final int MemberUserIDMaxLength = 64;
	private static final int MemberPasswordMaxLength = 32;

	/* Fields */
	private String fAdminUserID;
	private String fAdminPassword;
	private String fMemberUserID;
	private String fMemberPassword;

	/* Construction */
	private Authenticate(String adminUserID, String adminPassword)
	{
		fAdminUserID = adminUserID;
		fAdminPassword = adminPassword;
	}

	public Authenticate(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public static Authenticate newInstance(String adminUserID, String adminPassword)
	{
		return new Authenticate(adminUserID, adminPassword);
	}

	/* Getters/Setters */
	public void setMemberUser(String memberUserID, String memberPassword)
	{
		fMemberUserID = memberUserID;
		fMemberPassword = memberPassword;
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fAdminUserID = reader.readString("AdminUserID", AdminUserIDMaxLength);
		fAdminPassword = reader.readString("AdminPassword", AdminPasswordMaxLength);
		fMemberUserID = reader.readString("MemberUserID", MemberUserIDMaxLength);
		fMemberPassword = reader.readString("MemberPassword", MemberPasswordMaxLength);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("AdminUserID", fAdminUserID, AdminUserIDMaxLength);
		writer.writeString("AdminPassword", fAdminPassword, AdminPasswordMaxLength);
		writer.writeString("MemberUserID", fMemberUserID, MemberUserIDMaxLength);
		writer.writeString("MemberPassword", fMemberPassword, MemberPasswordMaxLength);
	}
}
