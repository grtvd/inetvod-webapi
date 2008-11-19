/**
 * Copyright © 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import java.util.Date;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ShowID;
import com.inetvod.common.dbdata.Show;

public class ShowSearch implements Writeable
{
	/* Fields */
	protected ShowID fShowID;
	protected String fName;
	protected String fEpisodeName;
	protected Date fReleasedOn;
	protected Short fReleasedYear;
	protected String fPictureURL;
	protected ShowProviderList fShowProviderList = new ShowProviderList();

	/* Getters and Setters */
	public ShowID getShowID() { return fShowID; }
	public String getName() { return fName; }
	public String getEpisodeName() { return fEpisodeName; }
	public Short getReleasedYear() { return fReleasedYear; }
	public String getPictureURL() { return fPictureURL; }
	public ShowProviderList getShowProviderList() { return fShowProviderList; }

	/* Constuction Methods */
	public ShowSearch()
	{
	}

	public ShowSearch(Show show)
	{
		fShowID = show.getShowID();
		fName = show.getName();
		fEpisodeName = show.getEpisodeName();
		fReleasedOn = show.getReleasedOn();
		fReleasedYear = show.getReleasedYear();
		fPictureURL = show.getPictureURL();
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeString("Name", fName, Show.NameMaxLength);
		writer.writeString("EpisodeName", fEpisodeName, Show.EpisodeNameMaxLength);
		writer.writeDateTime("ReleasedOn", fReleasedOn);
		writer.writeShort("ReleasedYear", fReleasedYear);
		writer.writeString("PictureURL", fPictureURL, Show.PictureURLMaxLength);
		writer.writeList("ShowProvider", fShowProviderList);
	}
}