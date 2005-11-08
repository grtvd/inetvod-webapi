/**
 * Copyright � 2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.providerClient;

import com.inetvod.common.data.MemberID;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.ProviderShowID;
import com.inetvod.common.data.ShowCost;
import com.inetvod.common.data.ShowCostList;
import com.inetvod.common.data.ShowFormat;
import com.inetvod.common.data.ShowIDList;
import com.inetvod.common.dbdata.MemberProvider;
import com.inetvod.common.dbdata.Provider;
import com.inetvod.providerClient.request.CheckShowAvailResp;
import com.inetvod.providerClient.request.CheckShowAvailRqst;
import com.inetvod.providerClient.request.DataRequestor;
import com.inetvod.providerClient.request.RentShowResp;
import com.inetvod.providerClient.request.RentShowRqst;
import com.inetvod.providerClient.request.ShowDetailResp;
import com.inetvod.providerClient.request.ShowDetailRqst;
import com.inetvod.providerClient.request.ShowListResp;
import com.inetvod.providerClient.request.WatchShowRqst;
import com.inetvod.providerClient.request.WatchShowResp;
import com.inetvod.providerClient.rqdata.Payment;
import com.inetvod.providerClient.rqdata.ProviderStatusCode;
import com.inetvod.providerClient.rqdata.ShowDetailList;

public class ProviderRequestor
{
	/* Fields */
	private String fProviderRequestURL;
	private String fProviderAdminUserID;
	private String fProviderAdminPassword;
	private String fProviderMemberUserID;
	private String fProviderMemberPassword;

	private ProviderStatusCode fStatusCode;

	/* Getters and Setters */
	public void setMemberUser(String memberUserID, String memberPassword)
	{
		fProviderMemberUserID = memberUserID;
		fProviderMemberPassword = memberPassword;
	}

	public ProviderStatusCode getStatusCode() { return fStatusCode; }

	/* Construction */
	private ProviderRequestor(Provider provider)
	{
		fProviderRequestURL = provider.getRequestURL();
		fProviderAdminUserID = provider.getAdminUserID();
		fProviderAdminPassword = provider.getAdminPassword();
	}

	public static ProviderRequestor newInstance(Provider provider)
	{
		return new ProviderRequestor(provider);
	}

	public static ProviderRequestor newInstance(ProviderID providerID) throws Exception
	{
		Provider provider = Provider.get(providerID);
		return new ProviderRequestor(provider);
	}

	public static ProviderRequestor newInstance(ProviderID providerID, MemberID memberID) throws Exception
	{
		ProviderRequestor providerRequestor = ProviderRequestor.newInstance(providerID);

		MemberProvider memberProvider = MemberProvider.findByMemberIDProviderID(memberID, providerID);
		//TODO: need to decrypt Member's Provider credentials
		providerRequestor.setMemberUser(memberProvider.getEncryptedUserName(), memberProvider.getEncryptedPassword());

		return providerRequestor;
	}

	/* Implementation */
	private DataRequestor newDataRequestor(boolean memberRequest)
	{
		if(memberRequest)
			return DataRequestor.newInstance(fProviderRequestURL, fProviderAdminUserID, fProviderAdminPassword,
				fProviderMemberUserID, fProviderMemberPassword);
		else
			return DataRequestor.newInstance(fProviderRequestURL, fProviderAdminUserID, fProviderAdminPassword,
				null, null);
	}

	public boolean pingServer()
	{
		DataRequestor dataRequestor = newDataRequestor(false);
		dataRequestor.pingServer();

		fStatusCode = dataRequestor.getStatusCode();
		return ProviderStatusCode.sc_Success.equals(fStatusCode);
	}

	public ShowIDList showList()
	{
		DataRequestor dataRequestor = newDataRequestor(false);
		ShowListResp showListResp = dataRequestor.showList();

		fStatusCode = dataRequestor.getStatusCode();
		if(showListResp != null)
			return showListResp.getShowIDList();
		return null;
	}

	public ShowDetailList showDetail(ShowIDList showIDList)
	{
		DataRequestor dataRequestor = newDataRequestor(false);
		ShowDetailRqst showDetailRqst = ShowDetailRqst.newInstance(showIDList);
		ShowDetailResp showDetailResp = dataRequestor.showDetail(showDetailRqst);

		fStatusCode = dataRequestor.getStatusCode();
		if(showDetailResp != null)
			return showDetailResp.getShowDetailList();
		return null;
	}

	public ShowCostList checkShowAvail(ProviderShowID providerShowID, ShowFormat showFormat)
	{
		DataRequestor dataRequestor = newDataRequestor(true);
		CheckShowAvailRqst checkShowAvailRqst = CheckShowAvailRqst.newInstance(providerShowID, showFormat);
		CheckShowAvailResp checkShowAvailResp = dataRequestor.checkShowAvail(checkShowAvailRqst);

		fStatusCode = dataRequestor.getStatusCode();
		if(checkShowAvailResp != null)
			return checkShowAvailResp.getShowCostList();
		return null;
	}

	public RentShowResp rentShow(ProviderShowID showID, String playerIPAddress, ShowFormat showFormat,
		ShowCost approvedCost, Payment payment)
	{
		DataRequestor dataRequestor = newDataRequestor(true);
		RentShowRqst rentShowRqst = RentShowRqst.newInstance(showID, playerIPAddress, showFormat,
			approvedCost, payment);
		RentShowResp rentShowResp = dataRequestor.rentShow(rentShowRqst);

		fStatusCode = dataRequestor.getStatusCode();
		return rentShowResp;
	}

	public WatchShowResp watchShow(ProviderShowID showID, String playerIPAddress)
	{
		DataRequestor dataRequestor = newDataRequestor(true);
		WatchShowRqst watchShowRqst = WatchShowRqst.newInstance(showID, playerIPAddress);
		WatchShowResp watchShowResp = dataRequestor.watchShow(watchShowRqst);

		fStatusCode = dataRequestor.getStatusCode();
		return watchShowResp;
	}
}
