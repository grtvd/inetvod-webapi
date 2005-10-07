/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.PlayerRequestable;
import com.inetvod.player.rqdata.StatusCode;

public class RequestData implements PlayerRequestable
{
	/* Constants */
	public static final Constructor<RequestData> CtorDataFiler = DataReader.getCtor(RequestData.class);
	private static final int RequestTypeMaxLength = 64;

	/* Properties */
	private String fRequestType;
	public String getRequestType() { return fRequestType; }

	private PlayerRequestable fRequest;

	protected StatusCode fStatusCode = StatusCode.sc_GeneralError;
	public StatusCode getStatusCode() { return fStatusCode; }

	public RequestData(DataReader filer) throws Exception
	{
		readFrom(filer);
	}

	public StatusCode setRequest(String version, String requestID, SessionData sessionData) throws Exception
	{
		if(fRequest instanceof SessionRequestable)
			return ((SessionRequestable)fRequest).setRequest(version, requestID, sessionData);

		return StatusCode.sc_Success;
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

	@SuppressWarnings({"unchecked"})
	public void readFrom(DataReader reader) throws Exception
	{
		fRequestType = reader.readString("RequestType", RequestTypeMaxLength);

		Class<PlayerRequestable> cl = (Class<PlayerRequestable>)Class.forName(getClass().getPackage().getName() + "." + fRequestType);
		Constructor<PlayerRequestable> ctor = cl.getConstructor(new Class[] { DataReader.class });
		fRequest = reader.readObject(fRequestType, ctor);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("RequestType", fRequestType, RequestTypeMaxLength);
		writer.writeObject(fRequestType, fRequest);
	}
}
