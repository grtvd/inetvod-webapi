/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;


import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Requestable;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.core.Writeable;

public class INetVODPlayerRqst implements Requestable
{
	/* Constants */
	public static final int VersionMaxLength = 16;
	public static final int RequestIDMaxLength = 64;

	public static final Constructor<INetVODPlayerRqst> CtorDataFiler = DataReader.getCtor(INetVODPlayerRqst.class);

	/* Fields */
	protected String fVersion;
	protected String fRequestID;
	public String getRequestID() { return fRequestID; }

	protected SessionData fSessionData;
	protected RequestData fRequestData;

	protected StatusCode fStatusCode = StatusCode.sc_GeneralError;
	public StatusCode getStatusCode() { return fStatusCode; }

	public INetVODPlayerRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		INetVODPlayerResp response = new INetVODPlayerResp();

		// validate request
		//TODO: validate version of client

		// fulfull request
		response.setRequestID(fRequestID);

		fStatusCode = fRequestData.setRequest(fVersion, fRequestID, fSessionData);
		if(StatusCode.sc_Success.equals(fStatusCode))
		{
			response.setResponseData((ResponseData)fRequestData.fulfillRequest());
			fStatusCode = fRequestData.getStatusCode();
		}
		setStatus(response, fStatusCode);

		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fVersion = reader.readString("Version", VersionMaxLength);
		fRequestID = reader.readString("RequestID", RequestIDMaxLength);
		fSessionData = new SessionData(reader.readString("SessionData", SessionData.SessionDataMaxLength));

		fRequestData = reader.readObject("RequestData", RequestData.CtorDataFiler);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("Version", fVersion, VersionMaxLength);
		writer.writeString("RequestID", fRequestID, RequestIDMaxLength);
		writer.writeString("SessionData", fSessionData.toString(), SessionData.SessionDataMaxLength);

		writer.writeObject("RequestData", fRequestData);
	}

	protected void setStatus(INetVODPlayerResp response, StatusCode statusCode)
	{
		//TODO: move this a config file or DB
		response.setStatusCode(statusCode);

		if(StatusCode.sc_Success.equals(statusCode))
			response.setStatusMessage(null);
		else if(StatusCode.sc_InvalidUserID.equals(statusCode))
			response.setStatusMessage("We were unable to log you onto the system due to an invalid Logon ID or PIN.");
		else if(StatusCode.sc_InvalidSession.equals(statusCode))
			response.setStatusMessage("Your session has expired and it no longer valid.  Please try again.");
		else if(StatusCode.sc_UserIDPasswordMismatch.equals(statusCode))
			response.setStatusMessage("We were unable to log you onto the system due to an invalid Logon ID or PIN.");
		else if(StatusCode.sc_InvalidProviderUserIDPassword.equals(statusCode))
			response.setStatusMessage("TODO: need message");
		else if(StatusCode.sc_AlreadyEnrolledAtProvider.equals(statusCode))
			response.setStatusMessage("TODO: need message");
		else if(StatusCode.sc_NoAutoProviderEnrollment.equals(statusCode))
			response.setStatusMessage("TODO: need message");
		else if(StatusCode.sc_Player_Missing.equals(statusCode))
			response.setStatusMessage("Player information was not specified.");
		else if(StatusCode.sc_Player_OutOfDate.equals(statusCode))
			response.setStatusMessage("This version of the player is no longer supported. A newer version of this player is available.");
		else if(StatusCode.sc_ShowSearch_NeedCriteiia.equals(statusCode))
			response.setStatusMessage("A partial title, provider, or category must be provided for searching.");
		else
			response.setStatusMessage("An unknown error has occurred. Please try again. If the problem persists, please contact customer service.");	//TODO: better messages
	}
}
