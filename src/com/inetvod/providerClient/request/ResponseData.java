/**
 * Copyright © 2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.providerClient.request;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Readable;

public class ResponseData implements Readable
{
	/* Constants */
	public static final Constructor<ResponseData> CtorDataFiler = DataReader.getCtor(ResponseData.class);
	private static final int ResponseTypeMaxLength = 64;

	/* Properties */
	private String fResponseType;
	private Readable fResponse;

	public ResponseData(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	@SuppressWarnings({"unchecked"})
	public void readFrom(DataReader reader) throws Exception
	{
		fResponseType = reader.readString("ResponseType", ResponseTypeMaxLength);

		Class<Readable> cl = (Class<Readable>)Class.forName(getClass().getPackage().getName() + "." + fResponseType);
		Constructor<Readable> ctor = cl.getConstructor(new Class[] { DataReader.class });
		fResponse = reader.readObject(fResponseType, ctor);
	}
}
