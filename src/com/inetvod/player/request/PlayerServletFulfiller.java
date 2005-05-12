/**
 * Copyright © 2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Requestable;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.core.ServletFulfiller;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.DataFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlayerServletFulfiller extends ServletFulfiller
{
	public PlayerServletFulfiller(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	{
		super(httpServletRequest, httpServletResponse);
	}

	public DataFormat getRequestDataFormat()
	{
		return DataFormat.Binary;	//TODO: base format on URL
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
	protected Requestable readRequestableFromReader(DataReader dataFiler) throws Exception
	{
		return (Requestable)dataFiler.readObject("INetVODPlayerRqst", INetVODPlayerRqst.CtorDataFiler);
	}
}
