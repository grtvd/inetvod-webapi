/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.RentedShow;

public class RentedShowResp implements Writeable
{
	/* Fields */
	protected RentedShow fRentedShow;

	/* Getters and Setters */
	public void setRentedShow(RentedShow rentedShow) { fRentedShow = rentedShow; }

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeObject("RentedShow", fRentedShow);
	}
}