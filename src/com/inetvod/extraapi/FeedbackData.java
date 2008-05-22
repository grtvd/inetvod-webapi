/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.extraapi;

import java.lang.reflect.Constructor;

import com.inetvod.common.core.DataReader;
import com.inetvod.common.core.DataWriter;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.Writeable;

public class FeedbackData implements Readable, Writeable
{
	/* Constants */
	public static final Constructor<FeedbackData> CtorDataReader = DataReader.getCtor(FeedbackData.class);
	public static final String Name = "feedbackdata";

	private static final int MemberUserIDMaxLength = 64;
	private static final int SubjectMaxLength = 128;
	private static final int BodyMaxLength = 8192;

	/* Fields */
	private String fMemberUserID;
	private String fSubject;
	private String fBody;

	/* Getters and Setters */
	public String getMemberUserID() { return fMemberUserID; }
	public String getSubject() { return fSubject; }
	public String getBody() { return fBody; }

	/* Construction */

	public FeedbackData(DataReader reader) throws Exception
	{
		readFrom(reader);
	}

	/* Implementation */

	public void readFrom(DataReader reader) throws Exception
	{
		fMemberUserID = reader.readString("memberuserid", MemberUserIDMaxLength);
		fSubject = reader.readString("subject", SubjectMaxLength);
		fBody = reader.readString("body", BodyMaxLength);
	}

	public void writeTo(DataWriter writer) throws Exception
	{
		writer.writeString("memberuserid", fMemberUserID, MemberUserIDMaxLength);
		writer.writeString("subject", fSubject, SubjectMaxLength);
		writer.writeString("body", fBody, BodyMaxLength);
	}
}
