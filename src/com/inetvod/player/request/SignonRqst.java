/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import java.util.Calendar;
import java.util.GregorianCalendar;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Requestable;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.Member;
import com.inetvod.common.dbdata.SerialNumber;
import com.inetvod.common.dbdata.ManufacturerID;
import com.inetvod.player.rqdata.MemberPrefs;
import com.inetvod.player.rqdata.MemberProviderList;
import com.inetvod.player.rqdata.MemberState;
import com.inetvod.player.rqdata.Player;

public class SignonRqst implements Requestable
{
	protected String fUserID;
	protected String fPassword;
	protected Player fPlayer;
	public Player getPlayer() { return fPlayer; }

	protected StatusCode fStatusCode = StatusCode.sc_GeneralError;
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

		//TODO: decrypt UserID and Password based on Player

		serialNumber = SerialNumber.find(fUserID);
		if((serialNumber != null) && serialNumber.getActive())
		{
			if(serialNumber.getPIN().matches(fPassword))
			{
				Member member = Member.get(serialNumber.getMemberID());
				SessionData sessionData = new SessionData(member.getMemberID());
//					sessionData.UserID = fUserID;
//					sessionData.Password = fPassword;
				response.setSessionData(sessionData);

				GregorianCalendar cal = new GregorianCalendar();
				cal.add(Calendar.DATE, 1);
				response.setSessionExpires(cal.getTime());

				MemberState memberState = new MemberState();
				memberState.setMemberPrefs(new MemberPrefs(com.inetvod.common.dbdata.MemberPrefs.getCreate(member.getMemberID())));
				memberState.setMemberProviderList(new MemberProviderList(
					com.inetvod.common.dbdata.MemberProviderList.findByMemberID(member.getMemberID())));
				response.setMemberState(memberState);

				fStatusCode = StatusCode.sc_Success;
			}
			else
				fStatusCode = StatusCode.sc_UserIDPasswordMismatch;
		}
		else
			fStatusCode = StatusCode.sc_InvalidUserID;

		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fUserID = reader.readString("UserID", 128);
		fPassword = reader.readString("Password", 32);
		fPlayer = (Player)reader.readObject("Player", Player.CtorDataFiler);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("UserID", fUserID, 128);
		writer.writeString("Password", fPassword, 32);
		writer.writeObject("Player", fPlayer);
	}
}
