/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.dbdata.ShowCost;

public class CheckShowAvailResp implements Writeable
{
	public ShowCost ShowCost;

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeObject("ShowCost", ShowCost);
	}
}
