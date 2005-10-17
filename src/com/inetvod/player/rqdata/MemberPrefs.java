/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.dbdata.IncludeAdult;

public class MemberPrefs implements Writeable
{
	/* Fields */
	protected IncludeAdult fIncludeAdult;

	public MemberPrefs(com.inetvod.common.dbdata.MemberPrefs memberPrefs)
	{
		fIncludeAdult = memberPrefs.getIncludeAdult();
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("IncludeAdult", IncludeAdult.convertToString(fIncludeAdult), IncludeAdult.MaxLength);
	}
}
