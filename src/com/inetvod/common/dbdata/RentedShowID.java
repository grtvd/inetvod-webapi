/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.StringID;
import com.inetvod.common.core.CtorUtil;

import java.lang.reflect.Constructor;
import java.util.UUID;

public class RentedShowID extends StringID
{
	public static final Constructor CtorString = CtorUtil.getCtorString(RentedShowID.class);
	public static final int MaxLength = 64;

	public RentedShowID(String value)
	{
		super(value);
	}

	public static RentedShowID newInstance()
	{
		return new RentedShowID(UUID.randomUUID().toString());
	}
}
