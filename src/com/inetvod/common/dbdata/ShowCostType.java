/**
 * Copyright � 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.dbdata;

public class ShowCostType
{
	public static final int MaxLength = 32;

	public static final ShowCostType Free = new ShowCostType("Free");
	public static final ShowCostType Subscription = new ShowCostType("Subscription");
	public static final ShowCostType PayPerView = new ShowCostType("PayPerView");

	private final String fValue;

	private ShowCostType(String value)
	{
		fValue = value;
	}

	public String toString()
	{
		return fValue;
	}

	public static ShowCostType convertFromString(String value)
	{
		if((value == null) || (value.length() == 0))
			return null;

		if(Free.fValue.matches(value))
			return Free;
		if(Subscription.fValue.matches(value))
			return Subscription;
		if(PayPerView.fValue.matches(value))
			return PayPerView;

		throw new IllegalArgumentException("bad value(" + value + ")");
	}

	public static String convertToString(ShowCostType showCostType)
	{
		if(showCostType == null)
			return null;
		return showCostType.toString();
	}
}
