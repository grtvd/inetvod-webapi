/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import java.util.Date;

import com.inetvod.common.core.DataExists;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;

public class RentedShow extends DatabaseObject
{
	/* Constants */
	public static final int NumFields = 12;

	/* Fields */
	protected RentedShowID fRentedShowID;
	protected MemberID fMemberID;
	protected ShowID fShowID;
	protected ProviderID fProviderID;

	protected String fShowURL;
	protected ShowCost fShowCost;
	protected Date fRentedOn;
	protected Date fAvailableUntil;

	private static DatabaseAdaptor<RentedShow, RentedShowList> fDatabaseAdaptor =
		new DatabaseAdaptor<RentedShow, RentedShowList>(RentedShow.class, RentedShowList.class, NumFields);
	public static DatabaseAdaptor<RentedShow, RentedShowList> getDatabaseAdaptor() { return fDatabaseAdaptor; }

	/* Getters and Setters */
	public RentedShowID getRentedShowID() { return fRentedShowID; }

	public MemberID getMemberID() { return fMemberID; }
	public ShowID getShowID() { return fShowID; }
	public ProviderID getProviderID() { return fProviderID; }

	public String getShowURL() { return fShowURL; }
	public void setShowURL(String showURL) { fShowURL = showURL; }

	public ShowCost getShowCost() { return fShowCost; }
	public void setShowCost(ShowCost showCost) { fShowCost = showCost; }

	public Date getRentedOn() { return fRentedOn; }
	public void setRentedOn(Date rentedOn) { fRentedOn = rentedOn; }

	public Date getAvailableUntil() { return fAvailableUntil; }
	public void setAvailableUntil(Date availableUntil) { fAvailableUntil = availableUntil; }

	/* Constuction Methods */
	public RentedShow(Member member, Show show, Provider provider)
	{
		super(true);
		fRentedShowID = RentedShowID.newInstance();
		fMemberID = member.getMemberID();
		fShowID = show.getShowID();
		fProviderID = provider.getProviderID();
	}

	public RentedShow(DataReader reader) throws Exception
	{
		super(reader);
		readFrom(reader);
	}

	public static RentedShow newInstance(Member member, Show show, Provider provider)
	{
		return new RentedShow(member, show, provider);
	}

	protected static RentedShow load(RentedShowID rentedShowID, DataExists exists) throws Exception
	{
		return fDatabaseAdaptor.selectByKey(rentedShowID, exists);
	}

	public static RentedShow get(RentedShowID rentedShowID) throws Exception
	{
		return load(rentedShowID, DataExists.MustExist);
	}

	/* DatabaseObject Members */
	public void readFrom(DataReader reader) throws Exception
	{
		fRentedShowID = (RentedShowID)reader.readDataID("RentedShowID", RentedShowID.MaxLength, RentedShowID.CtorString);
		fMemberID = (MemberID)reader.readDataID("MemberID", MemberID.MaxLength, MemberID.CtorString);
		fShowID = (ShowID)reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fProviderID = (ProviderID)reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);

		fShowURL = reader.readString("ShowURL", Show.ShowURLMaxLength);
		fShowCost = reader.readObject("ShowCost", ShowCost.CtorDataReader);
		fRentedOn = reader.readDateTime("RentedOn");
		fAvailableUntil = reader.readDateTime("AvailableUntil");
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RentedShowID", fRentedShowID, RentedShowID.MaxLength);
		writer.writeDataID("MemberID", fMemberID, MemberID.MaxLength);
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);

		writer.writeString("ShowURL", fShowURL, Show.ShowURLMaxLength);
		writer.writeObject("ShowCost", fShowCost);
		writer.writeDateTime("RentedOn", fRentedOn);
		writer.writeDateTime("AvailableUntil", fAvailableUntil);
	}

	public void update() throws Exception
	{
		fDatabaseAdaptor.update(this);
	}

	public void delete() throws Exception
	{
		fDatabaseAdaptor.delete(fRentedShowID);
	}

	static public void delete(RentedShowID rentedShowID) throws Exception
	{
		fDatabaseAdaptor.delete(rentedShowID);
	}
}
