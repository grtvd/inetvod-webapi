/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.playerapi;

import com.inetvod.player.request.PlayerServletFulfiller;
import com.inetvod.common.core.ServletFulfiller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlayerXmlServlet extends HttpServlet
{
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
		throws ServletException, IOException
	{
	}

	protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
		throws ServletException, IOException
	{
		try
		{
			ServletFulfiller fulfiller = new PlayerServletFulfiller(httpServletRequest, httpServletResponse);
			fulfiller.fulfill();
		}
		catch(Exception e)
		{
			//throw new ServletException(e);
		}
	}
}
