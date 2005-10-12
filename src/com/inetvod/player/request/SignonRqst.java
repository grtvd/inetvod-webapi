/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import java.util.UUID;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Logger;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.ManufacturerID;
import com.inetvod.common.dbdata.Member;
import com.inetvod.common.dbdata.MemberSession;
import com.inetvod.common.dbdata.PlayerID;
import com.inetvod.common.dbdata.SerialNumber;
import com.inetvod.player.rqdata.IncludeAdult;
import com.inetvod.player.rqdata.MemberPrefs;
import com.inetvod.player.rqdata.MemberProviderList;
import com.inetvod.player.rqdata.MemberState;
import com.inetvod.player.rqdata.Player;
import com.inetvod.player.rqdata.PlayerRequestable;
import com.inetvod.player.rqdata.StatusCode;

public class SignonRqst implements PlayerRequestable
{
	/* Constants */
	private static final int UserIDMaxLength = 128;
	private static final int PasswordMaxLength = 32;

	/* Fields */
	private String fUserID;
	private String fPassword;
	private Player fPlayer;

	private StatusCode fStatusCode = StatusCode.sc_GeneralError;
	public StatusCode getStatusCode() { return fStatusCode; }

	public SignonRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		SignonResp response = new SignonResp();
		SerialNumber serialNumber;

		// get Player information
		if(fPlayer == null)
		{
			fStatusCode = StatusCode.sc_Player_Missing;
			return response;
		}
		//TODO: make standard Player-version check
		if((new ManufacturerID("inetvod").equals(fPlayer.getManufacturerID()) && "ps2".equals(fPlayer.getModelNo())))
		{
			String version = fPlayer.getVersion();
			if(!"1.0.1005".equals(version))
			{
				fStatusCode = StatusCode.sc_Player_OutOfDate;
				return response;
			}
		}

		//TODO: this should be using a UUID from a database of players based on Manufacturer, ModelNo, Version
		PlayerID playerID = new PlayerID(UUID.randomUUID().toString());

		//TODO: decrypt UserID and Password based on Player

		serialNumber = SerialNumber.find(fUserID);
		if((serialNumber != null) && serialNumber.getActive())
		{
			if(serialNumber.getPIN().matches(fPassword))
			{
				Member member = Member.get(serialNumber.getMemberID());
				MemberSession memberSession = MemberSession.newInstance(serialNumber.getMemberID(), playerID);

				SessionData sessionData = new SessionData(memberSession.getMemberSessionID());
				response.setSessionData(sessionData);
				response.setSessionExpires(memberSession.getExpiresAt());

				MemberState memberState = new MemberState();
				com.inetvod.common.dbdata.MemberPrefs dbMemberPrefs = com.inetvod.common.dbdata.MemberPrefs.getCreate(member.getMemberID());
				memberState.setMemberPrefs(new MemberPrefs(dbMemberPrefs));
				memberState.setMemberProviderList(new MemberProviderList(
					com.inetvod.common.dbdata.MemberProviderList.findByMemberID(member.getMemberID())));
				response.setMemberState(memberState);

				memberSession.setShowAdult(IncludeAdult.Always.equals(dbMemberPrefs.getIncludeAdult()));
				memberSession.update();

				fStatusCode = StatusCode.sc_Success;
			}
			else
				fStatusCode = StatusCode.sc_UserIDPasswordMismatch;
		}
		else
			fStatusCode = StatusCode.sc_InvalidUserID;

		Logger.logInfo(this, "fulfillRequest", String.format("UserID:%s; StatusCode:%d;", fUserID, StatusCode.convertToInt(fStatusCode)));
		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fUserID = reader.readString("UserID", UserIDMaxLength);
		fPassword = reader.readString("Password", PasswordMaxLength);
		fPlayer = reader.readObject("Player", Player.CtorDataReader);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("UserID", fUserID, UserIDMaxLength);
		writer.writeString("Password", fPassword, PasswordMaxLength);
		writer.writeObject("Player", fPlayer);
	}
}
