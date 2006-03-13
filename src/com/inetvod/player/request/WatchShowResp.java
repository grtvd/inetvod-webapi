/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.License;

public class WatchShowResp implements Writeable
{
	protected License fLicense;

	public License getLicense() { return fLicense; }
	public void setLicense(License license) { fLicense = license; }

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeObject("License", fLicense);
	}
}
