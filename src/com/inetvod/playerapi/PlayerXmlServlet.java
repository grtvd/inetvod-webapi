/**
 * Copyright © 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.playerapi;

import java.io.IOException;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inetvod.common.core.AppProperties;
import com.inetvod.common.core.Logger;
import com.inetvod.common.core.Mailer;
import com.inetvod.common.crypto.CryptoKeyStore;
import com.inetvod.common.dbdata.Category;
import com.inetvod.common.dbdata.DatabaseAdaptor;
import com.inetvod.common.dbdata.Member;
import com.inetvod.common.dbdata.MemberAccount;
import com.inetvod.common.dbdata.MemberLogon;
import com.inetvod.common.dbdata.MemberPrefs;
import com.inetvod.common.dbdata.MemberProvider;
import com.inetvod.common.dbdata.MemberSession;
import com.inetvod.common.dbdata.PlayerManager;
import com.inetvod.common.dbdata.Provider;
import com.inetvod.common.dbdata.ProviderConnection;
import com.inetvod.common.dbdata.Rating;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowCategory;
import com.inetvod.common.dbdata.ShowProvider;
import com.inetvod.common.web.ServletFulfiller;
import com.inetvod.player.request.PlayerServletFulfiller;

public class PlayerXmlServlet extends HttpServlet
{
	@Override
	public void init() throws ServletException
	{
		try
		{
			// load app properties
			AppProperties.initialize(getServletContext().getRealPath("/webapi.xml"));

			// set the log file
			Logger.initialize(getServletContext().getRealPath("/log4j.xml"),
				getServletContext().getInitParameter("logdir"));

			// setup db connection
			DatabaseAdaptor.setDBConnectFile(getServletContext().getInitParameter("dbconnect"));

			// init the CryptoKeyStore
			CryptoKeyStore.load(getServletContext().getInitParameter("cryptokeystore"));

			// load the PlayerManager
			PlayerManager.load(getServletContext().getInitParameter("playerdata"));

			// init Mailer
			Mailer.initialize();

			// prime UUID, first hit is big
			UUID.randomUUID();
		}
		catch(Exception e)
		{
			throw new ServletException(e.getMessage(), e);
		}

		// Preload DatabaseAdaptors
		Provider.getDatabaseAdaptor();
		ProviderConnection.getDatabaseAdaptor();
		Category.getDatabaseAdaptor();
		Rating.getDatabaseAdaptor();
		Member.getDatabaseAdaptor();
		MemberLogon.getDatabaseAdaptor();
		MemberAccount.getDatabaseAdaptor();
		MemberPrefs.getDatabaseAdaptor();
		MemberSession.getDatabaseAdaptor();
		MemberProvider.getDatabaseAdaptor();
		Show.getDatabaseAdaptor();
		ShowProvider.getDatabaseAdaptor();
		ShowCategory.getDatabaseAdaptor();
		RentedShow.getDatabaseAdaptor();
	}

	@Override
	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
		throws ServletException, IOException
	{
	}

	@Override
	protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
		throws ServletException, IOException
	{
		try
		{
			ServletFulfiller fulfiller = new PlayerServletFulfiller(httpServletRequest, httpServletResponse);
			fulfiller.fulfill();
		}
		catch(Exception ignore)
		{
			//throw new ServletException(e);
		}
	}
}
