/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import java.util.ArrayList;
import java.util.Collection;

public class RatingList extends ArrayList<Rating>
{
	/* Construction */
	public static RatingList newInstance(Collection<? extends com.inetvod.common.dbdata.Rating> ratingList)
	{
		RatingList thisRatingList = new RatingList();

		thisRatingList.addAllRatingList(ratingList);

		return thisRatingList;
	}

	/* Implementation */
	public void addAllRatingList(Collection<? extends com.inetvod.common.dbdata.Rating> ratingList)
	{
		for(com.inetvod.common.dbdata.Rating rating : ratingList)
			add(new Rating(rating));
	}
}
