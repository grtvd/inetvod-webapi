/**
 * Copyright © 2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.ProviderID;

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
