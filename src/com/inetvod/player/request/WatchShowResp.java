/**
 * Copyright © 2004 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.player.request;

import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.dbdata.Show;

public class WatchShowResp implements Writeable
{
	protected String fShowURL;
	protected String fShowAccessKey;

	public String getShowURL() { return fShowURL; }
	public void setShowURL(String showURL) { fShowURL = showURL; }

	public String getShowAccessKey() { return fShowAccessKey; }
	public void setShowAccessKey(String showAccessKey) { fShowAccessKey = showAccessKey; }

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("ShowURL", fShowURL, Show.ShowURLMaxLength);
		writer.writeString("ShowAccessKey", fShowAccessKey, Show.ShowAccessKeyMaxLength);
	}
}
