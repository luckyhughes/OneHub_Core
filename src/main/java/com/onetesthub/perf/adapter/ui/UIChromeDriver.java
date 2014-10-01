package com.onetesthub.perf.adapter.ui;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;

import com.onetesthub.perf.api.ui.PerfWebDriver;


public class UIChromeDriver extends ChromeDriver implements PerfWebDriver{
	
	
	public UIChromeDriver(Capabilities desiredCapabilities) {
		super(desiredCapabilities);
	
	}
		
}
	
