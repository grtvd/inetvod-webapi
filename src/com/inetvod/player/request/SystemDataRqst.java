/**
 * Copyright � 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.CategoryList;
import com.inetvod.common.dbdata.RatingList;
import com.inetvod.player.rqdata.ProviderList;
import com.inetvod.player.rqdata.StatusCode;

public class SystemDataRqst extends SessionRequestable
{
	public SystemDataRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		SystemDataResp response = new SystemDataResp();

		response.ProviderList = ProviderList.newInstance(com.inetvod.common.dbdata.ProviderList.find());
		response.CategoryList = CategoryList.find();
		response.RatingList = RatingList.find();

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
