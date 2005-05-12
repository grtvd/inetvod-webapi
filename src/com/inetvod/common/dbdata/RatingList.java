package com.inetvod.common.dbdata;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Bob
 * Date: Jul 7, 2004
 * Time: 10:16:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class RatingList extends ArrayList
{
	/* Constuction Methods */
	public static RatingList find() throws Exception
	{
		return (RatingList)Rating.getDatabaseAdaptor().selectManyByProc("Rating_GetAll", null);
	}
}
