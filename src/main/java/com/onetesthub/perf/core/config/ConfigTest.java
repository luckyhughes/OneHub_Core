package com.onetesthub.perf.core.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;

public class ConfigTest {

	private final static String propFile = "resources//test.prop";
	static Properties property;
	private static HashMap<String, String> configTestMap = new HashMap<String, String>();
	 
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

		ConfigTest.loadMap();
	}

	public static void loadMap() {
		
		for (Entry<Object, Object> entry : property.entrySet()) {
			String key = (String) entry.getKey();
			String value = (String) entry.getValue();

			configTestMap.put(key, value);
		}

	}
	
	public static HashMap<String, String> getMap()
	{
		return configTestMap;
	}
	
	public static String getValue(String key) {
		
		return getMap().get(key);
		
	}

}

	//private static String TESTPLANNAME;
	// private static String HTTPHOSTNAME;
	// private static String THREADS;
	// private static String LOOPCOUNTS;
	// private static String RANDOMTIMER;
	// private static String HTTPPORT;
	// private static String DBHOSTNAME;
	// private static String DBNAME;
	// private static String DBUSERNAME;
	// private static String DBPASSWORD;
	// static private String jmxfilename;
	// static private String jmxfilepath;

	//public static String getTESTPLANNAME() {
	//
	// return ConfigTest.TESTPLANNAME = property.getProperty("TESTPLANNAME");
	//
	// }
	//
	// public static String getHTTPHOSTNAME() {
	// return ConfigTest.HTTPHOSTNAME = property.getProperty("HTTPHOSTNAME");
	// }
	//
	// public static String getTHREADS() {
	// return ConfigTest.THREADS = property.getProperty("THREADS");
	// }
	//
	// public static String getLOOPCOUNTS() {
	// return ConfigTest.LOOPCOUNTS = property.getProperty("LOOPCOUNTS");
	// }
	//
	// public static String getRANDOMTIMER() {
	// return ConfigTest.RANDOMTIMER = property.getProperty("RANDOMTIMER");
	// }
	//
	// public static String getHTTPPORT() {
	// return ConfigTest.HTTPPORT = property.getProperty("HTTPPORT");
	// }
	//
	// public static String getDBHOSTNAME() {
	// return DBHOSTNAME = property.getProperty("HTTPHOSTNAME");
	// }
	//
	// public static String getDBNAME() {
	// return ConfigTest.DBNAME = property.getProperty("HTTPHOSTNAME");
	// }
	//
	// public static String getDBUSERNAME() {
	// return ConfigTest.DBUSERNAME = property.getProperty("HTTPHOSTNAME");
	// }
	//
	// public static String getDBPASSWORD() {
	// return ConfigTest.DBPASSWORD = property.getProperty("HTTPHOSTNAME");
	// }
	//
	// public static String getJmxfilename() {
	// ConfigTest.jmxfilename = property.getProperty("jmxfilename");
	// return jmxfilename;
	// }
	//
	// public static String getJmxfilepath() {
	// ConfigTest.jmxfilepath = property.getProperty("jmxfilepath");
	// return jmxfilepath;
	// }
