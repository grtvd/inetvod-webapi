/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import java.util.Date;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.CategoryID;
import com.inetvod.common.data.CategoryIDList;
import com.inetvod.common.data.RatingID;
import com.inetvod.common.data.ShowID;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowCategoryList;

public class ShowDetail implements Writeable
{
	/* Fields */
	protected ShowID fShowID;
	protected String fName;
	protected String fEpisodeName;
	protected String fEpisodeNumber;
	protected Date fReleasedOn;
	protected Short fReleasedYear;
	protected String fDescription;
	protected Short fRunningMins;
	protected String fPictureURL;

	protected CategoryIDList fCategoryIDList;
	protected RatingID fRatingID;
	protected Boolean fIsAdult;

	protected ShowProviderList fShowProviderList;

	/* Constuction Methods */
	public ShowDetail(Show show, ShowProviderList showProviderList,
		ShowCategoryList showCategoryList)
	{
		fShowID = show.getShowID();
		fName = show.getName();
		fEpisodeName = show.getEpisodeName();
		fEpisodeNumber = show.getEpisodeNumber();
		fReleasedOn = show.getReleasedOn();
		fReleasedYear = show.getReleasedYear();
		fDescription = show.getDescription();
		fRunningMins = show.getRunningMins();
		fPictureURL = show.getPictureURL();

		fCategoryIDList = showCategoryList.getCategoryIDList();
		fRatingID = show.getRatingID();
		fIsAdult = show.getIsAdult();

		fShowProviderList = showProviderList;
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeString("Name", fName, Show.NameMaxLength);
		writer.writeString("EpisodeName", fEpisodeName, Show.EpisodeNameMaxLength);
		writer.writeString("EpisodeNumber", fEpisodeNumber, Show.EpisodeNumberMaxLength);

		writer.writeDate("ReleasedOn", fReleasedOn);
		writer.writeShort("ReleasedYear", fReleasedYear);
		writer.writeString("Description", fDescription, Show.DescriptionMaxLength);

		writer.writeShort("RunningMins", fRunningMins);
		writer.writeString("PictureURL", fPictureURL, Show.PictureURLMaxLength);

		writer.writeStringList("CategoryID", fCategoryIDList, CategoryID.MaxLength);
		writer.writeDataID("RatingID", fRatingID, RatingID.MaxLength);
		writer.writeBoolean("IsAdult", fIsAdult);

		writer.writeList("ShowProvider", fShowProviderList);
	}
}
