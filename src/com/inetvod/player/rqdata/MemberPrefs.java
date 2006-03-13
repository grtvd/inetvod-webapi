/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.IncludeAdult;

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
