/**
 * Copyright © 2004-2009 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.StatusCode;

public class PlayerResp implements Writeable
{
	/* Constants */
	public static final int StatusMessageMaxLength = 1024;

	/* Fields */
	protected StatusCode fStatusCode;
	protected String fStatusMessage;
	protected ResponseData fResponseData;

	/* Getters and Setters */
	public void setStatusCode(StatusCode statusCode) { fStatusCode = statusCode; }
	public void setStatusMessage(String statusMessage) { fStatusMessage = statusMessage; }
	public void setResponseData(ResponseData responseData) { fResponseData = responseData; }

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeInt("StatusCode", StatusCode.convertToInt(fStatusCode));
		writer.writeString("StatusMessage", fStatusMessage, StatusMessageMaxLength);
		writer.writeObject("ResponseData", fResponseData);
	}
}
