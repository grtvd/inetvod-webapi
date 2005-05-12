package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.core.Writeable;

public class INetVODPlayerResp implements Writeable
{
	protected String fRequestID;
	protected StatusCode fStatusCode;
	protected String fStatusMessage;
	protected ResponseData fResponseData;

	public void setRequestID(String requestID) { fRequestID = requestID; }
	public void setStatusCode(StatusCode statusCode) { fStatusCode = statusCode; }
	public void setStatusMessage(String statusMessage) { fStatusMessage = statusMessage; }
	public void setResponseData(ResponseData responseData) { fResponseData = responseData; }

	public void readFrom(DataReader reader) throws Exception
	{
		//To change body of implemented methods use File | Settings | File Templates.
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("RequestID", fRequestID, 64);
		writer.writeInt("StatusCode", StatusCode.convertToInt(fStatusCode));
		writer.writeString("StatusMessage", fStatusMessage, 1024);
		writer.writeObject("ResponseData", fResponseData);
	}
}
