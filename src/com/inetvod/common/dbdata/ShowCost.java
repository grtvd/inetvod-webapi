/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Money;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.Writeable;

import java.lang.reflect.Constructor;

public class ShowCost implements Readable, Writeable
{
	/* Constants */
	public static Constructor CtorDataReader = DataReader.getCtor(ShowCost.class);
	public static final int DescriptionMaxLength = 32;

	/* Properties */
	protected ShowCostType fShowCostType;
	protected Money fCost;
	protected String fCostDisplay;

	/* Getters and Setters */
	public ShowCostType getShowCostType() { return fShowCostType; }
	public void setShowCostType(ShowCostType showCostType) { fShowCostType = showCostType; }

	public Money getCost() { return fCost; }
	public void setCost(Money cost) { fCost = cost; }

	public String getCostDisplay() { return fCostDisplay; }
	public void setCostDisplay(String description) { fCostDisplay = description; }

	/* Constuction Methods */
	public ShowCost()
	{
	}

	public ShowCost(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	public void readFrom(DataReader reader) throws Exception
	{
		fShowCostType = ShowCostType.convertFromString(reader.readString("ShowCostType", ShowCostType.MaxLength));
		fCost = (Money) reader.readObject("Cost", Money.CtorDataFiler);
		fCostDisplay = reader.readString("CostDisplay", DescriptionMaxLength);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("ShowCostType", ShowCostType.convertToString(fShowCostType), ShowCostType.MaxLength);
		writer.writeObject("Cost", fCost);
		writer.writeString("CostDisplay", fCostDisplay, DescriptionMaxLength);
	}
}