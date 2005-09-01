/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

import java.util.Date;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.CategoryID;
import com.inetvod.common.dbdata.CategoryIDList;
import com.inetvod.common.dbdata.ProviderID;
import com.inetvod.common.dbdata.RatingID;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowCategoryList;
import com.inetvod.common.dbdata.ShowCost;
import com.inetvod.common.dbdata.ShowID;
import com.inetvod.common.dbdata.ShowProvider;

public class ShowDetail implements Writeable
{
	/* Fields */
	protected ShowID fShowID;
	protected ProviderID fProviderID;
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

	protected ShowCost fShowCost;
	protected Short fRentalHours;

	/* Constuction Methods */
	public ShowDetail(Show show, ShowProvider showProvider,
		ShowCategoryList showCategoryList)
	{
		fShowID = show.getShowID();
		fProviderID = showProvider.getProviderID();
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

		fShowCost = showProvider.getShowCost();
		fRentalHours = showProvider.getRentalHours();
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
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

		writer.writeObject("ShowCost", fShowCost);
		writer.writeShort("RentalHours", fRentalHours);
	}
}
