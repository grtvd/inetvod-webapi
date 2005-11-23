/**
 * Copyright © 2004-2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.data.ShowCostList;

public class ShowProvider implements Writeable
{
	/* Fields */
	protected ProviderID fProviderID;
	protected ShowCostList fShowCostList;

	/* Getters and Setters */
	public ProviderID getProviderID() { return fProviderID; }
	//public ShowCost getShowCost() { return fShowCost; }

	/* Constuction Methods */
	public ShowProvider(com.inetvod.common.dbdata.ShowProvider showProvider)
	{
		fProviderID = showProvider.getProviderID();

		//TODO: update ShowProvider to store ShowCost list in db
		fShowCostList = new ShowCostList();
		fShowCostList.add(showProvider.getShowCost());
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeList("ShowCost", fShowCostList);
	}
}

