/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
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
