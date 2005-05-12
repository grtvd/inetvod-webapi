/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.StringID;
import com.inetvod.common.core.CtorUtil;

import java.lang.reflect.Constructor;

public class ShowCategoryID extends StringID
{
	public static final Constructor CtorString = CtorUtil.getCtorString(ShowCategoryID.class);
	public static final int MaxLength = 64;

	public ShowCategoryID(String value)
	{
		super(value);
	}
}
