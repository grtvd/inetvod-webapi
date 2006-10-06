/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import java.util.Calendar;

import com.inetvod.common.core.CountryID;
import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.Address;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.ProviderConnectionType;
import com.inetvod.common.dbdata.Member;
import com.inetvod.common.dbdata.MemberProvider;
import com.inetvod.common.dbdata.ProviderConnection;
import com.inetvod.player.rqdata.StatusCode;
import com.inetvod.providerClient.ProviderRequestor;
import com.inetvod.providerClient.request.EnrollRqst;
import com.inetvod.providerClient.rqdata.ProviderStatusCode;

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
		String userID = "member";	//TODO:
		String password = "memberpassword"; 	//TODO:
		String email = "abc@def.com";	//TODO:
		Member member = getMember();
		EnrollRqst enrollRqst = EnrollRqst.newInstance(userID, password, member.getFirstName(), member.getLastName(),
			email);
		//TODO: set BirthDate, ShippingAddress, BillingAddress
		Calendar cal = Calendar.getInstance();
		cal.set(1980, 0, 1, 0, 0, 0);
		enrollRqst.setBirthDate(cal.getTime());
		Address address = new Address();
		address.setAddrStreet1("1000 Hoy Circle");
		address.setCity("Collegeville");
		address.setState("PA");
		address.setPostalCode("19426");
		address.setCountry(CountryID.US);
		address.setPhone("610-757-9999");
		enrollRqst.setShippingAddress(address);
		enrollRqst.setBillingAddress(address);
		boolean success = providerRequestor.enroll(enrollRqst);

		ProviderStatusCode providerStatusCode = providerRequestor.getStatusCode();
		if(!ProviderStatusCode.sc_Success.equals(providerStatusCode) || !success)
		{
			fStatusCode = StatusCode.sc_UnknownProviderResponse;
			return null;
		}

		// Save member's provider information
		MemberProvider memberProvider = MemberProvider.newInstance(fMemberID, fProviderID);
		//TODO: set UserID, Password
		memberProvider.setUserID("member");	//TODO: remove
		memberProvider.setPassword("memberpassword");	//TODO: remove
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
