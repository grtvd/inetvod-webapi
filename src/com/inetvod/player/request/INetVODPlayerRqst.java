/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.PlayerRequestable;
import com.inetvod.player.rqdata.StatusCode;

public class INetVODPlayerRqst implements PlayerRequestable
{
	/* Constants */
	private static final int VersionMaxLength = 16;
	private static final int RequestIDMaxLength = 64;

	public static final Constructor<INetVODPlayerRqst> CtorDataReader = DataReader.getCtor(INetVODPlayerRqst.class);

	/* Fields */
	private String fVersion;
	private String fRequestID;
	public String getRequestID() { return fRequestID; }

	private SessionData fSessionData;

	private RequestData fRequestData;
	public RequestData getRequestData() { return fRequestData; }

	private StatusCode fStatusCode = StatusCode.sc_GeneralError;
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

		fRequestData = reader.readObject("RequestData", RequestData.CtorDataReader);
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
		else if(StatusCode.sc_InvalidUserIDPassword.equals(statusCode))
			response.setStatusMessage("We were unable to log you onto the system due to an invalid Logon ID or PIN.");
		else if(StatusCode.sc_InvalidSession.equals(statusCode))
			response.setStatusMessage("Your session has expired and it no longer valid.  Please try again.");
		else if(StatusCode.sc_InvalidProviderUserIDPassword.equals(statusCode))
			response.setStatusMessage("This Provider has reported that your User ID or Password is invalid. Please submit them again.");
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
		else if(StatusCode.sc_NoProviderResponse.equals(statusCode))
			response.setStatusMessage("This Provider cannot be communicated with at this time. Please try again later.");
		else if(StatusCode.sc_UnknownProviderResponse.equals(statusCode))
			response.setStatusMessage("An unknown error was returned from this Provider. Please try again later.");
		else if(StatusCode.sc_CreditCardNotOnFile.equals(statusCode))
			response.setStatusMessage("Your credit card must be on file with iNetVOD or this Provider to rent pay-per-view content. You may update your credit card preferences at inetvod.com.");
		else if(StatusCode.sc_CreditCardDenied.equals(statusCode))
			response.setStatusMessage("Your credit card was denied by this Provider. Please review your credit card information at inetvod.com.");
		else if(StatusCode.sc_ShowNoAccess.equals(statusCode))
			response.setStatusMessage("Your subscription with this Provider does not allow access to this show. Please review your subsription with this Provider.");
		else if(StatusCode.sc_ShowLevelInsufficient.equals(statusCode))
			response.setStatusMessage("Your subscription level with this Provider is too low to rent this show. Please review your subsription with this Provider.");
		else if(StatusCode.sc_ShowPaymentDenied.equals(statusCode))
			response.setStatusMessage("Your payment has been denied by this Provider. Please confirm your payment options with this Provider.");
		else if(StatusCode.sc_ShowRentExpired.equals(statusCode))
			response.setStatusMessage("This Show's rentail period has expired and as a result, this Show no longer viewable.");
		else
			response.setStatusMessage("An unknown error has occurred. Please try again. If the problem persists, please contact customer service.");	//TODO: better messages
	}
}
