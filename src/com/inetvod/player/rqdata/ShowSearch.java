package com.inetvod.player.rqdata;

import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowID;
import com.inetvod.common.dbdata.ShowProviderList;

public class ShowSearch implements Writeable
{
	/* Fields */
	protected ShowID fShowID;
	protected String fName;
	protected String fEpisodeName;
	protected Short fReleasedYear;
	protected ShowProviderList fShowProviderList = new ShowProviderList();

	/* Getters and Setters */
	public ShowID getShowID() { return fShowID; }
	public String getName() { return fName; }
	public String getEpisodeName() { return fEpisodeName; }
	public Short getReleasedYear() { return fReleasedYear; }
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
		fReleasedYear = show.getReleasedYear();
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeString("Name", fName, 64);
		writer.writeString("EpisodeName", fEpisodeName, 64);
		writer.writeShort("ReleasedYear", fReleasedYear);
		writer.writeList("ShowProvider", fShowProviderList);
	}
}