package com.inetvod.common.dbdata;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Bob
 * Date: Jul 7, 2004
 * Time: 10:03:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ProviderList extends ArrayList
{
	/* Constuction Methods */
	public static ProviderList find() throws Exception
	{
		return (ProviderList)Provider.getDatabaseAdaptor().selectManyByProc("Provider_GetAll", null);
	}
}
