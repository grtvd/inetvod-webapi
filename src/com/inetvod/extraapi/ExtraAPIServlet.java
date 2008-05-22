/**
 * Copyright © 2008 iNetVOD, Inc. All Rights Reserved.
 * iNetVOD Confidential and Proprietary.  See LEGAL.txt.
 */
package com.inetvod.extraapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.inetvod.common.core.AppProperties;
import com.inetvod.common.core.Logger;
import com.inetvod.common.core.Mailer;
import com.inetvod.common.core.StrUtil;
import com.inetvod.common.core.XmlDataReader;
import com.inetvod.common.data.ProviderConnectionType;
import com.inetvod.common.data.ProviderID;
import com.inetvod.common.dbdata.ProviderConnection;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.HeadMethod;

public class ExtraAPIServlet extends HttpServlet
{
	private static final int RQST_METHOD_POS = 3;
	private static final int RQST_ARGS_POS = 4;
	private static final int GET_RQST_PARTS = 5;
	private static final int POST_RQST_PARTS = 4;
	private static final String PARAM_ENCODING = "UTF-8";

	private static final String ADD_CONTENT_METHOD = "ac";
	private static final String SEND_FEEDBACK_METHOD = "fb";

	private static final String SUCCESS_RESULT = "OK";
	private static final String FAILED_RESULT = "FAIL";
	private static final String BAD_REQUEST_RESULT = "BAD";
	private static final String ADD_CONTENT_DUPLICATE_RESULT = "DUP";

	private static final int ConnectionTimeoutMillis = 30000;	//TODO: config?
	private static final int SocketTimeoutMillis = 30000;	//TODO: config?

	public static String SendFeedbackToEmailProperty = "extraapi.sendfeedback.toemail";

	private static String SendFeedbackToEmail;

	@Override
	public void init() throws ServletException
	{
		// this code assumes PlayerXmlServlet initalizes most things

		AppProperties properties = AppProperties.getThe();
		SendFeedbackToEmail = properties.getProperty(SendFeedbackToEmailProperty);
	}

//	@Override
//	protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
//		throws ServletException, IOException
//	{
//		String result = FAILED_RESULT;
//
//		try
//		{
//			Method requestMethod = getRequestMethod(httpServletRequest.getRequestURL().toString(), true);
//
//			if(ADD_CONTENT_METHOD.equals(requestMethod.getName()))
//			{
//				result = addContent(requestMethod.getArgs());
//			}
//		}
//		catch(Exception ignore)
//		{
//		}
//
//		try
//		{
//			sendResult(httpServletResponse, result);
//		}
//		catch(Exception ignore)
//		{
//			httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//		}
//	}

