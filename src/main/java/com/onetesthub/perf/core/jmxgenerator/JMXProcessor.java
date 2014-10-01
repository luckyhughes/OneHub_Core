package com.onetesthub.perf.core.jmxgenerator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Map.Entry;

import com.onetesthub.perf.core.config.ConfigTest;
import com.onetesthub.perf.core.datamodel.Common;

public class JMXProcessor {

	private static PrintWriter jmxWriter;
	private static String jmxFilename;
	//private Common common = new Common();

	static {

		jmxFilename = ConfigTest.getValue("FILEPATH")
				+ ConfigTest.getValue("JMXFILENAME");
		try {
			jmxWriter = new PrintWriter(jmxFilename, "UTF-8");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static PrintWriter getJmxWriter() {
		return jmxWriter;
	}

	public static void writeToJMX(String text) {

		jmxWriter.write(text);

	}

	public static void preJMX() {

		String testplan = new String(JMXFileFormat.jmxTestPlanStart);
		
		String threadGroup = new String(JMXFileFormat.httpThreadgroupStart);

		String newtestplan = null;

		for (Entry<String, String> entry : ConfigTest.getMap().entrySet()) {

			newtestplan = testplan.replace(entry.getKey(), entry.getValue());

			testplan = newtestplan;

			// only for testing...
			System.out.println("config key value pair :-" + entry.getKey()
					+ "," + entry.getValue());

		}

		writeToJMX(newtestplan);
		writeToJMX(threadGroup);
		
		Common.init();

	}
	
	

	public static void postJMX() {

		writeToJMX(JMXFileFormat.httpThreadgroupEnd);
		
		writeToJMX(JMXFileFormat.jmxTestPlanEnd);
		
		
		jmxWriter.close();
	
		System.out.println("jmx file has been created at : " + jmxFilename);
		

	}

}
