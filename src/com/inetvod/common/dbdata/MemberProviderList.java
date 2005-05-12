/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import java.util.ArrayList;
import java.sql.Types;

public class MemberProviderList extends ArrayList
{
	/* Constuction Methods */
	public static MemberProviderList findByMemberID(MemberID memberID) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[1];

		params[0] = new DatabaseProcParam(Types.VARCHAR, memberID.toString());

		return (MemberProviderList)MemberProvider.getDatabaseAdaptor().selectManyByProc("MemberProvider_GetByMemberID", params);
	}
}
