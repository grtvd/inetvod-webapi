/**
 * Copyright © 2004-2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Logger;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.IncludeAdult;
import com.inetvod.common.data.ManufacturerID;
import com.inetvod.common.data.MemberID;
import com.inetvod.common.data.PlayerID;
import com.inetvod.common.dbdata.Member;
import com.inetvod.common.dbdata.MemberLogon;
import com.inetvod.common.dbdata.MemberSession;
import com.inetvod.common.dbdata.PlayerManager;
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
	private static final String GuestUserID = "guest";

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
			fStatusCode = StatusCode.sc_PlayerMissing;
			return response;
		}
		//TODO: make standard Player-version check
		if(new ManufacturerID("inetvod").equals(fPlayer.getManufacturerID()))
		{
			if("ps2".equals(fPlayer.getModelNo()))
			{
				fStatusCode = StatusCode.sc_PlayerOutOfDate;
				return response;
			}
			else if("mpdemo".equals(fPlayer.getModelNo()))
			{
				String version = fPlayer.getVersion();
				if(!"1.0.1007".equals(version))
				{
					fStatusCode = StatusCode.sc_PlayerOutOfDate;
					return response;
				}
			}
		}

		//TODO: this should be using a UUID from a database of players based on Manufacturer, ModelNo, Version
		PlayerID playerID = PlayerManager.getThe().findPlayerIDFromManufacturerIDModelNo(fPlayer.getManufacturerID(),
			fPlayer.getModelNo());
		if(playerID == null)
		{
			fStatusCode = StatusCode.sc_PlayerUnknown;
			return response;
		}

		//TODO: decrypt UserID and Password based on Player

		if(GuestUserID.equals(fUserID))
			memberLogon = MemberLogon.get(MemberID.GuestMemberID);
		else
		{
			if(StrUtil.isNumeric(fUserID))
				memberLogon = MemberLogon.findByLogonIDPIN(Integer.parseInt(fUserID), fPassword);
			else
				memberLogon = MemberLogon.findByEmailPassword(fUserID, fPassword);
		}

		if(memberLogon != null)
		{
			Member member = Member.get(memberLogon.getMemberID());
			com.inetvod.common.dbdata.MemberPrefs dbMemberPrefs = com.inetvod.common.dbdata.MemberPrefs.getCreate(member.getMemberID());
			MemberSession memberSession = MemberSession.newInstance(memberLogon.getMemberID(), playerID,
				dbMemberPrefs.getIncludeRatingIDList());

			SessionData sessionData = new SessionData(memberSession.getMemberSessionID());
			response.setSessionData(sessionData);
			response.setSessionExpires(memberSession.getExpiresAt());

			MemberState memberState = new MemberState();
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
