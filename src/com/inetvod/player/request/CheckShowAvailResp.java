/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.ShowCostList;

public class CheckShowAvailResp implements Writeable
{
	/* Fields */
	private ShowCostList fShowCostList;

	/* Construction */
	private CheckShowAvailResp(ShowCostList showCostList)
	{
		fShowCostList = showCostList;
	}

	public static CheckShowAvailResp newInstance(ShowCostList showCostList)
	{
		return new CheckShowAvailResp(showCostList);
	}

	/* Implementation */
	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeList("ShowCost", fShowCostList);
	}
}
