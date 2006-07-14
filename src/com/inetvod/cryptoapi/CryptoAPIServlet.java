/**
 * Copyright © 2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.cryptoapi;

import java.io.IOException;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inetvod.common.crypto.CryptoDigest;

public class CryptoAPIServlet extends HttpServlet
{
	private static final int RQST_METHOD_POS = 3;
	private static final int RQST_ARGS_POS = 4;
	private static final int RQST_PARTS = 5;

	private static final String DIGEST_METHOD = "digest";

	public void init() throws ServletException
	{
		// this code assumes PlayerXmlServlet is initalized everything
	}

	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
		throws ServletException, IOException
	{
		try
		{
			Method requestMethod = getRequestMethod(httpServletRequest.getRequestURL().toString());
			String result = null;

			if(DIGEST_METHOD.equals(requestMethod.getName()))
			{
				result = digest(requestMethod.getArgs());
			}
			else
				throw new Exception("Unknown request");

			sendResult(httpServletResponse, result);
		}
		catch(Exception e)
		{
			httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private Method getRequestMethod(String requestURL) throws Exception
	{
		String[] parts = (new URL(requestURL)).getPath().split("/");

		if(parts.length != RQST_PARTS)
			throw new Exception(String.format("Cannot parse request, parts != %d", RQST_PARTS));

		return new Method(parts[RQST_METHOD_POS], parts[RQST_ARGS_POS]);
	}

	private void sendResult(HttpServletResponse httpServletResponse, String result) throws IOException
	{
		httpServletResponse.getWriter().write(result);
	}

	private String digest(String data) throws Exception
	{
		return CryptoDigest.encrypt(data);
	}

	private static class Method
	{
		private String fName;
		private String fArgs;

		public String getName() { return fName; }
		public String getArgs() { return fArgs; }

		public Method(String name, String args)
		{
			fName = name;
			fArgs = args;
		}
	}
}
