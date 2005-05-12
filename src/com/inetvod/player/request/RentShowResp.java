/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.RentedShowID;
import com.inetvod.common.dbdata.Show;

public class RentShowResp implements Writeable
{
	protected RentedShowID fRentedShowID;
	protected String fShowURL;

	public RentedShowID getRentedShowID() { return fRentedShowID; }
	public void setRentedShowID(RentedShowID rentedShowID) { fRentedShowID = rentedShowID; }

	public String getShowURL() { return fShowURL; }
	public void setShowURL(String showURL) { fShowURL = showURL; }

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RentedShowID", fRentedShowID, RentedShowID.MaxLength);
		writer.writeString("ShowURL", fShowURL, Show.ShowURLMaxLength);
	}
}
