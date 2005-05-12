/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.CtorUtil;
import com.inetvod.common.core.StringID;


import java.lang.reflect.Constructor;
import java.util.UUID;

public class MemberID extends StringID
{
	public static final Constructor CtorString = CtorUtil.getCtorString(MemberID.class);
	public static final int MaxLength = 64;

	public MemberID(String value)
	{
		super(value);
	}

	public static MemberID newInstance()
	{
		return new MemberID(UUID.randomUUID().toString());
	}
}
