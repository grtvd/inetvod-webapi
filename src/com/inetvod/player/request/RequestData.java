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

public class RequestData implements Requestable
{
	/* Constants */
	public static final Constructor CtorDataFiler = DataReader.getCtor(RequestData.class);
	public static final int RequestTypeMaxLength = 64;

	/* Properties */
	protected String fRequestType;
	protected Requestable fRequest;

	protected StatusCode fStatusCode = StatusCode.sc_GeneralError;
	public StatusCode getStatusCode() { return fStatusCode; }

	public RequestData(DataReader filer) throws Exception
	{
		readFrom(filer);
	}

	public void setRequest(String version, String requestID, SessionData sessionData) throws Exception
	{
		if(fRequest instanceof SessionRequestable)
			((SessionRequestable)fRequest).setRequest(version, requestID, sessionData);
	}

	public Writeable fulfillRequest() throws Exception
	{
		if(fRequest == null)
		{
			fStatusCode = StatusCode.sc_GeneralError;
			return null;
		}

		Writeable response = fRequest.fulfillRequest();
		ResponseData responseData = null;

		fStatusCode = fRequest.getStatusCode();

		if(response != null)
			responseData = new ResponseData(response);

		return responseData;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fRequestType = reader.readString("RequestType", RequestTypeMaxLength);

		Class cl = Class.forName(getClass().getPackage().getName() + "." + fRequestType);
		Constructor ctor = cl.getConstructor(new Class[] { DataReader.class });
		fRequest = (Requestable)reader.readObject(fRequestType, ctor);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("RequestType", fRequestType, RequestTypeMaxLength);
		writer.writeObject(fRequestType, fRequest);
	}
}
