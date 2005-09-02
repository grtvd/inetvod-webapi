/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.RentedShowID;
import com.inetvod.player.rqdata.License;
import com.inetvod.player.rqdata.LicenseMethod;

public class WatchShowRqst extends SessionRequestable
{
	/* Fields */
	protected RentedShowID fRentedShowID;

	/* Constuction Methods */
	public WatchShowRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		WatchShowResp response;
		RentedShow rentedShow;

		response = new WatchShowResp();

		rentedShow = RentedShow.get(fRentedShowID);
		//TODO: fetch this from Provider API
		License license = new License();
		license.setLicenseMethod(LicenseMethod.URLOnly);
		license.setShowURL(rentedShow.getShowURL());
		response.setLicense(license);
		//TODO: update rentedShow.setAvailableUntil(); from provider
		//TODO: fetch this from Provider API

		fStatusCode = StatusCode.sc_Success;
		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fRentedShowID = (RentedShowID)reader.readDataID("RentedShowID", RentedShowID.MaxLength, RentedShowID.CtorString);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RentedShowID", fRentedShowID, RentedShowID.MaxLength);
	}
}
