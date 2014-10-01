package com.onetesthub.perf.library.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.impl.conn.SchemeRegistryFactory;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

//import com.jayway.restassured.config.HttpClientConfig;
//import com.jayway.restassured.config.RestAssuredConfig;
//import  com.jayway.restassured.matcher.RestAssuredMatchers.*;


import com.onetesthub.perf.adapter.ui.BaseUIPerfTest;

public class TC2 extends BaseUIPerfTest {

	@SuppressWarnings("static-access")
	@Test
	public void test1() throws ClientProtocolException, IOException {

		// RestAssuredConfig.newConfig().httpClient(HttpClientConfig.httpClientConfig().setParam(ConnRoutePNames.DEFAULT_PROXY,

		HttpHost proxy = new HttpHost("127.0.0.1", 8084, "http");

		HttpHost target = new HttpHost("napa.lithium.com", 80, "http");

		PoolingClientConnectionManager cxMgr = new PoolingClientConnectionManager(SchemeRegistryFactory.createDefault());
		cxMgr.setMaxTotal(100);
		cxMgr.setDefaultMaxPerRoute(20);

		DefaultHttpClient httpclient = new DefaultHttpClient(cxMgr);

		httpclient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

		// HttpGet request = new HttpGet("/");
		//
		// HttpResponse response = httpclient.execute(target,request);

		HttpPost httpPost = new HttpPost("/t5/forums/searchpage/tab/message");

		List<NameValuePair> postParams = new ArrayList<NameValuePair>();

		postParams.add(new BasicNameValuePair("filter", "location"));

		postParams.add(new BasicNameValuePair("q", "lithium"));

		postParams.add(new BasicNameValuePair("location", "Community"));

		httpPost.setEntity(new UrlEncodedFormEntity(postParams));

		HttpResponse response = httpclient.execute(target, httpPost);
		System.out.println("Response Code : " + response.getEntity().getContent().toString());

	}

}
