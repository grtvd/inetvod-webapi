package com.inetvod.player.request;

import com.inetvod.common.dbdata.Member;
import com.inetvod.common.core.*;

/**
 * A Requestable that understands SessionData.
 */
public abstract class SessionRequestable implements Requestable
{
	protected String fVersion;
	protected String fRequestID;
	protected SessionData fSessionData;

	protected Member fMember;

	protected StatusCode fStatusCode = StatusCode.sc_GeneralError;
	public StatusCode getStatusCode() { return fStatusCode; }

	public void setRequest(String version, String requestID, SessionData sessionData) throws Exception
	{
		fVersion = version;
		fRequestID = requestID;
		fSessionData = sessionData;

		if((fSessionData == null) || (fSessionData.getMemberID() == null))
			throw new Exception("Missing SessionData");
		fMember = Member.get(fSessionData.getMemberID());
	}

//	public abstract Writeable fulfillRequest();
//
//	public abstract void readFrom(DataReader reader) throws Exception;
//	public abstract void writeTo(DataWriter writer) throws Exception;
}
