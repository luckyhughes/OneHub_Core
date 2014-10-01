package com.onetesthub.perf.csvconfigprocessor;

public class CSVDataPrep {
	
	private CSVDataConfigProcessor csvDataConfig;
	
	public CSVDataPrep() {
		
		csvDataConfig = new CSVDataConfigProcessor();
		
	}
	
		public void init() {
			
			// Add methods for each csv data config configuration needed in test plan
			
			//1. for search term keyword
			csvDataConfig.CreateCSVConfig("searchterm.csv", "search Keywords","searchterm");
		
		}

}
