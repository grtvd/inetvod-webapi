/**
 * Copyright © 2005-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.Requestable;

public interface PlayerRequestable extends Requestable
{
	StatusCode getStatusCode();
}
