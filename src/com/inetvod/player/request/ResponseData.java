/**
 * Copyright � 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;

public class ResponseData implements Writeable
{
	/* Constants */
	public static final int ResponseTypeMaxLength = 64;

	/* Properties */
	protected String fResponseType;
	protected Writeable fResponse;

	public ResponseData(Writeable response)
	{
		String[] nameParts = response.getClass().getName().split("\\.");
		fResponseType = nameParts[nameParts.length - 1];
		fResponse = response;
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("ResponseType", fResponseType, ResponseTypeMaxLength);
		writer.writeObject(fResponseType, fResponse);
	}
}
