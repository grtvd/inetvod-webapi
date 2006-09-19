/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings({"MagicNumber"})
public class StatusCode
{
	public static final StatusCode sc_Success = new StatusCode(0);

	public static final StatusCode sc_InvalidUserIDPassword = new StatusCode(1000);
	public static final StatusCode sc_InvalidSession = new StatusCode(1001);
	public static final StatusCode sc_InvalidProviderUserIDPassword = new StatusCode(1003);

	public static final StatusCode sc_AlreadyEnrolledAtProvider = new StatusCode(1004);
	public static final StatusCode sc_NoAutoProviderEnrollment = new StatusCode(1005);

	public static final StatusCode sc_PlayerMissing = new StatusCode(1006);
	public static final StatusCode sc_PlayerOutOfDate = new StatusCode(1007);
	public static final StatusCode sc_PlayerUnknown = new StatusCode(1008);

	public static final StatusCode sc_ShowSearch_NeedCriteiia = new StatusCode(1010);

	public static final StatusCode sc_NoProviderResponse = new StatusCode(1011);
	public static final StatusCode sc_UnknownProviderResponse = new StatusCode(1012);
	public static final StatusCode sc_CreditCardNotOnFile = new StatusCode(1013);
	public static final StatusCode sc_CreditCardDenied = new StatusCode(1014);
	public static final StatusCode sc_ShowNoAccess = new StatusCode(1015);
	public static final StatusCode sc_ShowLevelInsufficient = new StatusCode(1016);
	public static final StatusCode sc_ShowPaymentDenied = new StatusCode(1017);
	public static final StatusCode sc_ShowRentExpired = new StatusCode(1018);
	public static final StatusCode sc_InvalidAdultPIN = new StatusCode(1019);

	public static final StatusCode sc_GeneralError = new StatusCode(9999);

	private static List<StatusCode> fAllValues = Arrays.asList(new StatusCode[]
		{
			sc_Success,
			sc_InvalidUserIDPassword,
			sc_InvalidSession,
			sc_InvalidProviderUserIDPassword,
			sc_AlreadyEnrolledAtProvider,
			sc_NoAutoProviderEnrollment,
			sc_PlayerMissing,
			sc_PlayerOutOfDate,
			sc_PlayerUnknown,
			sc_ShowSearch_NeedCriteiia,
			sc_NoProviderResponse,
			sc_UnknownProviderResponse,
			sc_CreditCardNotOnFile,
			sc_CreditCardDenied,
			sc_ShowNoAccess,
			sc_ShowLevelInsufficient,
			sc_ShowPaymentDenied,
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
