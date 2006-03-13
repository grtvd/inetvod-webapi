/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import java.util.UUID;

import com.inetvod.common.data.MemberSessionID;

public class SessionData
{
	/* Constants */
	public static final int SessionDataMaxLength = Short.MAX_VALUE;

	/* Fields */
	protected MemberSessionID fMemberSessionID;
	//public string UserID = "";
	//public string Password = "";

	protected String fDataEncoded;

	public MemberSessionID getMemberSessionID() { return fMemberSessionID; }

	public SessionData(MemberSessionID memberSessionID)
	{
		fMemberSessionID = memberSessionID;
		if(fMemberSessionID != null)
			fDataEncoded = UUID.randomUUID().toString() + "|" + fMemberSessionID.toString() + "|" + UUID.randomUUID().toString();
	}

	public SessionData(String value)
	{
		fDataEncoded = value;

		//TODO: develop better encoding/encryption
		//			UserID = "";
		//			Password = "";

		if(fDataEncoded != null)
		{
			String[] parts = fDataEncoded.split("\\|");
			if(parts.length > 1)
				fMemberSessionID = new MemberSessionID(parts[1]);
		//			if(parts.Length > 0)
		//				UserID = parts[0];
		//			if(parts.Length > 1)
		//				Password = parts[1];
		}
	}

	public String toString()
	{
		return fDataEncoded;
	}
}
