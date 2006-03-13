/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.ShowDetail;

public class ShowDetailResp implements Writeable
{
	public ShowDetail ShowDetail;

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeObject("ShowDetail", ShowDetail);
	}
}
