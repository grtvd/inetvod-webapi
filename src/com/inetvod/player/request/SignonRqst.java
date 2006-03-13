/**
 * Copyright � 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import java.util.UUID;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Logger;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.IncludeAdult;
import com.inetvod.common.data.ManufacturerID;
import com.inetvod.common.data.PlayerID;
import com.inetvod.common.dbdata.Member;
import com.inetvod.common.dbdata.MemberLogon;
import com.inetvod.common.dbdata.MemberSession;
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
		MemberLogon memberLogon = null;

		// get Player information
		if(fPlayer == null)
		{
			fStatusCode = StatusCode.sc_Player_Missing;
			return response;
		}
		//TODO: make standard Player-version check
		if(new ManufacturerID("inetvod").equals(fPlayer.getManufacturerID()))
		{
			if("ps2".equals(fPlayer.getModelNo()))
			{
				fStatusCode = StatusCode.sc_Player_OutOfDate;
				return response;
			}
			else if("mpdemo".equals(fPlayer.getModelNo()))
			{
				String version = fPlayer.getVersion();
				if(!"1.0.1006".equals(version))
				{
					fStatusCode = StatusCode.sc_Player_OutOfDate;
					return response;
				}
			}
		}

		//TODO: this should be using a UUID from a database of players based on Manufacturer, ModelNo, Version
		PlayerID playerID = new PlayerID(UUID.randomUUID().toString());

		//TODO: decrypt UserID and Password based on Player

		int logonID = -1;
		try { logonID = Integer.parseInt(fUserID); } catch(NumberFormatException e) {}

		if(logonID != -1)
			memberLogon = MemberLogon.findByLogonIDPIN(Integer.parseInt(fUserID), fPassword);
		if(memberLogon != null)
		{
			Member member = Member.get(memberLogon.getMemberID());
			MemberSession memberSession = MemberSession.newInstance(memberLogon.getMemberID(), playerID);

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
			fStatusCode = StatusCode.sc_InvalidUserIDPassword;

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
