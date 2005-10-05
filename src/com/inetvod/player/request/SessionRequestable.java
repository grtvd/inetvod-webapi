/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.Requestable;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.dbdata.Member;
import com.inetvod.common.dbdata.MemberSession;

/**
 * A Requestable that understands SessionData.
 */
public abstract class SessionRequestable implements Requestable
{
	protected String fVersion;
	protected String fRequestID;
	protected SessionData fSessionData;

	protected MemberSession fMemberSession;
	protected Member fMember;

	protected StatusCode fStatusCode = StatusCode.sc_GeneralError;
	public StatusCode getStatusCode() { return fStatusCode; }

	public StatusCode setRequest(String version, String requestID, SessionData sessionData) throws Exception
	{
		fVersion = version;
		fRequestID = requestID;
		fSessionData = sessionData;

		if((fSessionData == null) || (fSessionData.getMemberSessionID() == null))
			return StatusCode.sc_GeneralError;

		fMemberSession = MemberSession.find(fSessionData.getMemberSessionID());
		if((fMemberSession == null) || (System.currentTimeMillis() > fMemberSession.getExpiresAt().getTime()))
		{
			if(fMemberSession != null)
				fMemberSession.delete();
			return StatusCode.sc_InvalidSession;
		}

		fMember = Member.get(fMemberSession.getMemberID());
		return StatusCode.sc_Success;
	}
}
