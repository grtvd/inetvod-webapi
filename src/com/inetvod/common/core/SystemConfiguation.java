/**
 * Copyright � 2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.common.core;

//TODO: this class should be loaded from the database or from an XML file
public class SystemConfiguation
{
	/* Fields */
	private static SystemConfiguation fSystemConfiguation;

	@SuppressWarnings({"MagicNumber"})
	private long fSessionTimeoutMillis = (24 * 60 * 60 * 1000);		// 24 hours

	/* Getters & Setters */
	public long getSessionTimeoutMillis() { return fSessionTimeoutMillis; }

	/* Construction */
	public static SystemConfiguation getThe()
	{
		if(fSystemConfiguation == null)
			fSystemConfiguation = new SystemConfiguation();

		return fSystemConfiguation;
	}
}