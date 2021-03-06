/**
 * Copyright � 2004-2009 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.data.MemberID;
import com.inetvod.common.dbdata.Member;
import com.inetvod.common.dbdata.MemberSession;
import com.inetvod.player.rqdata.PlayerRequestable;
import com.inetvod.player.rqdata.StatusCode;

/**
 * A Requestable that understands SessionData.
 */
public abstract class SessionRequestable implements PlayerRequestable
{
	/* Fields */
	//private String fVersion;

	protected MemberSession fMemberSession;
	protected MemberID fMemberID;
	protected Member fMember;
	protected String fPlayerIPAddress;

	protected StatusCode fStatusCode = StatusCode.sc_GeneralError;
	public StatusCode getStatusCode() { return fStatusCode; }

	/* Implementation */
	protected boolean areGuestsAllowedForRequest() { return false; }

	public StatusCode setRequest(String version, SessionData sessionData, String playerIPAddress)
		throws Exception
	{
		//fVersion = version;

		if((sessionData == null) || (sessionData.getMemberSessionID() == null))
			return StatusCode.sc_GeneralError;

		fMemberSession = MemberSession.find(sessionData.getMemberSessionID());
		if((fMemberSession == null) || (System.currentTimeMillis() > fMemberSession.getExpiresAt().getTime()))
		{
			if(fMemberSession != null)
				fMemberSession.delete();
			return StatusCode.sc_InvalidSession;
		}

		if (MemberID.GuestMemberID.equals(fMemberSession.getMemberID()) && !areGuestsAllowedForRequest())
			return StatusCode.sc_GuestNotAllowed;

		fMemberID = fMemberSession.getMemberID();
		fPlayerIPAddress = playerIPAddress;
		return StatusCode.sc_Success;
	}

	public Member getMember() throws Exception
	{
		if(fMember == null)
			fMember = Member.get(fMemberID);

		return fMember;
	}
}
