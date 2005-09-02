/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.ShowCostList;

public class CheckShowAvailResp implements Writeable
{
	public ShowCostList ShowCostList;

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeList("ShowCost", ShowCostList);
	}
}
