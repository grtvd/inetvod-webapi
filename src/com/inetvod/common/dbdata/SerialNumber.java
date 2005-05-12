package com.inetvod.common.dbdata;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.DataExists;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Bob
 * Date: Jul 6, 2004
 * Time: 11:43:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class SerialNumber extends DatabaseObject
{
	/* Fields */
	protected String fSerialNumberID;
	protected boolean fActive;
	protected MemberID fMemberID;
	protected String fPIN;

	private static DatabaseAdaptor fDatabaseAdaptor = DatabaseAdaptor.newInstance(SerialNumber.class, ArrayList.class, 4);
	public static DatabaseAdaptor getDatabaseAdaptor() { return fDatabaseAdaptor; }

	/* Getters and Setters */
	public String getSerialNumberID() { return fSerialNumberID; }

	public boolean getActive() { return fActive; }

	public MemberID getMemberID() { return fMemberID; }

	public String getPIN() { return fPIN; }
	public void setPIN(String PIN) { fPIN = PIN; }

	/* Constuction Methods */
	public SerialNumber(DataReader reader) throws Exception
	{
		super(reader);
		readFrom(reader);
	}

	protected static SerialNumber load(String serialNumberID, DataExists exists) throws Exception
	{
		return (SerialNumber)fDatabaseAdaptor.selectByKey(serialNumberID, exists);
	}

	public static SerialNumber get(String serialNumberID) throws Exception
	{
		return load(serialNumberID, DataExists.MustExist);
	}

	public static SerialNumber find(String serialNumberID) throws Exception
	{
		return load(serialNumberID, DataExists.MayNotExist);
	}

	/* Activation */
	public void SetActive(MemberID memberID, String pin)
	{
		fMemberID = memberID;
		fActive = true;
		fPIN = pin;
	}

	public void SetInactive()
	{
		fMemberID = null;
		fActive = false;
		fPIN = null;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fSerialNumberID = reader.readString("SerialNumberID", 64);
		fActive = reader.readBooleanValue("Active");
		fMemberID = (MemberID)reader.readDataID("MemberID", MemberID.MaxLength, MemberID.CtorString);
		fPIN = reader.readString("PIN", 16);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("SerialNumberID", fSerialNumberID, 64);
		writer.writeBooleanValue("Active", fActive);
		writer.writeDataID("MemberID", fMemberID, MemberID.MaxLength);
		writer.writeString("PIN", fPIN, 16);
	}

	public void update() throws Exception
	{
		fDatabaseAdaptor.update(this);
	}

	public void delete() throws Exception
	{
		fDatabaseAdaptor.delete(fSerialNumberID);
	}
}
