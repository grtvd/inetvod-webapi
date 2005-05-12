/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.StringID;
import com.inetvod.common.core.CtorUtil;

import java.lang.reflect.Constructor;

public class ProviderID extends StringID
{
	public static final Constructor CtorString = CtorUtil.getCtorString(ProviderID.class);
	public static final int MaxLength = 64;

	public ProviderID(String value)
	{
		super(value);
	}
}
