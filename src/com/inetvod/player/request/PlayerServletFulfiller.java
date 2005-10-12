/**
 * Copyright © 2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inetvod.common.core.DataFormat;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Requestable;
import com.inetvod.common.core.ServletFulfiller;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.StatusCode;

public class PlayerServletFulfiller extends ServletFulfiller
{
	public PlayerServletFulfiller(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	{
		super(httpServletRequest, httpServletResponse);
	}

	public DataFormat getRequestDataFormat()
	{
		if(this.fHttpServletRequest.getRequestURL().toString().toLowerCase().endsWith("/xml"))
			return DataFormat.XML;
		return DataFormat.Binary;
	}

	protected Writeable createResponseFromException(Requestable requestable, Exception e)
	{
		INetVODPlayerResp response = new INetVODPlayerResp();

		if((requestable != null) && (requestable instanceof INetVODPlayerRqst))
			response.setRequestID(((INetVODPlayerRqst)requestable).getRequestID());

		response.setStatusCode(StatusCode.sc_GeneralError);

		return response;
	}

	/// <summary>
	/// Read a Requestable object from its name
	/// </summary>
	/// <param name="className"></param>
	/// <returns></returns>
	protected Requestable readRequestableFromReader(DataReader dataReader) throws Exception
	{
		return dataReader.readObject("INetVODPlayerRqst", INetVODPlayerRqst.CtorDataReader);
	}

	protected String getRequestType(Requestable requestable)
	{
		String requestType = null;

		if(requestable instanceof INetVODPlayerRqst)
		{
			INetVODPlayerRqst iNetVODPlayerRqst = (INetVODPlayerRqst)requestable;
			if(iNetVODPlayerRqst.getRequestData() != null)
				requestType = iNetVODPlayerRqst.getRequestData().getRequestType();
		}

		return requestType;
	}
}
