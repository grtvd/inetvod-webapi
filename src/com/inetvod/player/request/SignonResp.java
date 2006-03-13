/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import java.util.Date;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.MemberState;

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
