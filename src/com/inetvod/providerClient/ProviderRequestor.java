/**
 * Copyright © 2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.providerClient;

import com.inetvod.common.dbdata.Provider;
import com.inetvod.providerClient.request.DataRequestor;
import com.inetvod.providerClient.rqdata.StatusCode;

public class ProviderRequestor
{
	/* Fields */
	private Provider fProvider;
	private String fProviderRequestURL;
	private String fProviderAdminUserID;
	private String fProviderAdminPassword;

	/* Construction */
	private ProviderRequestor(Provider provider)
	{
		fProvider = provider;
		fProviderRequestURL = "http://api.inetvod.com/provider/providerapi";	//TODO: get from Provider
		fProviderAdminUserID = "super";	//TODO: get from Provider
		fProviderAdminPassword = "superpassword";	//TODO: get from Provider
	}

	public static ProviderRequestor newInstance(Provider provider)
	{
		return new ProviderRequestor(provider);
	}

	/* Implementation */
	private DataRequestor newDataRequestor()
	{
		return DataRequestor.newInstance(fProviderRequestURL, fProviderAdminUserID, fProviderAdminPassword);
	}

	public boolean pingServer()
	{
		DataRequestor dataRequestor = newDataRequestor();
		dataRequestor.pingServer();

		return StatusCode.sc_Success.equals(dataRequestor.getStatusCode());
	}
}
