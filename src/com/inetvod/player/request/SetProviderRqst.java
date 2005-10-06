/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.MemberProvider;
import com.inetvod.common.dbdata.ProviderID;

public class SetProviderRqst extends SessionRequestable
{
	/* Fields */
	private ProviderID fProviderID;
	private String fUserID;
	private String fPassword;

	/* Constuction Methods */
	public SetProviderRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		SetProviderResp response;
		MemberProvider memberProvider;

		//TODO: decrypt UserID and Password based on Player

		response = new SetProviderResp();

		memberProvider = MemberProvider.findByMemberIDProviderID(fMemberID, fProviderID);
		if(memberProvider == null)
			memberProvider = MemberProvider.newInstance(fMemberID, fProviderID);

		memberProvider.setEncryptedUserName(fUserID);
		memberProvider.setEncryptedPassword(fPassword);
		memberProvider.update();

		fStatusCode = StatusCode.sc_Success;

		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
		fUserID = reader.readString("UserID", MemberProvider.EncryptedUserIDMaxLength);
		fPassword = reader.readString("Password", MemberProvider.EncryptedPasswordMaxLength);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeString("UserID", fUserID, MemberProvider.EncryptedUserIDMaxLength);
		writer.writeString("Password", fPassword, MemberProvider.EncryptedPasswordMaxLength);
	}
}
