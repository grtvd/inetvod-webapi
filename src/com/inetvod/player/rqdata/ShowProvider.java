/**
 * Copyright © 2004-2007 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
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
	public ShowProvider(ProviderID providerID, ShowCostList showCostList)
	{
		fProviderID = providerID;
		fShowCostList = showCostList;
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeList("ShowCost", fShowCostList);
	}
}

