package com.onetesthub.perf.core.datamodel;

import com.onetesthub.perf.core.jmxgenerator.JMXFileFormat;
import com.onetesthub.perf.core.jmxgenerator.JMXProcessor;

public class Common {

	public static void init() {

		// generate random number
		randomNumber("randomNumber", 1, 10000);

	}

	// add random variable
	public static void randomNumber(String varName, int min, int max) {

		String randomjmx = JMXFileFormat.randomVariable.replaceAll("RANDOMVARNAME", varName)
				.replaceAll("RANDOM_MIN","1").replaceAll("RANDOM_MAX",
						"1000000");
		
		JMXProcessor.writeToJMX(randomjmx);

	}

}
