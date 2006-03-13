/**
 * Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ProviderID;

public class Provider implements Writeable
{
	/* Fields */
	private ProviderID fProviderID;
	private String fName;

	/* Construction */
	public Provider(com.inetvod.common.dbdata.Provider provider)
	{
		fProviderID = provider.getProviderID();
		fName = provider.getName();
	}

	/* Implementation */
	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeString("Name", fName, com.inetvod.common.dbdata.Provider.NameMaxLength);
	}
}
