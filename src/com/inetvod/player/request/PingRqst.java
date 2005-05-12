package com.inetvod.player.request;

import com.inetvod.common.core.*;

public class PingRqst implements Requestable
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
