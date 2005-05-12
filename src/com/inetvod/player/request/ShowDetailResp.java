/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.DataWriter;
import com.inetvod.player.rqdata.ShowDetail;

public class ShowDetailResp implements Writeable
{
	public ShowDetail ShowDetail;

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeObject("ShowDetail", ShowDetail);
	}
}
