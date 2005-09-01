/**
 * Copyright � 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataExists;

import java.lang.reflect.Constructor;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Types;

public class DatabaseAdaptor
{
	protected Class fObjectType;
	protected String fObjectName;
	protected Constructor fObjectCtor;
	protected Class fListType;
	protected Constructor fListCtor;
//	protected String fKeyFieldName;

	//protected Connection fSqlConnection;
	protected String fGetStoredProcedure;
	protected String fInsertStoredProcedure;
	protected String fUpdateStoredProcedure;
	protected String fDeleteStoredProcedure;

	protected DatabaseAdaptor(Class objectType, Class listType, int numFields) throws Exception
	{
		fObjectType = objectType;
		fListType = listType;
//		fKeyFieldName = keyFieldName;

		fObjectCtor = fObjectType.getConstructor(new Class[] { DataReader.class } );
		fListCtor = fListType.getConstructor(new Class[]{});

		//Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		//fSqlConnection = DriverManager.getConnection("jdbc:microsoft:sqlserver://DAVIDSON03\\INETVOD","sa","");

		String name = objectType.getName();
		String[] nameParts = name.split("\\.");
		fObjectName = nameParts[nameParts.length - 1];

		fGetStoredProcedure = buildProcName(fObjectName + "_Get", 1);
		fInsertStoredProcedure =buildProcName(fObjectName + "_Insert", numFields);
		fUpdateStoredProcedure = buildProcName(fObjectName + "_Update", numFields);
		fDeleteStoredProcedure = buildProcName(fObjectName + "_Delete", 1);
	}

	public static DatabaseAdaptor newInstance(Class objectType, Class listType, int numFields)
	{
		try
		{
			return new DatabaseAdaptor(objectType, listType, numFields);
		}
		catch(Exception e)
		{
		}

		return null;
	}

	public Connection getConnection() throws Exception
	{
		Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
		Connection conn = DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost","sa","st&r3uc");
		conn.setCatalog("iNetVOD");
		return conn;
	}

	public DatabaseObject selectByKey(Object key, DataExists exists) throws Exception
	{
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet;

		try
		{
			connection = getConnection();

			statement = connection.prepareCall(fGetStoredProcedure);
			statement.setString(1, key.toString());
			resultSet = statement.executeQuery();

			return readOne(resultSet, exists);
		}
		finally
		{
			if(statement != null)
				statement.close();
			if(connection != null)
				connection.close();
		}
	}

	public DatabaseObject select(String command, DataExists exists) throws Exception
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet;

		try
		{
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(command);

			return readOne(resultSet, exists);
		}
		finally
		{
			if(statement != null)
				statement.close();
			if(connection != null)
				connection.close();
		}
	}

	public DatabaseObject selectByProc(String prodecure, DatabaseProcParam[] params, DataExists exists) throws Exception
	{
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet;
		DatabaseObject newObject;

		try
		{
			connection = getConnection();

			statement = connection.prepareCall(buildProcName(prodecure, (params == null) ? 0 : params.length));
			setProcParams(statement, params);
			resultSet = statement.executeQuery();

			return readOne(resultSet, exists);
		}
		finally
		{
			if(statement != null)
				statement.close();
			if(connection != null)
				connection.close();
		}
	}

	public List selectManyByProc(String prodecure, DatabaseProcParam[] params) throws Exception
	{
		List newList = (List)fListCtor.newInstance(new Object[] {});
		Connection connection = null;
		CallableStatement statement = null;
		ResultSet resultSet;
		DatabaseObject newObject;

		try
		{
			connection = getConnection();

			statement = connection.prepareCall(buildProcName(prodecure, (params == null) ? 0 : params.length));
			setProcParams(statement, params);
			resultSet = statement.executeQuery();

			while((newObject = readOne(resultSet, DataExists.MayNotExist)) != null)
				newList.add(newObject);
		}
		finally
		{
			if(statement != null)
				statement.close();
			if(connection != null)
				connection.close();
		}

		return newList;
	}

	protected String buildProcName(String procedure, int numParams)
	{
		String fields = "";

		if(numParams > 0)
		{
			StringBuffer sb = new StringBuffer("?");
			for(int i = 1; i < numParams; i++)
				sb.append(", ?");
			fields = sb.toString();
		}

		return "{call " + procedure + "(" + fields +")}";
	}

	protected void setProcParams(PreparedStatement statement, DatabaseProcParam[] params) throws SQLException
	{
		DatabaseProcParam param;

		if(params != null)
		{
			for(int i = 0; i < params.length; i++)
			{
				param = params[i];

				if(param.Value == null)
					statement.setNull(i + 1, param.SqlType);
				else if(param.SqlType == Types.VARCHAR)
					statement.setString(i + 1, (String)param.Value);
				else
					throw new IllegalArgumentException();
			}
		}
	}

	protected DatabaseObject readOne(ResultSet resultSet, DataExists exists) throws SQLException, SearchException
	{
		if(!resultSet.next())
		{
			if(exists == DataExists.MayNotExist)
				return null;
			throw new SearchException("No Record Found");
		}

		try
		{
			DatabaseFieldReader reader = new DatabaseFieldReader(resultSet);
			DatabaseObject newObject = (DatabaseObject) fObjectCtor.newInstance(new Object[] { reader });
			newObject.setNewRecord(false);
			return newObject;
		}
		catch(Exception e)
		{
			throw new SearchException("Read Record Failed", e);
		}
	}

	public void update(DatabaseObject databaseObject) throws Exception
	{
		Connection connection = null;
		PreparedStatement statement = null;

		try
		{
			DatabaseFieldWriter writer;

			connection = getConnection();

			if(databaseObject.isNewRecord())
				statement = connection.prepareStatement(fInsertStoredProcedure);
			else
				statement = connection.prepareStatement(fUpdateStoredProcedure);

			writer = new DatabaseFieldWriter(statement);
			databaseObject.writeTo(writer);

			int result = statement.executeUpdate();
			if(result != 1)
				throw new UpdateException("update failed, result(" + result + ")");
			databaseObject.setNewRecord(false);
		}
		finally
		{
			if(statement != null)
				statement.close();
			if(connection != null)
				connection.close();
		}
	}

	public void delete(Object key) throws Exception
	{
		Connection connection = null;
		CallableStatement statement = null;

		try
		{
			connection = getConnection();

			statement = connection.prepareCall(fDeleteStoredProcedure);
			statement.setString(1, key.toString());

			int result = statement.executeUpdate();
			if(result != 1)
				throw new UpdateException("delete failed, result(" + result + ")");
		}
		finally
		{
			if(statement != null)
				statement.close();
			if(connection != null)
				connection.close();
		}
	}

	private void metaDataCheck() throws Exception
	{
		Connection connection = null;
		//PreparedStatement statement = null;
		CallableStatement statement = null;
		ResultSet resultSet;

		connection = getConnection();
		//resultSet = connection.getMetaData().getSchemas();
		resultSet = connection.getMetaData().getProcedureColumns("iNetVOD", "dbo", null, null);
		while(resultSet.next())
		{
			for(int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++)
				System.out.print(resultSet.getString(i) + "|");
			System.out.println();
		}
	}
}