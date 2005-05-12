package com.inetvod.common.dbdata;

import com.inetvod.common.core.Readable;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.DataReader;

/**
 * Created by IntelliJ IDEA.
 * User: Bob
 * Date: Jun 8, 2004
 * Time: 11:25:38 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DatabaseObject implements Readable, Writeable
{
	protected boolean fIsNewRecord;

	protected DatabaseObject(boolean isNewRecord)
	{
		fIsNewRecord = isNewRecord;
	}

	protected DatabaseObject(DataReader reader)
	{
		fIsNewRecord = false;
	}

	public boolean isNewRecord()
	{
		return fIsNewRecord;
	}

	public void setNewRecord(boolean newRecord)
	{
		fIsNewRecord = newRecord;
	}
}
