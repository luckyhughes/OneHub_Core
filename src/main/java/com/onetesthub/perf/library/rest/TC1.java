package com.onetesthub.perf.library.rest;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import net.lightbody.bmp.proxy.ProxyServer;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;

import com.onetesthub.perf.adapter.ui.BaseUIPerfTest;


public class TC1 extends BaseUIPerfTest {
	
	Restapi restapi;
	
	TC1(){
		
		restapi = new Restapi("napa.lithium.com", 80);
	}
	
	@Test
	public void homepage() {
		
		restapi.get("/");
				
		Assert.assertTrue(restapi.contains("sandeep"));
	}
	
	@Test
	public void search() {

		restapi.params("filter", "location");
		restapi.params("q", "lithium");
		restapi.params("location", "Community");
		restapi.post("/t5/forums/searchpage/tab/message");
		
		Assert.assertTrue(restapi.contains("napa"));
		Assert.assertTrue(restapi.statusCode(300));

	}
	
	@Test
	public void search1() {

		restapi.params("filter", "location");
		restapi.params("q", "goodybag");
		restapi.params("location", "Community");
		restapi.post("/t5/forums/searchpage/tab/message");
				
		Assert.assertTrue(restapi.contains("napa"));
		Assert.assertTrue(restapi.statusCode(200));
	

	}

	}


