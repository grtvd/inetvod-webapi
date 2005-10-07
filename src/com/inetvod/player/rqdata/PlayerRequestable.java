/**
 * Copyright © 2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.rqdata;

import com.inetvod.common.core.Requestable;

public interface PlayerRequestable extends Requestable
{
	StatusCode getStatusCode();
}
