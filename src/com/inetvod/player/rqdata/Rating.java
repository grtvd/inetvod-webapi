/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.RatingID;

public class Rating implements Writeable
{
	/* Fields */
	private RatingID fRatingID;
	private String fName;

	/* Construction */
	public Rating(com.inetvod.common.dbdata.Rating rating)
	{
		fRatingID = rating.getRatingID();
		fName = rating.getName();
	}

	/* Implementation */
	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RatingID", fRatingID, RatingID.MaxLength);
		writer.writeString("Name", fName, com.inetvod.common.dbdata.Rating.NameMaxLength);
	}
}
