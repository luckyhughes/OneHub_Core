package com.onetesthub.perf.adapter.ui;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.onetesthub.perf.api.ui.PerfWebDriver;


public class UIRemoteWebDriver extends RemoteWebDriver implements PerfWebDriver{
	
	
	UIRemoteWebDriver(Capabilities desiredCapabilities) {
		super(desiredCapabilities);
	
	}
	
}
	