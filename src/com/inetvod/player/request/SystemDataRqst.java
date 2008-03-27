/**
 * Copyright © 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.CategoryList;
import com.inetvod.player.rqdata.ProviderList;
import com.inetvod.player.rqdata.RatingList;
import com.inetvod.player.rqdata.StatusCode;

public class SystemDataRqst extends SessionRequestable
{
	/* Constuction Methods */
	public SystemDataRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	protected boolean areGuestsAllowedForRequest() { return true; }

	public Writeable fulfillRequest() throws Exception
	{
		SystemDataResp response = new SystemDataResp();

		if (fMemberSession.getShowAdult())
		{
			response.ProviderList = ProviderList.newInstance(com.inetvod.common.dbdata.ProviderList.find());
			response.CategoryList = CategoryList.newInstance(com.inetvod.common.dbdata.CategoryList.find());
		}
		else
		{
			response.ProviderList = ProviderList.newInstance(com.inetvod.common.dbdata.ProviderList.findByNoAdult());
			response.CategoryList = CategoryList.newInstance(com.inetvod.common.dbdata.CategoryList.findByNoAdult());
		}
		response.RatingList = RatingList.newInstance(com.inetvod.common.dbdata.RatingList.find());

		fStatusCode = StatusCode.sc_Success;

		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		// no members
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		// no members
	}
}
