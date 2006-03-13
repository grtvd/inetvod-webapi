/**
 * Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.player.rqdata.DownloadShowList;

public class DownloadShowListResp implements Writeable
{
	/* Fields */
	protected DownloadShowList fDownloadShowList = new DownloadShowList();

	/* Getters and Setters */
	public DownloadShowList getDownloadShowList() { return fDownloadShowList; }

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeList("DownloadShow", fDownloadShowList);
	}
}
