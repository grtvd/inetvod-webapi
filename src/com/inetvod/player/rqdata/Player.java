/**
 * Copyright © 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ManufacturerID;

public class Player implements Readable, Writeable
{
	/* Constants */
	public static final Constructor<Player> CtorDataReader = DataReader.getCtor(Player.class);
	private static final int ModelNoMaxLength = com.inetvod.common.dbdata.Player.ModelNoMaxLength;
	public static final int SerialNoMaxLength = com.inetvod.common.dbdata.Player.SerialNoMaxLength;
	public static final int VersionMaxLength = com.inetvod.common.dbdata.Player.VersionMaxLength;

	/* Fields */
	protected ManufacturerID fManufacturerID;
	protected String fModelNo;
	protected String fSerialNo;
	protected String fVersion;

	/* Getters and Setters */
	public ManufacturerID getManufacturerID() { return fManufacturerID; }
	public String getModelNo() { return fModelNo; }
	public String getSerialNo() { return fSerialNo; }
	public String getVersion() { return fVersion; }

	/* Construction */
	public Player(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */
	public void readFrom(DataReader reader) throws Exception
	{
		fManufacturerID = reader.readDataID("ManufacturerID", ManufacturerID.MaxLength, ManufacturerID.CtorString);
		fModelNo = reader.readString("ModelNo", ModelNoMaxLength);
		fSerialNo = reader.readString("SerialNo", SerialNoMaxLength);
		fVersion = reader.readString("Version", VersionMaxLength);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ManufacturerID", fManufacturerID, ManufacturerID.MaxLength);
		writer.writeString("ModelNo", fModelNo, ModelNoMaxLength);
		writer.writeString("SerialNo", fSerialNo, SerialNoMaxLength);
		writer.writeString("Version", fVersion, VersionMaxLength);
	}
}
