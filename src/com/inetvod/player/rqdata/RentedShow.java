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
import com.inetvod.common.dbdata.RentedShowID;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowCategoryList;
import com.inetvod.common.dbdata.ShowCost;
import com.inetvod.common.dbdata.ShowID;

public class RentedShow implements Writeable
{
	/* Fields */
	protected RentedShowID fRentedShowID;

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

	protected ShowCost fShowCost = new ShowCost();
	public Date fRentedOn;
	public Date fAvailableUntil;

	/* Getters and Setters */

	/* Constuction Methods */
	public RentedShow(com.inetvod.common.dbdata.RentedShow rentedShow, Show show,
		ShowCategoryList showCategoryList) throws Exception
	{
		if(!rentedShow.getShowID().equals(show.getShowID()))
			throw new Exception("ShowID mismatch");

		fRentedShowID = rentedShow.getRentedShowID();
		fShowID = rentedShow.getShowID();
		fProviderID = rentedShow.getProviderID();
		fName = show.getName();
		fEpisodeName = show.getEpisodeName();
		fEpisodeNumber = show.getEpisodeNumber();
		fReleasedOn = show.getReleasedOn();
		fReleasedYear = show.getReleasedYear();
		fDescription	= show.getDescription();
		fRunningMins = show.getRunningMins();
		fPictureURL = show.getPictureURL();
		fCategoryIDList = showCategoryList.getCategoryIDList();
		fRatingID = show.getRatingID();
		fIsAdult = show.getIsAdult();

		fShowCost = rentedShow.getShowCost();
		fRentedOn = rentedShow.getRentedOn();
		fAvailableUntil = rentedShow.getAvailableUntil();
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RentedShowID", fRentedShowID, RentedShowID.MaxLength);

		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeString("Name", fName, Show.NameMaxLength);
		writer.writeString("EpisodeName", fEpisodeName, Show.EpisodeNameMaxLength);
		writer.writeString("EpisodeNumber", fEpisodeNumber, Show.EpisodeNumberMaxLength);

		writer.writeDate("ReleasedOn", fReleasedOn);
		writer.writeShort("ReleasedYear", fReleasedYear);
		writer.writeString("Description", fDescription, Short.MAX_VALUE);

		writer.writeShort("RunningMins", fRunningMins);
		writer.writeString("PictureURL", fPictureURL, Show.PictureURLMaxLength);

		writer.writeStringList("CategoryID", fCategoryIDList, CategoryID.MaxLength);
		writer.writeDataID("RatingID", fRatingID, RatingID.MaxLength);
		writer.writeBoolean("IsAdult", fIsAdult);

		writer.writeObject("ShowCost", fShowCost);
		writer.writeDateTime("RentedOn", fRentedOn);
		writer.writeDateTime("AvailableUntil", fAvailableUntil);
	}
}
