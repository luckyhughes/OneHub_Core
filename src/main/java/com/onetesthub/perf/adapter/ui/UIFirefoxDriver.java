package com.onetesthub.perf.adapter.ui;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.onetesthub.perf.api.ui.PerfWebDriver;


public class UIFirefoxDriver extends FirefoxDriver implements PerfWebDriver{
	
	
	public UIFirefoxDriver(Capabilities desiredCapabilities) {
		super(desiredCapabilities);
	
	}
	
}
	
