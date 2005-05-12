/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

public class IncludeAdult
{
	public static final int MaxLength = 32;

	public static final IncludeAdult Never = new IncludeAdult("Never");
	public static final IncludeAdult PromptPassword = new IncludeAdult("PromptPassword");
	public static final IncludeAdult Always = new IncludeAdult("Always");

	private final String fValue;

	private IncludeAdult(String value)
	{
		fValue = value;
	}

	public String toString()
	{
		return fValue;
	}

	public static IncludeAdult convertFromString(String value)
	{
		if((value == null) || (value.length() == 0))
			return null;

		if(Never.fValue.matches(value))
			return Never;
		if(PromptPassword.fValue.matches(value))
			return PromptPassword;
		if(Always.fValue.matches(value))
			return Always;

		throw new IllegalArgumentException("bad value(" + value + ")");
	}

	public static String convertToString(IncludeAdult includeAdult)
	{
		if(includeAdult == null)
			return null;
		return includeAdult.toString();
	}
}
