/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

import java.util.Date;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.RentedShowID;
import com.inetvod.common.data.ShowID;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.Show;

public class RentedShowSearch implements Writeable
{
	/* Fields */
	protected RentedShowID fRentedShowID;

	protected ShowID fShowID;
	protected ProviderID fProviderID;
	protected String fName;
	protected String fEpisodeName;

	public Date fAvailableUntil;

	/* Getters and Setters */

	/* Constuction Methods */
	public RentedShowSearch(RentedShow rentedShow, Show show) throws Exception
	{
		if(!rentedShow.getShowID().equals(show.getShowID()))
			throw new Exception("ShowID mismatch");

		fRentedShowID = rentedShow.getRentedShowID();

		fShowID = rentedShow.getShowID();
		fProviderID = rentedShow.getProviderID();
		fName = show.getName();
		fEpisodeName = show.getEpisodeName();

		fAvailableUntil = rentedShow.getAvailableUntil();
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RentedShowID", fRentedShowID, RentedShowID.MaxLength);

		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeString("Name", fName, Show.NameMaxLength);
		writer.writeString("EpisodeName", fEpisodeName, Show.EpisodeNameMaxLength);

		writer.writeDateTime("AvailableUntil", fAvailableUntil);
	}
}
