/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import java.util.UUID;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ProviderConnectionType;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.dbdata.Member;
import com.inetvod.common.dbdata.MemberAccount;
import com.inetvod.common.dbdata.MemberLogon;
import com.inetvod.common.dbdata.MemberProvider;
import com.inetvod.common.dbdata.ProviderConnection;
import com.inetvod.player.rqdata.StatusCode;
import com.inetvod.providerClient.ProviderRequestor;
import com.inetvod.providerClient.request.EnrollRqst;
import com.inetvod.providerClient.rqdata.ProviderStatusCode;

public class ProviderEnrollRqst extends SessionRequestable
{
	/* Constants */
	private static final int PasswordMaxLength = 16;

	/* Fields */
	private ProviderID fProviderID;

	/* Constuction Methods */
	public ProviderEnrollRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		// Confirm member is not already enrolled
		if(MemberProvider.findByMemberIDProviderID(fMemberID, fProviderID) != null)
		{
			fStatusCode = StatusCode.sc_AlreadyEnrolledAtProvider;
			return null;
		}

		// Fetch ProviderAPI ProviderConnection, other connections do not support enrollment
		ProviderConnection providerConnection = ProviderConnection.findByProviderIDConnectionType(
			fProviderID, ProviderConnectionType.ProviderAPI);
		if(providerConnection == null)
		{
			fStatusCode = StatusCode.sc_GeneralError;
			return null;
		}

		ProviderRequestor providerRequestor = ProviderRequestor.newInstance(providerConnection);

		// Confirm Provider's server can be communicated with
		if(!providerRequestor.pingServer())
		{
			fStatusCode = StatusCode.sc_NoProviderResponse;
			return null;
		}

		// Enroll user at provider
		Member member = getMember();
		MemberLogon memberLogon = MemberLogon.get(member.getMemberID());
		MemberAccount memberAccount = MemberAccount.find(member.getMemberID());

		String userID = String.format("inetvod%d", memberLogon.getLogonID());
		String password = UUID.randomUUID().toString().replaceAll("-", "").substring(0, PasswordMaxLength);
		String email = String.format("%d@inetvod.net", memberLogon.getLogonID());
		EnrollRqst enrollRqst = EnrollRqst.newInstance(userID, password, member.getFirstName(), member.getLastName(),
			email);

		if(memberAccount != null)
		{
			enrollRqst.setBirthDate(memberAccount.getBirthDate());
			enrollRqst.setShippingAddress(memberAccount.getHomeAddress());
			if(memberAccount.getCreditCard() != null)
				enrollRqst.setBillingAddress(memberAccount.getCreditCard().getBillingAddress());
		}

		boolean success = providerRequestor.enroll(enrollRqst);

		ProviderStatusCode providerStatusCode = providerRequestor.getStatusCode();
		if(!ProviderStatusCode.sc_Success.equals(providerStatusCode) || !success)
		{
			fStatusCode = StatusCode.sc_UnknownProviderResponse;
			return null;
		}

		// Save member's provider information
		MemberProvider memberProvider = MemberProvider.newInstance(fMemberID, fProviderID);
		memberProvider.setUserID(userID);
		memberProvider.setPassword(password);
		memberProvider.update();

		// Return response to player
		ProviderEnrollResp response = new ProviderEnrollResp();

		fStatusCode = StatusCode.sc_Success;
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