	@Override
	protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
		throws ServletException, IOException
	{
		String result = FAILED_RESULT;

		try
		{
			Method requestMethod = getRequestMethod(httpServletRequest.getRequestURL().toString(), false);

			if(ADD_CONTENT_METHOD.equals(requestMethod.getName()))
			{
				result = addContent(getRequestBody(httpServletRequest));
			}
			else if(SEND_FEEDBACK_METHOD.equals(requestMethod.getName()))
			{
				result = sendFeedback(httpServletRequest);
			}
		}
		catch(Exception ignore)
		{
		}

		try
		{
			sendResult(httpServletResponse, result);
		}
		catch(Exception ignore)
		{
			httpServletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	private static Method getRequestMethod(String requestURL, boolean isGet) throws Exception
	{
		String[] parts = (new URL(requestURL)).getPath().split("/");
		int rqstParts = isGet ? GET_RQST_PARTS : POST_RQST_PARTS;

		if(parts.length != rqstParts)
			throw new Exception(String.format("Cannot parse request, parts != %d", rqstParts));

		if(isGet)
			return new Method(parts[RQST_METHOD_POS], URLDecoder.decode(parts[RQST_ARGS_POS], PARAM_ENCODING));
		return new Method(parts[RQST_METHOD_POS], null);
	}

	private static String getRequestBody(HttpServletRequest httpServletRequest) throws IOException
	{
		return (new BufferedReader(new InputStreamReader(httpServletRequest.getInputStream(), PARAM_ENCODING))).readLine();
	}

	private static void sendResult(HttpServletResponse httpServletResponse, String result) throws IOException
	{
		httpServletResponse.getWriter().write(result);
	}

	private static String addContent(String url) throws Exception
	{
		if(!StrUtil.hasLen(url))
			return FAILED_RESULT;

		if(ProviderConnection.findByConnectionURL(url) != null)
			return ADD_CONTENT_DUPLICATE_RESULT;

		if(!addContentConfirmURL(url))
			return FAILED_RESULT;

		ProviderConnection providerConnection = ProviderConnection.newInstance(ProviderID.Unknown,
			ProviderConnectionType.Rss2);
		providerConnection.setConnectionURL(url);
		providerConnection.update();

		Logger.logWarn(ExtraAPIServlet.class, "addContent", String.format("Successfully added url(%s)", url));
		return SUCCESS_RESULT;
	}

	private static boolean addContentConfirmURL(String url)
	{
		//TODO: load the URL response into the Rss2 object and validate, check for Enclosures of type video and audio
		try
		{
			// Send HTTP request to server
			HttpClient httpClient = new HttpClient();
			httpClient.getParams().setParameter("http.connection.timeout", ConnectionTimeoutMillis);	//http.connection.timeout
			httpClient.getParams().setParameter("http.socket.timeout", SocketTimeoutMillis);	//http.socket.timeout
			HeadMethod headMethod = new HeadMethod(url);
			headMethod.setFollowRedirects(true);

			try
			{
				int rc = httpClient.executeMethod(headMethod);
				if(rc != HttpStatus.SC_OK)
				{
					Logger.logWarn(ExtraAPIServlet.class, "addContentConfirmURL", String.format(
						"Bad result(%d) from url(%s)", rc, url));
					return false;
				}

				return true;
			}
			finally
			{
				headMethod.releaseConnection();
			}
		}
		catch(Exception e)
		{
			Logger.logWarn(ExtraAPIServlet.class, "addContentConfirmURL",
				String.format("Exception while validating url(%s)", url), e);
		}

		return false;
	}

	private static String sendFeedback(HttpServletRequest httpServletRequest)
	{
		FeedbackData feedbackData = null;

		try
		{
			feedbackData = readFeedbackData(httpServletRequest);

			if(!StrUtil.hasLen(feedbackData.getSubject()) || !StrUtil.hasLen(feedbackData.getBody()))
				return BAD_REQUEST_RESULT;

			StringBuilder sb = new StringBuilder();
			sb.append(String.format("Member: %s\n", feedbackData.getMemberUserID()));
			sb.append(String.format("IP: %s\n", httpServletRequest.getRemoteAddr()));
			sb.append(String.format("Subject: %s\n", feedbackData.getSubject()));
			sb.append(String.format("Body: %s\n", feedbackData.getBody()));

			Mailer.sendMail(SendFeedbackToEmail, "Feedback", sb.toString());
			return SUCCESS_RESULT;
		}
		catch(Exception e)
		{
			Logger.logWarn(ExtraAPIServlet.class, "sendFeedback", String.format("Failed to email, Subject(%s), Body(%s)",
				(feedbackData != null) ? feedbackData.getSubject() : "", (feedbackData != null) ? feedbackData.getBody() : ""),
				e);
		}

		return FAILED_RESULT;
	}

	private static FeedbackData readFeedbackData(HttpServletRequest httpServletRequest) throws Exception
	{
		XmlDataReader reader = new XmlDataReader(httpServletRequest.getInputStream());
		return reader.readObject(FeedbackData.Name, FeedbackData.CtorDataReader);
	}

	private static class Method
	{
		private String fName;
		private String fArgs;

		public String getName() { return fName; }
		public String getArgs() { return fArgs; }

		public Method(String name, String args)
		{
			fName = name;
			fArgs = args;
		}
	}
}
