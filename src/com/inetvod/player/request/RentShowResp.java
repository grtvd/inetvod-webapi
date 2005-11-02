/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.RentedShowID;
import com.inetvod.player.rqdata.License;

public class RentShowResp implements Writeable
{
	protected RentedShowID fRentedShowID;
	protected License fLicense;

	public RentedShowID getRentedShowID() { return fRentedShowID; }
	public void setRentedShowID(RentedShowID rentedShowID) { fRentedShowID = rentedShowID; }

	public License getLicense() { return fLicense; }
	public void setLicense(License license) { fLicense = license; }

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RentedShowID", fRentedShowID, RentedShowID.MaxLength);
		writer.writeObject("License", fLicense);
	}
}
