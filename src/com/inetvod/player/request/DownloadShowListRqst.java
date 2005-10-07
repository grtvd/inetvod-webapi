/**
 * Copyright © 2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import java.util.Iterator;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.common.dbdata.RentedShowList;
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
		RentedShow rentedShow;
		Iterator iterator;

		response = new DownloadShowListResp();

		rentedShowList = RentedShowList.findByMemberID(fMemberID);

		iterator = rentedShowList.iterator();
		while(iterator.hasNext())
		{
			rentedShow = (RentedShow)iterator.next();

			//TODO: need to filter for downloadable shows only
			response.getDownloadShowList().add(new DownloadShow(rentedShow));
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
