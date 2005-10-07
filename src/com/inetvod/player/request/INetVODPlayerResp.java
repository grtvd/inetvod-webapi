/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.StatusCode;

public class INetVODPlayerResp implements Writeable
{
	/* Constants */
	public static final int RequestIDMaxLength = 64;
	public static final int StatusMessageMaxLength = 1024;

	/* Fields */
	protected String fRequestID;
	protected StatusCode fStatusCode;
	protected String fStatusMessage;
	protected ResponseData fResponseData;

	/* Getters and Setters */
	public void setRequestID(String requestID) { fRequestID = requestID; }
	public void setStatusCode(StatusCode statusCode) { fStatusCode = statusCode; }
	public void setStatusMessage(String statusMessage) { fStatusMessage = statusMessage; }
	public void setResponseData(ResponseData responseData) { fResponseData = responseData; }

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("RequestID", fRequestID, RequestIDMaxLength);
		writer.writeInt("StatusCode", StatusCode.convertToInt(fStatusCode));
		writer.writeString("StatusMessage", fStatusMessage, StatusMessageMaxLength);
		writer.writeObject("ResponseData", fResponseData);
	}
}
