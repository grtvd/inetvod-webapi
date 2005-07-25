package com.inetvod.player.rqdata;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.ProviderID;
import com.inetvod.common.dbdata.ShowCost;

public class ShowProvider implements Writeable
{
	/* Fields */
	protected ProviderID fProviderID;
	protected ShowCost fShowCost;

	/* Getters and Setters */
	public ProviderID getProviderID() { return fProviderID; }
	public ShowCost getShowCost() { return fShowCost; }

	/* Constuction Methods */
	public ShowProvider(com.inetvod.common.dbdata.ShowProvider showProvider)
	{
		fProviderID = showProvider.getProviderID();
		fShowCost = showProvider.getShowCost();
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeDataID("ProviderID", fProviderID, ProviderID.MaxLength);
		writer.writeObject("ShowCost", fShowCost);
	}
}

