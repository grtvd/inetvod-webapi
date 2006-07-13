/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.crypto.CryptoDigest;
import com.inetvod.common.data.IncludeAdult;
import com.inetvod.common.dbdata.MemberPrefs;
import com.inetvod.player.rqdata.StatusCode;

public class EnableAdultAccessRqst extends SessionRequestable
{
	/* Constants */
	private static final int EncryptedPasswordMaxLength = 32;

	/* Fields */
	private String fPassword;

	/* Constuction Methods */
	public EnableAdultAccessRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		EnableAdultAccessResp response;
		MemberPrefs memberPrefs;

		// Confirm user desires to see Adult content
		memberPrefs = MemberPrefs.getCreate(fMemberID);
		if(IncludeAdult.Never.equals(memberPrefs.getIncludeAdult()))
			return null;

		String password = fPassword;

		if(StrUtil.isNumeric(password))	//TODO:
			try { password = CryptoDigest.encrypt(password); } catch(Exception e) {}

		if(!memberPrefs.getAdultPIN().equals(password))
		{
			fStatusCode = StatusCode.sc_InvalidAdultPIN;
			return null;
		}

		response = new EnableAdultAccessResp();

		// Update session to show adult
		fMemberSession.setShowAdult(true);
		fMemberSession.update();

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
