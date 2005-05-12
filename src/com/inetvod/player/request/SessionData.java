package com.inetvod.player.request;

import com.inetvod.common.dbdata.MemberID;

/**
 * Summary description for SessionData.
 */
public class SessionData
{
	/* Constants */
	public static final int SessionDataMaxLength = Short.MAX_VALUE;

	/* Fields */
	protected MemberID fMemberID;
	//public string UserID = "";
	//public string Password = "";

	protected String fDataEncoded = null;

	public MemberID getMemberID() { return fMemberID; }

	public SessionData(MemberID memberID)
	{
		fMemberID = memberID;
		if(fMemberID != null)
			fDataEncoded = "xxx|" + fMemberID.toString() + "|xxx";
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
				fMemberID = new MemberID(parts[1]);
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
