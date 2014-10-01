package com.onetesthub.perf.core.proxy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import net.lightbody.bmp.proxy.ProxyServer;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.onetesthub.perf.adapter.ui.UIChromeDriver;
import com.onetesthub.perf.adapter.ui.UIFirefoxDriver;
import com.onetesthub.perf.api.ui.PerfWebDriver;
import com.onetesthub.perf.core.config.ConfigProcessor;

public class DriverDefault {

	static String driverType;
	private static PerfWebDriver driver = null;
	private static ProxyServer server;
	static DesiredCapabilities capabilities;
	static String baseUrl;

	 static {
		 
		 
//		 try {
//				String hname = InetAddress.getLocalHost().getCanonicalHostName();
//				System.out.println("hname is @@@@@@:::"+hname);
//			} catch (UnknownHostException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		 
		 getBaseUrl();

		if (IsPerfEnabled()) {

			try {
				setProxy();
				EnablePerf();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		setDriver();
	}

	public static boolean IsPerfEnabled() {
		String perfCheck = ConfigProcessor.getPerformanceEnable();
		if (perfCheck.equals("true")) {
			return true;
		}
		return false;
	}

	public static void setProxy() {

		server = new ProxyServer(ConfigProcessor.getProxyPort());
		
		
		

	}
	
	public static String getBaseUrl()
	{
		baseUrl = ConfigProcessor.getbaseUrl();
		System.out.println("DRIVERDEFAULT BaseUrl is: " + baseUrl);
		return baseUrl;
	}
	
	
	public static void EnablePerf() throws UnknownHostException {
		server = getProxy() ;
		try {
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		server.setCaptureHeaders(true);

		server.setCaptureContent(true);
		server.setRequestTimeout(100000000);
		server.newHar("/");

	}

	public static void setDriver() {
		// TODO Auto-generated method stub

		capabilities = null;
		capabilities = DesiredCapabilities.chrome();

		driverType = ConfigProcessor.getDriverType();
		String driverPath = ConfigProcessor.getDriverPath();

		if (driverType.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPath);

			try {
				capabilities.setCapability(CapabilityType.PROXY,
						server.seleniumProxy());
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver = new UIChromeDriver(capabilities);

		} else if (driverType.equals("firefox")) {

			// System.setProperty("webdriver.firefox.bin",
			// "/Applications/Firefox.app/Contents/MacOS/firefox-bin");
			capabilities = DesiredCapabilities.firefox();

			try {
				capabilities.setCapability(CapabilityType.PROXY,
						server.seleniumProxy());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver = new UIFirefoxDriver(capabilities);
		}

	}

	public static String getDriverType() {
		// TODO Auto-generated method stub
		return driverType;

	}

	public static PerfWebDriver getDriver() {
		// TODO Auto-generated method stub
		return driver;

	}

	public static ProxyServer getProxy() {
		// TODO Auto-generated method stub
		return server;

	}

	public static void stopProxy() {
		// TODO Auto-generated method stub
		try {
			server.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
