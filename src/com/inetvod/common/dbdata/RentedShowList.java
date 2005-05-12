package com.inetvod.common.dbdata;

import java.util.ArrayList;
import java.sql.Types;

/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
public class RentedShowList extends ArrayList
{
	/* Construction Methods */
	public RentedShowList()
	{
	}

	public static RentedShowList findByMemberID(MemberID memberID) throws Exception
	{
		DatabaseProcParam params[] = new DatabaseProcParam[1];

		params[0] = new DatabaseProcParam(Types.VARCHAR, memberID.toString());

		return (RentedShowList)RentedShow.getDatabaseAdaptor().selectManyByProc("RentedShow_GetByMemberID", params);
	}
}
