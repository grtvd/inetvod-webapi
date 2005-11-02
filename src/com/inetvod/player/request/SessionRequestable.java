/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.data.MemberID;
import com.inetvod.common.dbdata.MemberSession;
import com.inetvod.player.rqdata.PlayerRequestable;
import com.inetvod.player.rqdata.StatusCode;

/**
 * A Requestable that understands SessionData.
 */
public abstract class SessionRequestable implements PlayerRequestable
{
	//private String fVersion;
	//private String fRequestID;

	protected MemberSession fMemberSession;
	protected MemberID fMemberID;
	//protected Member fMember;

	protected StatusCode fStatusCode = StatusCode.sc_GeneralError;
	public StatusCode getStatusCode() { return fStatusCode; }

	public StatusCode setRequest(String version, String requestID, SessionData sessionData) throws Exception
	{
		//fVersion = version;
		//fRequestID = requestID;

		if((sessionData == null) || (sessionData.getMemberSessionID() == null))
			return StatusCode.sc_GeneralError;

		fMemberSession = MemberSession.find(sessionData.getMemberSessionID());
		if((fMemberSession == null) || (System.currentTimeMillis() > fMemberSession.getExpiresAt().getTime()))
		{
			if(fMemberSession != null)
				fMemberSession.delete();
			return StatusCode.sc_InvalidSession;
		}

		fMemberID = fMemberSession.getMemberID();
		//fMember = Member.get(fMemberSession.getMemberID());
		return StatusCode.sc_Success;
	}
}
