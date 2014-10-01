package com.onetesthub.perf.core.jmxgenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.onetesthub.perf.core.config.ConfigTest;
import com.onetesthub.perf.core.processor.response.ResponseIdentifier;
import com.onetesthub.perf.core.proxy.FilterRequestsType;

import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarNameValuePair;
import net.lightbody.bmp.core.har.HarPostDataParam;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import net.lightbody.bmp.proxy.ProxyServer;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class ProcessEntry {

	ProxyServer server;
	private List<HarEntry> filteredEntrylist = new ArrayList<HarEntry>();
	private HarRequest harRequest;
	private String httpSamplerStart;
	private String httpSamplerProp;
	private String httpSamplerArguments;
	private String httpSamplerRawBody;
	private String DOMULTIPARTPOST = "false";
	private String xmlRequest;
	private String PageName;
	private String postRawBody;
	private String httpSamplerHeader;
	private String httpSamplerRegexExtractor;
	private String contentType;
	private String paramType;
	private HarResponse harResponse;

	HashMap<String, String> requestParamMap = new HashMap<String, String>();
	HashMap<String, String> requestValueMap = new HashMap<String, String>();
	HashMap<String, String> requestRegexMap = new HashMap<String, String>();

	HashMap<String, ResponseIdentifier> respRegexMap = new HashMap<String, ResponseIdentifier>();

	ParamProcessor pp;

	Logger log = Logger.getLogger(this.getClass().getName());

	ProcessEntry(ProxyServer server, ParamProcessor pp) {
		this.server = server;
		this.pp = pp;
		paramType = pp.getParamType();

	}

	public List<HarEntry> getEnteries() {

		List<HarEntry> rawEntrylist = server.getHar().getLog().getEntries();

		System.out.println("****Size BEFORE filter is :" + rawEntrylist.size());

		return rawEntrylist;
	}

	public void filterEnteries() {

		System.out.println("******IN FILTERENTRY*****");

		List<HarEntry> rawEntrylist = getEnteries();

		int listLength = rawEntrylist.size();

		long stime = 0, etime = 0, ltime = 0, btime = 0;

		for (int i = 0; i < listLength; i++) {

			HarEntry entry = rawEntrylist.get(i);

			harRequest = entry.getRequest();

			String urlpath = getRequestUrlpath();

			if (i == 0) {
				btime = entry.getStartedDateTime().getTime();
				ltime = btime + entry.getTime();
			}

			else {
				stime = entry.getStartedDateTime().getTime();
				etime = stime + entry.getTime();
			}

			if (etime > ltime) {
				ltime = etime;
			}

			if (i == listLength - 1) {
				// etime = entry.getStartedDateTime();

				// long ttime = etime.getTime() - stime.getTime() +
				// entry.getTime();

				double ttime = ltime - btime + entry.getTime();

				ttime = ttime / 1000;

				// System.out.println("t1 Total time is *** :" +
				// etime.getTime());
				//
				// System.out.println("t2 Total time is *** :" +
				// stime.getTime());

				System.out.println("Total time for page " + PageName + "is  :"
						+ ttime);

				log.info("<tr>");
				log.info("<td>" + PageName + "</td>");
				log.info("<td>" + ttime + "</td>");
				log.info("</tr>");

				// log.info("Total time for page " + PageName + "is  :" +
				// ttime);
			}

			// System.out.println("***entry getTime for urlpath" + urlpath +
			// "is :" + entry.getTime());

			int flag = 0;

			for (FilterRequestsType filter : FilterRequestsType.values()) {

				if (urlpath.endsWith(filter.getRequestsType())
						|| !(urlpath.contains(ConfigTest
								.getValue("HTTPHOSTNAME")))) {

					flag++;
				}
			}

			if (flag != 0) {
				// System.out.println("url Match is :" + harRequest.getUrl());
			}

			else if (flag == 0) {

				filteredEntrylist.add(entry);
				// System.out.println("url not Match is :"
				// + harRequest.getUrl());

			}

		}

		System.out.println("***Size after filter is :"
				+ filteredEntrylist.size());

		// return filteredEntrylist;

	}

	public String checkRequestType() {

		String httpMethod = harRequest.getMethod();

		if (httpMethod.equals("GET")) {
			return "GET";
		}

		else if (httpMethod.equals("POST")) {
			if (harRequest.getPostData().getParams() == null) {
				// DOMULTIPARTPOST = "true";
				return "POST";
			}

			else
				return "POST";
		}

		return "NOT FOUND";

	}

	public void processRequest(String pageName) {

		//process all subrequests for one UI click. adds transaction controller first then process all subrequests and then adds end transactioncontroller
		
		this.PageName = pageName;

		System.out.println("IN PROCESSREQUEST");

		JMXProcessor.writeToJMX(JMXFileFormat.TransactionControllerStart
				.replace("TRANSACTIONNAME", PageName));

		HarWriter harwriter = new HarWriter();

		if (harwriter.scriptDebugEnabled()) {
			harwriter.writeToHar(server.getHar());

		}

		filterEnteries();

		for (HarEntry entry : filteredEntrylist) {

			requestParamMap.clear();
			postRawBody = null;
			httpSamplerHeader = null;
			contentType = null;

			harRequest = entry.getRequest();
			harResponse = entry.getResponse();

			String reqType = checkRequestType();

			if (reqType.equals("GET")) {
				getRequestQueryParam();
			}

			else if (reqType.equals("POST") && DOMULTIPARTPOST.equals("false")) {

				if (harRequest.getPostData().getMimeType()
						.contains("application/json;charset=UTF-8")) {

					contentType = "json";
					getPostRawBody();

				}

				else {
					getPostParam();
				}
			}

			else if (reqType.equals("POST") && DOMULTIPARTPOST.equals("true")) {

				getMultipartPostParam();

			}

			else {
				System.out
						.println("Error **************REQUEST TYPE NOT FOUND***************");
			}

			processRequestXML();
		}

		JMXProcessor.writeToJMX(JMXFileFormat.TransactionControllerEnd);

	}

	public void NameTransaction(HarRequest harrequest, String transactionName) {

	}

	public String getRequestUrlpath() {

		String urlpath;
		String url = harRequest.getUrl();
		if (url.contains("?")) {
			urlpath = url.substring(0, url.indexOf("?"));
		}

		else {
			urlpath = url.substring(0, url.length());
		}

		return urlpath;

	}

	public String filterUrlPath() {

		String urlpath = getRequestUrlpath();

		// strip off the http://hostname from url

		if (urlpath.contains(ConfigTest.getValue("HTTPHOSTNAME"))) {

			urlpath = urlpath.substring(urlpath.indexOf("/", 7),
					urlpath.length());

			// System.out.println("****final url to jmx is :" + urlpath);
			return urlpath;
		}

		return urlpath;

	}

	public String processRequestHeader() {

		StringBuilder httpSamplerHeaderBuilder = new StringBuilder();

		String httpHeader = JMXFileFormat.httpSamplerHeader;

		for (int i = 0; i < harRequest.getHeaders().size(); i++) {
			String headerName = harRequest.getHeaders().get(i).getName();
			String headerValue = harRequest.getHeaders().get(i).getValue();

			if (headerName.equals("Content-Type")) {
				httpSamplerHeaderBuilder.append(httpHeader.replace(
						"HEADERNAME", headerName).replace("HEADERVALUE",
						headerValue));

			}
		}

		return httpSamplerHeaderBuilder.toString();

	}

	public String processRequestUrlpath() {

		httpSamplerStart = JMXFileFormat.httpSamplerStart;

		String urlpath = filterUrlPath();

		return httpSamplerStart = (httpSamplerStart.replaceAll("REQUESTNAME",
				urlpath)).replaceAll("FLAGPOSTRAWBODY", "false");

	}

	public HashMap<String, String> getRequestQueryParam() {

		requestParamMap.clear();

		List<HarNameValuePair> queryHarList = harRequest.getQueryString();

		for (HarNameValuePair entry : queryHarList) {

			requestParamMap.put(entry.getName(), entry.getValue());

		}

		return requestParamMap;

	}

	public HashMap<String, String> getPostParam() {

		// requestParamMap.clear();

		List<HarPostDataParam> postHarList = harRequest.getPostData()
				.getParams();

		for (HarPostDataParam entry : postHarList) {

			requestParamMap.put(entry.getName(), entry.getValue());

		}

		return requestParamMap;

	}

	public HashMap<String, String> getMultipartPostParam() {

		// requestParamMap.clear();

		if (harRequest.getPostData().getMimeType()
				.contains("application/json;charset=UTF-8")) {

		}

		else {
			String[] multipartpost = harRequest.getPostData().getText()
					.split("form-data");

			for (int i = 1; i < multipartpost.length; i++) {

				if (!multipartpost[i].contains("filename")) {

					String[] paramPair = multipartpost[i].split("\r");
					String paramName = paramPair[0];
					paramName = paramName
							.substring(paramName.indexOf("\"") + 1,
									paramName.length() - 1);
					String paramValue = paramPair[2].substring(1);

					requestParamMap.put(paramName, paramValue);
				}
			}
		}
		return requestParamMap;

	}

	public void getPostRawBody() {

		// requestParamMap.clear();
		// postRawBody = null;

		StringBuilder httpSamplerRawBodyBuilder = new StringBuilder();

		postRawBody = harRequest.getPostData().getText();

	}

	public String processRequestParams() {

		// HashMap<String, String> queryParamMap = getRequestQueryParam();

		String key, value, paramKey, paramValue;

		StringBuilder httpSamplerArgumentsBuilder = new StringBuilder();

		HashMap<String, String> paramMap = pp.getParamMap();
		HashMap<String, String> valueMap = pp.getValueMap();
		HashMap<String, String> regexMap = pp.getRegexMap();

		// System.out.println("******ValueMap is :" + valueMap.size());

		// System.out.println("******ParamMap is :" + paramMap.size());

		// System.out.println("******regexMap is :" + regexMap.size());

		if (!requestParamMap.isEmpty()) {

			for (Entry<String, String> entry : requestParamMap.entrySet()) {

				key = entry.getKey();
				value = entry.getValue();

				// logic to check and replace request param name and param
				// values
				if (paramMap.containsKey(key)) {
					value = paramMap.get(key);

					System.out.println("paramMap key value pair is :" + key
							+ ":" + value);
				}
				if (valueMap.containsKey(value)) {
					value = valueMap.get(value);

					System.out.println("valueMap key value pair is :" + key
							+ ":" + value);
				}

				httpSamplerArgumentsBuilder
						.append(JMXFileFormat.httpSamplerArgumentsRequest
								.replace("HTTPPARAMETER", key).replace(
										"HTTPVALUE", value));
			}

		}

		else if (postRawBody != null) {

			System.out.println("BEFORELOOP*********postrawbody :is"
					+ postRawBody);

			for (Entry<String, String> entry : paramMap.entrySet()) {

				postRawBody = postRawBody.replaceAll(entry.getKey(),
						entry.getValue());

				System.out.println("*********postrawbody after parammap  :is"
						+ postRawBody);

			}

			for (Entry<String, String> entry : valueMap.entrySet()) {

				key = entry.getKey();
				value = entry.getValue();

				System.out.println("regex key and value is : " + key + ":"
						+ value);

				postRawBody = postRawBody.replaceAll(key, value);

				System.out.println("*********postrawbody after valueMap :is"
						+ postRawBody);

			}

			StringBuffer sb = new StringBuffer();

			for (Entry<String, String> entry : regexMap.entrySet()) {

				key = entry.getKey();
				value = entry.getValue();

				System.out.println("regex key and value is : " + key + ":"
						+ value);

				String pattern = key;

				System.out.println("Pattern to match is:" + pattern);

				// Create a Pattern object
				Pattern pobj = Pattern.compile(pattern);

				// Now create matcher object.
				Matcher mobj = pobj.matcher(postRawBody);

				int count = 0;
				while (mobj.find()) {

					System.out.println("***match found and count :"
							+ mobj.groupCount());

					System.out.println("Found value0: " + mobj.group(0));
					System.out.println("Found value1: " + mobj.group(1));

					// if (count++ == 1) {
					// mobj.appendReplacement(sb, value);
					// }
					postRawBody = postRawBody.replaceAll(mobj.group(1), value);

				}
				System.out.println("after replacing match string is "
						+ postRawBody);

				// mobj.appendTail(sb);

				// postRawBody=sb.toString();

				System.out.println("*********postrawbody after regexMap :is"
						+ postRawBody);

			}

			httpSamplerArgumentsBuilder
					.append(JMXFileFormat.httpSamplerBodyRawRequest.replace(
							"HTTPBODYRAW", postRawBody));

			httpSamplerStart = httpSamplerStart.replaceAll("false", "true");

		}

		return httpSamplerArgumentsBuilder.toString();

	}

	public String processRequestProp() {

		StringBuilder httpSamplerPropBuilder = new StringBuilder();

		String urlpath = filterUrlPath();
		String urlPathParam = pp.getUrlPathParam();

		if (urlPathParam != null) {

			urlpath = urlPathParam;

		}

		httpSamplerPropBuilder.append(JMXFileFormat.httpSamplerPropRequest
				.replace("HTTPURL", urlpath)
				.replace("HTTPMETHOD", checkRequestType())
				.replace("DOMULTIPARTPOST", DOMULTIPARTPOST));

		return httpSamplerPropBuilder.toString();

	}

	public String processResponseRegex() {

		respRegexMap = pp.getRespRegexMap();

		String key, pattern, ord, responsetext;

		StringBuilder httpSamplerRegexBuilder = new StringBuilder();

		boolean found = false;
		ResponseIdentifier respIdenValue;

		if (respRegexMap != null) {

			for (Entry<String, ResponseIdentifier> regexentry : respRegexMap
					.entrySet()) {

				found = false;
				key = regexentry.getKey();
				respIdenValue = regexentry.getValue();
				pattern = respIdenValue.getRegex();
				ord = respIdenValue.getOrd();

				responsetext = harResponse.getContent().getText();

				if (responsetext != null) {

					System.out.println("Pattern to match is:" + pattern);

					// Create a Pattern object
					Pattern pobj = Pattern.compile(pattern);

					// Now create matcher object.
					Matcher mobj = pobj.matcher(responsetext);

					int count = 1;
					while (mobj.find()) {

						if (count == Integer.parseInt(ord)) {
							System.out
									.println("***match and coourence found as  :"
											+ mobj.group(1) + ":" + count);
							found = true;
							if (found) {

								httpSamplerRegexBuilder
										.append(JMXFileFormat.RegExtractor
												.replace("REGNAME", key)
												.replace("REGEXVARNAME", key)
												.replace("REGEXPATTERN",
														pattern)
												.replace("REGEXORDINAL", ord));

							}

							break;

						}

						count++;

					}

				}

			}
		}

		return httpSamplerRegexBuilder.toString();

	}

	public void processRequestXML() {

		StringBuilder xmlRequestBuilder = new StringBuilder();

		// add logic of paramerization or correlation to replace url if
		// applicable
		// Add logic of getting transaction name
		httpSamplerStart = processRequestUrlpath();

		// add logic of parameterization or correlation for query string if
		// applicable
		httpSamplerArguments = processRequestParams();

		// httpSamplerRawBody = processPostRawBody();

		// Add logic pf parameterization/correlation for prop here.
		httpSamplerProp = processRequestProp();
		

		// Adding header to request

		httpSamplerHeader = processRequestHeader();

		httpSamplerRegexExtractor = processResponseRegex();

		//httpSamplerEndClose = JMXFileFormat.httpSamplerEndClose;

		xmlRequestBuilder
				.append(httpSamplerStart)
				.append(httpSamplerArguments)
				.append(httpSamplerProp)
				.append(JMXFileFormat.httpSamplerEndCloseRequest);
		
		if (httpSamplerHeader !=null || httpSamplerRegexExtractor !=null)
		{
			xmlRequest = xmlRequestBuilder
			.append(JMXFileFormat.hashtree)
			.append(httpSamplerHeader)
			.append(httpSamplerRegexExtractor)
			.append(JMXFileFormat.httpSamplerEndClose)
			.toString();
		}
		
		else{
			xmlRequest = xmlRequestBuilder
			.append(httpSamplerHeader)
			.append(httpSamplerRegexExtractor)
			.append(JMXFileFormat.httpSamplerEndClose)
			.toString();
			
		}
		
		

		// if (contentType != null) {
		// httpSamplerHeaderEnd = JMXFileFormat.httpSamplerHeaderEnd;
		//
		// xmlRequest = xmlRequestBuilder.append(httpSamplerStart)
		// .append(httpSamplerArguments).append(httpSamplerProp)
		// .append(httpSamplerHeader).append(httpSamplerRegexExtractor).append(httpSamplerEndClose)
		// .append(httpSamplerHeaderEnd).toString();
		// } else {
		// xmlRequest = xmlRequestBuilder.append(httpSamplerStart)
		// .append(httpSamplerArguments).append(httpSamplerProp).append(httpSamplerRegexExtractor)
		// .append(httpSamplerEndClose).toString();
		// }

		// append(httpSamplerHeader).append(httpSamplerEndClose).
		// .append(httpSamplerHeader) .append(httpSamplerHeaderEnd)

		JMXProcessor.writeToJMX(xmlRequest);

	}

	public void cleanup() {
		// TODO Auto-generated method stub

		System.out.println("IN CLEANUP");

		if (filteredEntrylist != null) {

			System.out.println("IN CLEANUP - Clearing filterlist");

			server.getHar().getLog().getEntries().clear();
			filteredEntrylist.clear();

		}

		// cleaning paramMap
		pp.clearMap();

	}

}