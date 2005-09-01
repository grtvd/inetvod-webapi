/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.core.Writeable;

public class EnableAdultAccessRqst extends SessionRequestable
{
	/* Constants */
	public static final int EncryptedPasswordMaxLength = 32;

	/* Fields */
	protected String fPassword;

	/* Constuction Methods */
	public EnableAdultAccessRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		EnableAdultAccessResp response;

		//TODO: decrypt Password based on SessionData:Player

		response = new EnableAdultAccessResp();


		fStatusCode = StatusCode.sc_Success;
		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fPassword = reader.readString("Password", EncryptedPasswordMaxLength);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("Password", fPassword, EncryptedPasswordMaxLength);
	}
}
