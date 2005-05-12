/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.DataWriter;

public class MemberState implements Writeable
{
	/* Fields */
	protected MemberPrefs fMemberPrefs;
	protected MemberProviderList fMemberProviderList;

	/* Getters and Setters */
	public MemberPrefs getMemberPrefs() { return fMemberPrefs; }
	public void setMemberPrefs(MemberPrefs memberPrefs) { fMemberPrefs = memberPrefs; }

	public MemberProviderList getMemberProviderList() { return fMemberProviderList; }
	public void setMemberProviderList(MemberProviderList memberProviderList) { fMemberProviderList = memberProviderList; }

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeObject("MemberPrefs", fMemberPrefs);
		writer.writeList("MemberProvider", fMemberProviderList);
	}
}
