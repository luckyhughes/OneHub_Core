package com.onetesthub.perf.library.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.message.BasicNameValuePair;

import com.onetesthub.perf.core.config.ConfigProcessor;

public class Restapi {

	private HttpHost target;
	private HashMap<String, String> paramMap;
	private DefaultHttpClient httpclient;
	private HttpResponse response;
	private List<NameValuePair> postParams = new ArrayList<NameValuePair>();
	private Response responseObject = new Response();
	private String isdebug;

	Restapi(String baseUrl, int port) {

		HttpHost proxy = new HttpHost("127.0.0.1",
				ConfigProcessor.getProxyPort());

		target = new HttpHost(baseUrl, port);

		PoolingClientConnectionManager cxMgr = new PoolingClientConnectionManager(
				SchemeRegistryFactory.createDefault());
		cxMgr.setMaxTotal(100);
		cxMgr.setDefaultMaxPerRoute(20);

		httpclient = new DefaultHttpClient(cxMgr);

		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
				proxy);

		isdebug = ConfigProcessor.getscriptDebug();

	}

	public void get(String req) {

		HttpGet request = new HttpGet(req);

		try {
			response = httpclient.execute(target, request);
		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		parseResponseCode();
		parseResponse();

	}

	public void post(String req) {

		HttpPost httpPost = new HttpPost(req);

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(postParams));
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		try {
			response = httpclient.execute(target, httpPost);
		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		postParams.clear();

		parseResponseCode();
		parseResponse();
	}

	public void params(String paramName, String paramValue) {

		postParams.add(new BasicNameValuePair(paramName, paramValue));

	}

	public void parseResponseCode() {

		responseObject.setStatusCode(response.getStatusLine().getStatusCode());

		if (isdebug.equals("true")) {

			System.out.println(responseObject.getStatusCode());

		}

	}

	public void parseResponse() {

		BufferedReader brd = null;

		try {
			brd = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent()));
		} catch (IllegalStateException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		StringBuilder sb = new StringBuilder();

		String line = null;

		try {
			while ((line = brd.readLine()) != null) {

				sb.append(line);

			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		responseObject.setTextResponse(sb.toString());

		if (isdebug.equals("true")) {

			System.out.println(responseObject.getTextResponse());

		}

	}

	 public boolean contains(String text) {
		 
		 return responseObject.getTextResponse().contains(text);
	
	 }
	 
	 public boolean statusCode(int code) {
		 
		 return Integer.valueOf(responseObject.getStatusCode()).equals(code);
	
	 }

}
