/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.DataWriter;
import com.inetvod.player.rqdata.MemberState;

import java.util.Date;

public class SignonResp implements Writeable
{
	protected SessionData fSessionData;
	protected Date fSessionExpires;
	protected MemberState fMemberState;

	public void setSessionData(SessionData sessionData) { fSessionData = sessionData; }
	public void setSessionExpires(Date sessionExpires) { fSessionExpires = sessionExpires; }
	public void setMemberState(MemberState memberState) { fMemberState = memberState; }

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("SessionData", ((fSessionData != null) ? fSessionData.toString() : null),
			SessionData.SessionDataMaxLength);
		writer.writeDateTime("SessionExpires", fSessionExpires);
		writer.writeObject("MemberState", fMemberState);
	}
}
