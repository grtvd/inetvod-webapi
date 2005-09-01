/**
 * Copyright � 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import java.lang.reflect.Constructor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.inetvod.common.core.DataID;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Readable;

public class DatabaseFieldReader extends DataReader
{
	protected ResultSet fResultSet;
	protected List fFieldNamePrefixList = new ArrayList();

	public DatabaseFieldReader(ResultSet resultSet)
	{
		fResultSet = resultSet;
	}

	protected String buildFullFieldName(String fieldName)
	{
		if(fFieldNamePrefixList.size() == 0)
			return fieldName;

		StringBuffer sb = new StringBuffer();
		Iterator iter = fFieldNamePrefixList.iterator();
		while(iter.hasNext())
			sb.append((String)iter.next());
		sb.append(fieldName);

		return sb.toString();
	}

	/**
	 * Read a Byte.
	 *
	 * @param fieldName
	 * @return may return null
	 */
	public Byte readByte(String fieldName) throws Exception
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Read a Short.
	 *
	 * @param fieldName
	 * @return may return null
	 */
	public Short readShort(String fieldName) throws Exception
	{
		short value = fResultSet.getShort(buildFullFieldName(fieldName));

		if(fResultSet.wasNull())
			return null;

		return new Short(value);
	}

	/**
	 * Read a Integer.
	 *
	 * @param fieldName
	 * @return may return null
	 */
	public Integer readInt(String fieldName) throws Exception
	{
		throw new UnsupportedOperationException("need to implement");	//TODO: need to implement
	}

	/**
	 * Read a Float.
	 *
	 * @param fieldName
	 * @return may return null
	 */
	public Float readFloat(String fieldName) throws Exception
	{
		float value = fResultSet.getFloat(buildFullFieldName(fieldName));

		if(fResultSet.wasNull())
			return null;

		return new Float(value);
	}

	/**
	 * Read a Double.
	 *
	 * @param fieldName
	 * @return may return null
	 */
	public Double readDouble(String fieldName) throws Exception
	{
		double value = fResultSet.getDouble(buildFullFieldName(fieldName));

		if(fResultSet.wasNull())
			return null;

		return new Double(value);
	}

	/**
	 * Read a String.
	 *
	 * @param fieldName
	 * @param maxLength
	 * @return may return null
	 */
	public String readString(String fieldName, int maxLength) throws Exception
	{
		String data = fResultSet.getString(buildFullFieldName(fieldName));

		if ((data == null) || (data.length() == 0))
			return null;

		if(data.length() > maxLength)
			throw new Exception("invalid len(" + data.length() + "), maxLength(" + maxLength +" )");

		return data;
	}

	/**
	 * Read a Date, no Time component.
	 *
	 * @param fieldName
	 * @return may return null
	 */
	public Date readDate(String fieldName) throws Exception
	{
		Date value = fResultSet.getDate(buildFullFieldName(fieldName));

		if(fResultSet.wasNull())
			return null;

		return value;
	}

	/**
	 * Read a Date with a Time compnent.
	 *
	 * @param fieldName
	 * @return may return null
	 */
	public Date readDateTime(String fieldName) throws Exception
	{
		Date value = fResultSet.getTimestamp(buildFullFieldName(fieldName));

		if(fResultSet.wasNull())
			return null;

		return value;
	}

	/**
	 * Read a Boolean.
	 *
	 * @param fieldName
	 * @return may return null
	 */
	public Boolean readBoolean(String fieldName) throws Exception
	{
		boolean value = fResultSet.getBoolean(buildFullFieldName(fieldName));

		if(fResultSet.wasNull())
			return null;

		return new Boolean(value);
	}

	/**
	 * Read an Object.
	 *
	 * @param fieldName
	 * @param ctorDataFiler
	 * @return may return null
	 */
	public Readable readObject(String fieldName, Constructor ctorDataFiler) throws Exception
	{
		fFieldNamePrefixList.add(fieldName + "_");
		Readable readable = (Readable)ctorDataFiler.newInstance(new Object[] { this });
		fFieldNamePrefixList.remove(fFieldNamePrefixList.size() - 1);

		return readable;
	}

	/**
	 * Read a list of complex Objects.
	 *
	 * @param fieldName
	 * @param listCtor
	 * @param itemCtorDataFiler
	 * @return will never return null, may return an empty list
	 */
	public List readList(String fieldName, Constructor listCtor, Constructor itemCtorDataFiler) throws Exception
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Read a list of Strings (or non-complex items than can be constructed from a sting).
	 *
	 * @param fieldName
	 * @param maxLength
	 * @param listCtor
	 * @param itemCtorString
	 * @return will never return null, may return an empty list
	 */
	public List readStringList(String fieldName, int maxLength, Constructor listCtor, Constructor itemCtorString) throws Exception
	{
		return null;  //To change body of implemented methods use File | Settings | File Templates.
	}

	/**
	 * Read a DataID object.
	 *
	 * @param fieldName
	 * @param maxLength
	 * @param ctorString
	 * @return may return null
	 */
	public DataID readDataID(String fieldName, int maxLength, Constructor ctorString) throws Exception
	{
		String data = readString(fieldName, maxLength);

		if (data == null)
			return null;

		return (DataID)ctorString.newInstance(new Object[] { data });
	}
}