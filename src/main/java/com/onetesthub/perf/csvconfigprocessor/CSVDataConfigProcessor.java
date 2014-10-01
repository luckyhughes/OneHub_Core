package com.onetesthub.perf.csvconfigprocessor;

import com.onetesthub.perf.core.config.ConfigTest;
import com.onetesthub.perf.core.jmxgenerator.JMXFileFormat;
import com.onetesthub.perf.core.jmxgenerator.JMXProcessor;

public class CSVDataConfigProcessor {

	private String filename;
	private String description;
	private String delimiter;
	private String shareMode;
	private String params;

	private String filepath;

	CSVDataConfigProcessor() {

		filepath = ConfigTest.getValue("DATASETPATH");
	}

	public void CreateCSVConfig(String filename, String description,
			String delimiter, String shareMode, String params) {

		this.filename = filepath + "/" + filename;
		this.description = description;
		this.delimiter = delimiter;
		this.shareMode = shareMode;
		this.params = params;

		jmxProcessor();

	}

	public void CreateCSVConfig(String filename, String description,
			String params) {

		this.filename = filepath + "/" + filename;
		this.description = description;
		this.params = params;

		this.delimiter = ",";
		this.shareMode = "shareMode.all";

		jmxProcessor();

	}

	public void jmxProcessor() {

		StringBuilder jmxBuilder = new StringBuilder(JMXFileFormat.CSVDataSet);

		String jmxString = jmxBuilder.toString().replace("FILENAME", filename)
				.replace("DESCRIPTION", description)
				.replace("DELIMITER", delimiter)
				.replace("SHAREMODE", shareMode).replace("PARAMS", params);

		JMXProcessor.writeToJMX(jmxString);

	}

}
