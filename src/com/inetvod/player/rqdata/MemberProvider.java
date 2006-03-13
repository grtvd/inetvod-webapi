/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ProviderID;

public class MemberProvider implements Writeable
{
	protected ProviderID fProviderID;

	public MemberProvider(com.inetvod.common.dbdata.MemberProvider memberProvider)
	{
		fProviderID = memberProvider.getProviderID();
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
	}
}
