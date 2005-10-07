/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"MagicNumber"})
public class StatusCode
{
	public static final StatusCode sc_Success = new StatusCode(0);

	public static final StatusCode sc_InvalidUserID = new StatusCode(1000);
	public static final StatusCode sc_InvalidSession = new StatusCode(1001);
	public static final StatusCode sc_UserIDPasswordMismatch = new StatusCode(1002);
	public static final StatusCode sc_InvalidProviderUserIDPassword = new StatusCode(1003);

	public static final StatusCode sc_AlreadyEnrolledAtProvider = new StatusCode(1004);
	public static final StatusCode sc_NoAutoProviderEnrollment = new StatusCode(1005);

	public static final StatusCode sc_Player_Missing = new StatusCode(1006);
	public static final StatusCode sc_Player_OutOfDate = new StatusCode(1007);

	public static final StatusCode sc_ShowSearch_NeedCriteiia = new StatusCode(1020);

	public static final StatusCode sc_CantPingProvider = new StatusCode(1030);

	public static final StatusCode sc_GeneralError = new StatusCode(9999);

	private static List<StatusCode> fAllValues = Arrays.asList(new StatusCode[]
		{
			sc_Success,
			sc_InvalidUserID,
			sc_InvalidSession,
			sc_UserIDPasswordMismatch,
			sc_InvalidProviderUserIDPassword,
			sc_AlreadyEnrolledAtProvider,
			sc_NoAutoProviderEnrollment,
			sc_Player_Missing,
			sc_Player_OutOfDate,
			sc_ShowSearch_NeedCriteiia,
			sc_CantPingProvider,
			sc_GeneralError
		});

	protected int fValue;

	private StatusCode(int value)
	{
		fValue = value;
	}

	public static StatusCode convertFromInt(Integer value)
	{
		if(value == null)
			return sc_Success;

		for(StatusCode statusCode : fAllValues)
			if(statusCode.fValue == value)
				return statusCode;

		return sc_GeneralError;
	}

	public static Integer convertToInt(StatusCode statusCode)
	{
		if(statusCode == null)
			return sc_Success.fValue;

		return statusCode.fValue;
	}
}
