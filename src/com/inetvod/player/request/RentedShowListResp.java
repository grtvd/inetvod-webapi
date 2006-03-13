/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.RentedShowSearchList;

public class RentedShowListResp implements Writeable
{
	/* Fields */
	protected RentedShowSearchList fRentedShowSearchList = new RentedShowSearchList();

	/* Getters and Setters */
	public RentedShowSearchList getRentedShowSearchList() { return fRentedShowSearchList; }

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeList("RentedShowSearch", fRentedShowSearchList);
	}
}