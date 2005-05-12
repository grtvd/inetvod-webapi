/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.StringID;
import com.inetvod.common.core.CtorUtil;

import java.lang.reflect.Constructor;

public class ShowProviderID extends StringID
{
	public static final Constructor CtorString = CtorUtil.getCtorString(ShowProviderID.class);
	public static final int MaxLength = 64;

	public ShowProviderID(String value)
	{
		super(value);
	}
}
