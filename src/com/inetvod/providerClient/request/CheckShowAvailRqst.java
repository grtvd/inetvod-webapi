/**
 * Copyright © 2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.providerClient.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.ShowID;
import com.inetvod.common.dbdata.ProviderShowID;

public class CheckShowAvailRqst implements Writeable
{
	/* Fields */
	private ProviderShowID fShowID;

	/* Construction */
	private CheckShowAvailRqst(ProviderShowID showID)
	{
		fShowID = showID;
	}

	public static CheckShowAvailRqst newInstance(ProviderShowID showID)
	{
		return new CheckShowAvailRqst(showID);
	}

	/* Implementation */
	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
	}
}
