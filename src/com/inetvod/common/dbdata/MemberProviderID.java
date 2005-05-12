/**
 * Copyright � 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.StringID;
import com.inetvod.common.core.CtorUtil;

import java.lang.reflect.Constructor;
import java.util.UUID;

public class MemberProviderID extends StringID
{
	public static final Constructor CtorString = CtorUtil.getCtorString(MemberProviderID.class);
	public static final int MaxLength = 64;

	public MemberProviderID(String value)
	{
		super(value);
	}

	public static MemberProviderID newInstance()
	{
		return new MemberProviderID(UUID.randomUUID().toString());
	}
}
