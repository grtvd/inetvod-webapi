/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.ShowSearchList;

public class ShowSearchResp implements Writeable
{
	public ShowSearchList ShowSearchList = new ShowSearchList();
	public Boolean ReachedMax;

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeList("ShowSearch", ShowSearchList);
		writer.writeBoolean("ReachedMax", ReachedMax);
	}
}
