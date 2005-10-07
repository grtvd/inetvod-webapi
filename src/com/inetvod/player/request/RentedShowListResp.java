/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
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