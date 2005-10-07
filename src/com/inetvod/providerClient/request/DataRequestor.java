/**
 * Copyright � 2005 iNetVOD, Inc. All Rights Reserved.
 * Confidential and Proprietary
 */
package com.inetvod.providerClient.request;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

import com.inetvod.common.core.Logger;
import com.inetvod.common.core.Readable;
import com.inetvod.common.core.Writeable;
import com.inetvod.common.core.XmlDataReader;
import com.inetvod.common.core.XmlDataWriter;
import com.inetvod.providerClient.rqdata.Authenticate;
import com.inetvod.providerClient.rqdata.StatusCode;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class DataRequestor
{
	/* Constants */
	private static final String Version = "1.0.0";

	/* Fields */
	private String fRequestURL;
	private String fAdminUserID;
	private String fAdminPassword;
	private StatusCode fStatusCode;

	/* Getters & Setters */
	public StatusCode getStatusCode() { return fStatusCode; }

	/* Construction */
	private DataRequestor(String requestURL, String adminUserID, String adminPassword)
	{
		fRequestURL = requestURL;
		fAdminUserID = adminUserID;
		fAdminPassword = adminPassword;
	}

	public static DataRequestor newInstance(String requestURL, String adminUserID, String adminPassword)
	{
		return new DataRequestor(requestURL, adminUserID, adminPassword);
	}

	/* Implementation */
	private INetVODProviderRqst createHeader(Writeable payload)
	{
		INetVODProviderRqst request = INetVODProviderRqst.newInstance(Version, UUID.randomUUID().toString());

		Authenticate authenticate = Authenticate.newInstance(fAdminUserID, fAdminPassword);
		request.setAuthenticate(authenticate);

		RequestData requestData = RequestData.newInstance(payload);
		request.setRequestData(requestData);

		return request;
	}

	private Readable sendRequest(Writeable payload)
	{
		INetVODProviderRqst iNetVODProviderRqst;
		INetVODProviderResp iNetVODProviderResp;
		String requestName = "INetVODProviderRqst";
		String responseName = "INetVODProviderResp";

		try
		{
			// create request envelop
			iNetVODProviderRqst = createHeader(payload);

			// Convert 'writeable' to XML
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			XmlDataWriter dataWriter = new XmlDataWriter(printWriter, "UTF-8");
			dataWriter.writeObject(requestName, iNetVODProviderRqst);
			String requestXml = stringWriter.toString();
			//System.out.println(requestXml);

			// Send HTTP request to server
			HttpClient httpClient = new HttpClient();
			String contentType = "text/xml; charset=ISO-8859-1";
			PostMethod postMethod = new PostMethod(fRequestURL);
			postMethod.setRequestEntity(new StringRequestEntity(requestXml, contentType, null));

			postMethod.setRequestHeader("Content-type", contentType);
			try
			{
				httpClient.executeMethod(postMethod);
				InputStream responseStream = postMethod.getResponseBodyAsStream();

				// Convert response XML to FieldReadable
				XmlDataReader dataReader = new XmlDataReader(responseStream);
				iNetVODProviderResp = dataReader.readObject(responseName, INetVODProviderResp.CtorDataReader);
				fStatusCode = iNetVODProviderResp.getStatusCode();

				return iNetVODProviderResp.getResponseData();
			}
			finally
			{
				postMethod.releaseConnection();
			}
		}
		catch(Exception e)
		{
			Logger.logInfo(this, "sendRequest", e);
			fStatusCode = StatusCode.sc_GeneralError;
		}

		return null;
	}

	public StatusCode pingServer()
	{
		sendRequest(PingRqst.newInstance());

		if(!StatusCode.sc_Success.equals(fStatusCode))
			Logger.logInfo(this, "pingServer", String.format("bad StatusCode(%d) returned", StatusCode.convertToInt(fStatusCode)));
		return fStatusCode;
	}
}
