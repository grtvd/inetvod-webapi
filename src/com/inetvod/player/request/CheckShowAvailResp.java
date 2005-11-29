/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ShowCost;

public class CheckShowAvailResp implements Writeable
{
	/* Fields */
	private ShowCost fShowCost;

	/* Construction */
	private CheckShowAvailResp(ShowCost showCost)
	{
		fShowCost = showCost;
	}

	public static CheckShowAvailResp newInstance(ShowCost showCost)
	{
		return new CheckShowAvailResp(showCost);
	}

	/* Implementation */
	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeObject("ShowCost", fShowCost);
	}
}
