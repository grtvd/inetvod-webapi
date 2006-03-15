/**
 * Copyright © 2004-2006 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.common.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.ByteOrder;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vladium.utils.timing.ITimer;
import com.vladium.utils.timing.TimerFactory;

public abstract class ServletFulfiller
{
	protected HttpServletRequest fHttpServletRequest;
	protected HttpServletResponse fHttpServletResponse;

	protected ServletFulfiller(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
	{
		fHttpServletRequest = httpServletRequest;
		fHttpServletResponse = httpServletResponse;
	}

	public abstract DataFormat getRequestDataFormat();

	public void fulfill() throws Exception
	{
		Requestable request = null;
		Writeable response = null;
		//Date startTime = new Date();
		ITimer timer = TimerFactory.newTimer();
		boolean logged = false;

		timer.start();
		try
		{
			request = readRequestable();
		}
		catch(Exception e)
		{
			response = createResponseFromException(request, e);

			logRequest(false, "Failed reading request", fHttpServletRequest.getInputStream(), timer, e);
			logged = true;

			// if we have a valid response, don't propogate exception
			if(response == null)
				throw e;
		}

		try
		{
			if((response == null) && (request != null))
				response = request.fulfillRequest();
		}
		catch(Exception e)
		{
			response = createResponseFromException(request, e);

			logRequest(false, "Failed fulfilling request", null, request, response, timer, e);
			logged = true;

			// if we have a valid response, don't propogate exception
			if(response == null)
				throw e;
		}

		try
		{
			writeWriteable(response);
		}
		catch(Exception e)
		{
			logRequest(false, "Failed writing response", null, request, response, timer, e);
			throw e;
		}

		if(!logged)
			logRequest(request, response, timer);
	}

	protected abstract Writeable createResponseFromException(Requestable requestable, Exception e);

	protected Requestable readRequestable() throws Exception
	{
		DataFormat dataFormat = getRequestDataFormat();

		if(dataFormat.equals(DataFormat.XML))
			return readRequestableFromXmlStream();
		else if(dataFormat.equals(DataFormat.Binary))
			return readRequestableFromBinaryStream();

		throw new Exception("Unknown DataFormat: " + dataFormat.toString());
	}

	protected Requestable readRequestableFromXmlStream() throws Exception
	{
		XmlDataReader reader;
		Requestable requestable;

		reader = new XmlDataReader(fHttpServletRequest.getInputStream());
		//reader = new XmlDataReader(fHttpServletRequest.getReader());
		requestable = readRequestableFromReader(reader);

		return requestable;
	}

	protected Requestable readRequestableFromBinaryStream() throws Exception
	{
		BinaryDataReader reader;
		Requestable requestable;

		reader = new BinaryDataReader(fHttpServletRequest.getInputStream(), ByteOrder.BIG_ENDIAN);
		requestable = readRequestableFromReader(reader);

		return requestable;
	}

	/// <summary>
	/// Read a Requestable object from its name
	/// </summary>
	/// <param name="className"></param>
	/// <returns></returns>
	protected abstract Requestable readRequestableFromReader(DataReader dataReader) throws Exception;

	protected void writeWriteable(Writeable writeable) throws Exception
	{
		DataFormat dataFormat = getRequestDataFormat();

		if(dataFormat.equals(DataFormat.XML))
			writeWriteableToXmlPrintWriter(writeable);
		else if(dataFormat.equals(DataFormat.Binary))
			writeWriteableToBinaryStream(writeable);
		else
			throw new Exception("Unknown DataFormat: " + dataFormat.toString());
	}

	protected void writeWriteableToXmlStream(Writeable writeable) throws Exception
	{
		XmlDataWriter writer;

		writer = new XmlDataWriter(fHttpServletResponse.getOutputStream());
		writeWriteableToWriter(writeable, writer);
	}

	protected void writeWriteableToXmlPrintWriter(Writeable writeable) throws Exception
	{
		XmlDataWriter writer;

		fHttpServletResponse.setContentType("text/xml; charset=UTF-8");
		writer = new XmlDataWriter(fHttpServletResponse.getWriter(), fHttpServletResponse.getCharacterEncoding());
		writeWriteableToWriter(writeable, writer);
		//writer.flush();
	}

	protected void writeWriteableToBinaryStream(Writeable writeable) throws Exception
	{
		BinaryDataWriter writer;

		writer = new BinaryDataWriter(fHttpServletResponse.getOutputStream(), ByteOrder.BIG_ENDIAN);
		writeWriteableToWriter(writeable, writer);
	}

	protected void writeWriteableToWriter(Writeable writeable, DataWriter writer) throws Exception
	{
		String[] nameParts = writeable.getClass().getName().split("\\.");
		writer.writeObject(nameParts[nameParts.length - 1], writeable);
	}

	protected abstract String getRequestType(Requestable requestable);

	protected void logRequest(boolean success, String msg, String requestType, InputStream requestStream,
		InputStream responseStream, String requestFileExt, String responseFileExt, ITimer timer, Exception exception)
	{
		PrintWriter writer = null;

		try
		{
			timer.stop();
			double milliSecs = timer.getDuration();

			StringBuffer sb = new StringBuffer();
			String fileDir = "c:\\temp\\iNetVOD\\requests\\";
			String baseFileName = String.format ("%s-%d", (new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSSS")).format(
				new Date(), sb, new FieldPosition(DateFormat.YEAR_FIELD)).toString(), Thread.currentThread().getId());
			String fileName = (new File(fileDir, baseFileName)).getPath();

			if(requestStream != null)
				StreamUtil.streamToFile(requestStream, fileName + "_Rqst" + requestFileExt);

			if(responseStream != null)
				StreamUtil.streamToFile(responseStream, fileName + "_Resp" + responseFileExt);

			sb = new StringBuffer();
			sb.append(String.format("result:%s; ", (success ? "Success" : "FAILED")));
			if((msg != null) && (msg.length() > 0))
				sb.append(String.format("msg:%s; ", msg));
			if((requestType != null) && (requestType.length() > 0))
				sb.append(String.format("type:%s; ", requestType));
			sb.append(String.format("time:%.4f; ", milliSecs));
			sb.append(String.format("file:%s; ", baseFileName));
			if(exception == null)
				Logger.logInfo(this, "logRequest", sb.toString());
			else
				Logger.logErr(this, "logRequest", sb.toString(), exception);
		}
		catch(Exception e)
		{
			if(writer != null)
				try { writer.close(); } catch(Exception e2) {}
			Logger.logErr(this, "logRequest", "Failure during logging", e);
		}
	}

	protected void logRequest(boolean success, String msg, InputStream requestStream,
		ITimer timer, Exception exception)
	{
		logRequest(success, msg, null, requestStream, null, ".raw", null, timer, exception);
	}

	protected void logRequest(boolean success, String msg, Requestable request,
		ITimer timer, Exception exception) throws Exception
	{
		XmlDataWriter requestWriter = null;
		try
		{
			ByteArrayOutputStream requestStream = new ByteArrayOutputStream();
			requestWriter = new XmlDataWriter(requestStream);
			writeWriteableToWriter(request, requestWriter);
			requestWriter.close();

			logRequest(success, getRequestType(request), null, new ByteArrayInputStream(requestStream.toByteArray()),
				null, ".xml", null, timer, exception);
		}
		catch(Exception e)
		{
			Logger.logErr(this, "logRequest", "Failure during logging", e);
		}
		finally
		{
			if(requestWriter != null)
				requestWriter.close();
		}
	}

	protected void logRequest(boolean success, String msg, String requestType, Requestable request, Writeable response,
		ITimer timer, Exception exception) throws Exception
	{
		XmlDataWriter requestWriter = null;
		XmlDataWriter responseWriter = null;
		try
		{
			ByteArrayOutputStream requestStream = new ByteArrayOutputStream();
			requestWriter = new XmlDataWriter(requestStream);
			writeWriteableToWriter(request, requestWriter);
			requestWriter.flush();

			ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
			responseWriter = new XmlDataWriter(responseStream);
			writeWriteableToWriter(response, responseWriter);
			responseWriter.flush();

			ByteArrayInputStream requestInStream = new ByteArrayInputStream(requestStream.toByteArray());
			ByteArrayInputStream responseInStream = new ByteArrayInputStream(responseStream.toByteArray());

			logRequest(success, msg, requestType, requestInStream, responseInStream, ".xml", ".xml", timer, exception);
		}
		catch(Exception e)
		{
			Logger.logErr(this, "logRequest", "Failure during logging", e);
		}
		finally
		{
			if(requestWriter != null)
				requestWriter.close();
			if(responseWriter != null)
				responseWriter.close();
		}
	}

	protected void logRequest(Requestable request, Writeable response, ITimer timer) throws Exception
	{
		logRequest(true, null, getRequestType(request), request, response, timer, null);
	}
}
