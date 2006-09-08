/**
 * Copyright � 2005-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.RentedShowList;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.ShowList;
import com.inetvod.player.rqdata.DownloadShow;
import com.inetvod.player.rqdata.StatusCode;


public class DownloadShowListRqst extends SessionRequestable
{
	/* Constuction Methods */
	public DownloadShowListRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		DownloadShowListResp response;
		RentedShowList rentedShowList;
		ShowList showList;
		Show show;

		response = new DownloadShowListResp();

		rentedShowList = RentedShowList.findByMemberID(fMemberID);
		showList = ShowList.findByRentedShowMemberID(fMemberID);

		for(RentedShow rentedShow : rentedShowList)
		{
			//TODO: need to filter for downloadable shows only
			show = showList.get(rentedShow.getShowID());
			response.getDownloadShowList().add(new DownloadShow(rentedShow, show));
		}

		fStatusCode = StatusCode.sc_Success;
		return response;
	}

	public void readFrom(DataReader reader) throws Exception
	{
		// No Fields
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		// No Fields
	}
}
