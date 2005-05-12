package com.inetvod.common.dbdata;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Bob
 * Date: Jul 7, 2004
 * Time: 10:16:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class CategoryList extends ArrayList
{
	/* Constuction Methods */
	public static CategoryList find() throws Exception
	{
		return (CategoryList)Category.getDatabaseAdaptor().selectManyByProc("Category_GetAll", null);
	}
}
