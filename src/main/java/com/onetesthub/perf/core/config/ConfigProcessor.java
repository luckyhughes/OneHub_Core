package com.onetesthub.perf.core.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProcessor {

	private static  final String propFile = "resources//properties.prop";
	private static Properties property;
	private static String driverType;
	private static String driverPath;
	private static String performanceEnable;
	private static String proxyPort;
	private static String scriptDebug;
	private static String harFilePath;
	private static String baseUrl;
	private static String uiPerfLog;

	static {

		property = new Properties();
		try {
			property.load(new FileInputStream(propFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getDriverType() {
		ConfigProcessor.driverType = property.getProperty("driverType");
		return driverType;
	}

	public static String getDriverPath() {
		ConfigProcessor.driverPath = property.getProperty("driverPath");
		return driverPath;
	}

	public static String getPerformanceEnable() {
		ConfigProcessor.performanceEnable = property.getProperty("performanceEnable");
		return performanceEnable;
	}
	
	public static int getProxyPort() {
		ConfigProcessor.proxyPort = property.getProperty("proxyPort");
		return Integer.parseInt(proxyPort);
	}
	
	public static String getscriptDebug() {
		ConfigProcessor.scriptDebug = property.getProperty("scriptDebug");
		return scriptDebug;
	}
	
	public static String getharFilePath() {
		ConfigProcessor.harFilePath = property.getProperty("harFilePath");
		return harFilePath;
	}
	
	public static String getuiPerfLog() {
		ConfigProcessor.uiPerfLog = property.getProperty("uiPerfLog");
		return uiPerfLog;
	}
	
	public static String getbaseUrl() {
		ConfigProcessor.baseUrl = property.getProperty("baseUrl");
		System.out.println("CONFIGPROCESSOR BaseUrl is: " + ConfigProcessor.baseUrl);
		return baseUrl;
	}
	
	public static String getPropfile() {
		return propFile;
	}

}
