/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

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

	public static final StatusCode sc_GeneralError = new StatusCode(9999);

	protected int fValue;

	private StatusCode(int value)
	{
		fValue = value;
	}

	public static StatusCode convertFromInt(Integer value)
	{
		if(value == null)
			return sc_Success;

		if(value == sc_Success.fValue)
			return sc_Success;
		if(value == sc_InvalidUserID.fValue)
			return sc_InvalidUserID;
		if(value == sc_UserIDPasswordMismatch.fValue)
			return sc_UserIDPasswordMismatch;
		if(value == sc_InvalidProviderUserIDPassword.fValue)
			return sc_InvalidProviderUserIDPassword;

		if(value == sc_AlreadyEnrolledAtProvider.fValue)
			return sc_AlreadyEnrolledAtProvider;
		if(value == sc_NoAutoProviderEnrollment.fValue)
			return sc_NoAutoProviderEnrollment;

		if(value == sc_NoAutoProviderEnrollment.fValue)
			return sc_NoAutoProviderEnrollment;

		if(value == sc_Player_Missing.fValue)
			return sc_Player_Missing;
		if(value == sc_Player_OutOfDate.fValue)
			return sc_Player_OutOfDate;

		return sc_GeneralError;
	}

	public static Integer convertToInt(StatusCode statusCode)
	{
		if(statusCode == null)
			return sc_Success.fValue;

		return statusCode.fValue;
	}
}
