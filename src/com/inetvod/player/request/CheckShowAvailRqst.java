/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.MemberProvider;
import com.inetvod.common.dbdata.Provider;
import com.inetvod.common.dbdata.ProviderID;
import com.inetvod.common.dbdata.ShowCostList;
import com.inetvod.common.dbdata.ShowID;
import com.inetvod.common.dbdata.ShowProvider;
import com.inetvod.player.rqdata.StatusCode;
import com.inetvod.providerClient.ProviderRequestor;
import com.inetvod.providerClient.rqdata.ProviderStatusCode;

public class CheckShowAvailRqst extends SessionRequestable
{
	/* Fields */
	private ShowID fShowID;
	private ProviderID fProviderID;

	/* Constuction Methods */
	public CheckShowAvailRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		Provider provider = Provider.get(fProviderID);
		ProviderRequestor providerRequestor = ProviderRequestor.newInstance(provider);

		// Confirm Provider's server can be communicated with
		if(!providerRequestor.pingServer())
		{
			fStatusCode = StatusCode.sc_NoProviderResponse;
			return null;
		}

		// Fetch Member's Provider information
		MemberProvider memberProvider = MemberProvider.findByMemberIDProviderID(this.fMemberID, fProviderID);
		//TODO: need to decrypt Member's Provider credentials
		providerRequestor.setMemberUser(memberProvider.getEncryptedUserName(), memberProvider.getEncryptedPassword());

		// Fetch Show as offered by Provider
		ShowProvider showProvider = ShowProvider.getByShowIDProviderID(fShowID, fProviderID);

		// Send request to Provider
		ShowCostList showCostList = providerRequestor.checkShowAvail(showProvider.getProviderShowID());
		ProviderStatusCode providerStatusCode = providerRequestor.getStatusCode();
		if(!ProviderStatusCode.sc_Success.equals(providerStatusCode)
			|| (showCostList == null))
		{
			if(ProviderStatusCode.sc_InvalidMemberUserID.equals(providerStatusCode))
				fStatusCode = StatusCode.sc_InvalidProviderUserIDPassword;
			else
				fStatusCode = StatusCode.sc_NoProviderResponse;
			return null;
		}

		CheckShowAvailResp response = CheckShowAvailResp.newInstance(showCostList);
		fStatusCode = StatusCode.sc_Success;

		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fShowID = reader.readDataID("ShowID", ShowID.MaxLength, ShowID.CtorString);
		fProviderID = reader.readDataID("ProviderID", ProviderID.MaxLength, ProviderID.CtorString);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ShowID", fShowID, ShowID.MaxLength);
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
	}
}
