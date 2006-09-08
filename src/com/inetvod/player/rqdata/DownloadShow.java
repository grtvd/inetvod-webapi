/**
 * Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.data.RentedShowID;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.Show;

public class DownloadShow implements Writeable
{
	/* Constants */
	public static final int ShowURLMaxLength = 4096;
	private static final int DataFileNameMaxLength = 128;

	/* Fields */
	protected RentedShowID fRentedShowID;
	protected String fShowURL;
	protected String fDataFileName;

	/* Construction */
	public DownloadShow(RentedShow rentedShow, Show show)
	{
		fRentedShowID = rentedShow.getRentedShowID();
		fShowURL = rentedShow.getShowURL();
		fDataFileName = show.getName();
		if(StrUtil.hasLen(show.getEpisodeName()))
			fDataFileName += String.format(" - ''%s''", show.getEpisodeName());
	}

	/* Implementation */
	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RentedShowID", fRentedShowID, RentedShowID.MaxLength);
		writer.writeString("ShowURL", fShowURL, ShowURLMaxLength);
		writer.writeString("DataFileName", fDataFileName, DataFileNameMaxLength);
	}
}
