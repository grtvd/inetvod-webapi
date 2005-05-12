package com.inetvod.common.dbdata;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.DataExists;

/**
 * Created by IntelliJ IDEA.
 * User: Bob
 * Date: Jul 7, 2004
 * Time: 9:52:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class Rating extends DatabaseObject
{
	/* Properties */
	RatingID fRatingID;
	String fName;

	private static DatabaseAdaptor fDatabaseAdaptor = DatabaseAdaptor.newInstance(Rating.class, RatingList.class, 2);
	public static DatabaseAdaptor getDatabaseAdaptor() { return fDatabaseAdaptor; }

	/* Getters and Setters */


	/* Constuction Methods */
	public Rating(DataReader reader) throws Exception
	{
		super(reader);
		readFrom(reader);
	}

	protected static Rating load(RatingID ratingID, DataExists exists) throws Exception
	{
		return (Rating)fDatabaseAdaptor.selectByKey(ratingID, exists);
	}

	public static Rating get(RatingID ratingID) throws Exception
	{
		return load(ratingID, DataExists.MustExist);
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fRatingID = (RatingID)reader.readDataID("RatingID", RatingID.MaxLength, RatingID.CtorString);
		fName = reader.readString("Name", 64);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("RatingID", fRatingID, RatingID.MaxLength);
		writer.writeString("Name", fName, 64);
	}
}
