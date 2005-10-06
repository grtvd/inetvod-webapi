/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.MemberProvider;
import com.inetvod.common.dbdata.ProviderID;

public class ProviderEnrollRqst extends SessionRequestable
{
	/* Fields */
	private ProviderID fProviderID;

	/* Constuction Methods */
	public ProviderEnrollRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		ProviderEnrollResp response;
		MemberProvider memberProvider;

		response = new ProviderEnrollResp();

		memberProvider = MemberProvider.findByMemberIDProviderID(fMemberID, fProviderID);
		if (memberProvider == null)
		{
			//TODO: Enroll user at provider

			memberProvider = MemberProvider.newInstance(fMemberID, fProviderID);
			//TODO: set UserID, Password
			memberProvider.update();

			fStatusCode = StatusCode.sc_Success;
		}
		else
			fStatusCode = StatusCode.sc_AlreadyEnrolledAtProvider;

		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
	}
}
