/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.PlayerRequestable;
import com.inetvod.player.rqdata.StatusCode;

public class PingRqst implements PlayerRequestable
{
	protected StatusCode fStatusCode = StatusCode.sc_GeneralError;
	public StatusCode getStatusCode() { return fStatusCode; }

	public PingRqst(DataReader filer)
	{
		readFrom(filer);
	}
	public Writeable fulfillRequest()
	{
		fStatusCode = StatusCode.sc_Success;
		return new PingResp();
	}

	public void readFrom(DataReader filer)
	{
		// No Fields
	}

	public void writeTo(DataWriter filer)
	{
		// No Fields
	}
}
