/**
 * Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.RentedShowID;
import com.inetvod.common.dbdata.RentedShow;

public class DownloadShow implements Writeable
{
	/* Constants */
	public static final int ShowURLMaxLength = 4096;

	/* Fields */
	protected RentedShowID fRentedShowID;
	protected String fShowURL;

	/* Construction */
	public DownloadShow(RentedShow rentedShow)
	{
		fRentedShowID = rentedShow.getRentedShowID();
		fShowURL = rentedShow.getShowURL();
	}

	/* Implementation */
	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RentedShowID", fRentedShowID, RentedShowID.MaxLength);
		writer.writeString("ShowURL", fShowURL, ShowURLMaxLength);
	}
}
