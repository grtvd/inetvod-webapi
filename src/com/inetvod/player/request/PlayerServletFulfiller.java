/**
 * Copyright © 2005-2009 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inetvod.common.core.DataFormat;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Requestable;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.web.ServletFulfiller;
import com.inetvod.player.rqdata.StatusCode;

public class PlayerServletFulfiller extends ServletFulfiller
{
	public PlayerServletFulfiller(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	{
		super(httpServletRequest, httpServletResponse);
	}

	@Override
	public DataFormat getRequestDataFormat()
	{
		if(this.fHttpServletRequest.getRequestURL().toString().toLowerCase().endsWith("/xml"))
			return DataFormat.XML;
		return DataFormat.Binary;
	}

	@Override
	protected Writeable createResponseFromException(Requestable requestable, Exception e)
	{
		PlayerResp response = new PlayerResp();

		response.setStatusCode(StatusCode.sc_GeneralError);

		return response;
	}

	@Override
	protected Requestable readRequestableFromReader(DataReader dataReader) throws Exception
	{
		PlayerRqst playerRqst = dataReader.readObject("PlayerRqst", PlayerRqst.CtorDataReader);
		playerRqst.readRequestData(fHttpServletRequest);
		return playerRqst;
	}

	@Override
	protected String getRequestType(Requestable requestable)
	{
		String requestType = null;

		if(requestable instanceof PlayerRqst)
		{
			PlayerRqst playerRqst = (PlayerRqst)requestable;
			if(playerRqst.getRequestData() != null)
				requestType = playerRqst.getRequestData().getRequestType();
		}

		return requestType;
	}
}
