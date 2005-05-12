/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.StatusCode;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.dbdata.ShowList;
import com.inetvod.common.dbdata.Show;
import com.inetvod.common.dbdata.RentedShowList;
import com.inetvod.common.dbdata.RentedShow;
import com.inetvod.player.rqdata.RentedShowSearch;

import java.util.Iterator;

public class RentedShowListRqst extends SessionRequestable
{
	/* Constuction Methods */
	public RentedShowListRqst(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public Writeable fulfillRequest() throws Exception
	{
		RentedShowListResp response;
		RentedShowList rentedShowList;
		ShowList showList;
		Show show;
		RentedShow rentedShow;
		Iterator iterator;

		response = new RentedShowListResp();

		rentedShowList = RentedShowList.findByMemberID(fMember.getMemberID());
		showList = ShowList.findByRentedShowMemberID(fMember.getMemberID());

		iterator = rentedShowList.iterator();
		while(iterator.hasNext())
		{
			rentedShow = (RentedShow)iterator.next();

			show = showList.get(rentedShow.getShowID());
			response.getRentedShowSearchList().add(new RentedShowSearch(rentedShow, show));
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
