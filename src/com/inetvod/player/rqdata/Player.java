/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.dbdata.ManufacturerID;

import java.lang.reflect.Constructor;

public class Player implements Readable, Writeable
{
	public static final Constructor CtorDataFiler = DataReader.getCtor(Player.class);

	/* Fields */
	protected ManufacturerID fManufacturerID;
	protected String fModelNo;
	protected String fSerialNo;
	protected String fVersion;

	public Player(DataReader filer) throws Exception
	{
		readFrom(filer);
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fManufacturerID = (ManufacturerID)reader.readDataID("ManufacturerID", ManufacturerID.MaxLength,
			ManufacturerID.CtorString);
		fModelNo = reader.readString("ModelNo", 32);
		fSerialNo = reader.readString("SerialNo", 64);
		fVersion = reader.readString("Version", 16);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ManufacturerID", fManufacturerID, ManufacturerID.MaxLength);
		writer.writeString("ModelNo", fModelNo, 32);
		writer.writeString("SerialNo", fSerialNo, 64);
		writer.writeString("Version", fVersion, 16);
	}
}
