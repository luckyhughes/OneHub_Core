/*
 * JMXFileFormat.java
 * Created on Aug 13, 2013
 *
 * Copyright 2013 Lithium Technologies, Inc. 
 * Emeryville, California, U.S.A.  All Rights Reserved.
 *
 * This software is the  confidential and proprietary information
 * of  Lithium  Technologies,  Inc.  ("Confidential Information")
 * You shall not disclose such Confidential Information and shall 
 * use  it  only in  accordance  with  the terms of  the  license 
 * agreement you entered into with Lithium.
 */

package com.onetesthub.perf.core.jmxgenerator;

/**
 * @author binod.gupta
 */
public class JMXFileFormat {

	public final static String jmxTestPlanStart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<jmeterTestPlan version=\"1.2\" properties=\"2.4\" jmeter=\"2.9 r1437961\">"
			+ "<hashTree>\n"
			+ "<TestPlan guiclass=\"TestPlanGui\" testclass=\"TESTCLASSNAME\" testname=\"TESTPLANNAME\" enabled=\"true\">\n"
			+ "<stringProp name=\"TestPlan.comments\"></stringProp>\n"
			+ "<boolProp name=\"TestPlan.functional_mode\">false</boolProp>\n"
			+ "<boolProp name=\"TestPlan.serialize_threadgroups\">false</boolProp>\n"
			+ "<elementProp name=\"TestPlan.user_defined_variables\" elementType=\"Arguments\" guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" testname=\"User Defined Variables\" enabled=\"true\">\n"
			+ "<collectionProp name=\"Arguments.arguments\"/>\n"
			+ "</elementProp>\n"
			+ "<stringProp name=\"TestPlan.user_define_classpath\"></stringProp>\n"
			+ "</TestPlan>\n"
			+ "<hashTree>\n"
			
			//user defined variables, can be replaced at jmeter command-line 
			+ "<Arguments guiclass=\"ArgumentsPanel\" testclass=\"Arguments\" testname=\"User Defined Variables\" enabled=\"true\">\n"
			+ "<collectionProp name=\"Arguments.arguments\">\n"
			
			+ "<elementProp name=\"server\" elementType=\"Argument\">\n"
			+ "<stringProp name=\"Argument.name\">server</stringProp>\n"
			+ "<stringProp name=\"Argument.value\">${__P(server,HTTPHOSTNAME)}</stringProp>\n"
			+ "<stringProp name=\"Argument.metadata\">=</stringProp>\n"
			+ "</elementProp>\n"
			
			//jmeter -n -t SimpleJmxExample.jmx -Jserver=hostname
			
				
			+ "<elementProp name=\"server\" elementType=\"Argument\">\n"
			+ "<stringProp name=\"Argument.name\">server</stringProp>\n"
			+ "<stringProp name=\"Argument.value\">${__P(dbserver,DBHOSTNAME)}</stringProp>\n"
			+ "<stringProp name=\"Argument.metadata\">=</stringProp>\n"
			+ "</elementProp>\n"
			
			//DB Server Name
			
			+ "<elementProp name=\"threads\" elementType=\"Argument\">\n"
			+ "<stringProp name=\"Argument.name\">threads</stringProp>\n"
			+ "<stringProp name=\"Argument.value\">${__P(threads,THREADS)}</stringProp>\n"
			+ "<stringProp name=\"Argument.metadata\">=</stringProp>\n"
			+ "</elementProp>\n"
			
			//jmeter -n -t SimpleJmxExample.jmx -Jthreads=10
			
			+ "<elementProp name=\"loopcount\" elementType=\"Argument\">\n"
			+ "<stringProp name=\"Argument.name\">loopcount</stringProp>\n"
			+ "<stringProp name=\"Argument.value\">${__P(loopcount,LOOPCOUNTS)}</stringProp>\n"
			+ "<stringProp name=\"Argument.metadata\">=</stringProp>\n"
			+ "</elementProp>\n"
			//jmeter -n -t SimpleJmxExample.jmx -Jloopcount=10
			
			+ "<elementProp name=\"randomTimer\" elementType=\"Argument\">\n"
			+ "<stringProp name=\"Argument.name\">randomTimer</stringProp>\n"
			+ "<stringProp name=\"Argument.value\">${__P(randomTimer, RANDOMTIMER)}</stringProp>\n"
			+ "<stringProp name=\"Argument.metadata\">=</stringProp>\n"
			+ "</elementProp>\n"
			//jmeter -n -t SimpleJmxExample.jmx -JrandomTimer=1000 (milliseconds)
			
			+ "</collectionProp>\n"
			+ "</Arguments>\n"
			+ "<hashTree/>\n"
			
			//HTTP request defaults
			+ "<ConfigTestElement guiclass=\"HttpDefaultsGui\" testclass=\"ConfigTestElement\" testname=\"HTTP Request Defaults\" enabled=\"true\">\n"
			+ "<elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\" guiclass=\"HTTPArgumentsPanel\" testclass=\"Arguments\" testname=\"User Defined Variables\" enabled=\"true\">\n"
			+ "<collectionProp name=\"Arguments.arguments\">\n"
			+ "<elementProp name=\"\" elementType=\"HTTPArgument\">\n"
			+ "<boolProp name=\"HTTPArgument.always_encode\">false</boolProp>\n"
			+ "<stringProp name=\"Argument.value\"></stringProp>\n"
			+ "<stringProp name=\"Argument.metadata\">=</stringProp>\n"
			+ "<boolProp name=\"HTTPArgument.use_equals\">true</boolProp>\n"
			+ "</elementProp>\n"
			+ "</collectionProp>\n"
			+ "</elementProp>\n"
			+ "<stringProp name=\"HTTPSampler.domain\">${server}</stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.port\">HTTPPORT</stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.connect_timeout\"></stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.protocol\"></stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.path\"></stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.concurrentPool\">4</stringProp>\n"
			+ "</ConfigTestElement>\n"
			+ "<hashTree/>\n"
			
			//HTTP header manager
		    + "<HeaderManager guiclass=\"HeaderPanel\" testclass=\"HeaderManager\" testname=\"HTTP Header Manager\" enabled=\"true\">\n"
		    + "<collectionProp name=\"HeaderManager.headers\"/>\n"
		    + "</HeaderManager>\n"
		    + "<hashTree/>\n"
		    
		  	//HTTP cookie manager
		    + "<CookieManager guiclass=\"CookiePanel\" testclass=\"CookieManager\" testname=\"HTTP Cookie Manager\" enabled=\"true\">\n"
		    + "<collectionProp name=\"CookieManager.cookies\"/>\n"
		    + "<boolProp name=\"CookieManager.clearEachIteration\">false</boolProp>\n"
		    + "</CookieManager>\n"
		    + "<hashTree/>\n"

			//JDBC connection configuration
			+ "<JDBCDataSource guiclass=\"TestBeanGUI\" testclass=\"JDBCDataSource\" testname=\"MySQL Configuration\" enabled=\"true\">\n"
			+ "<boolProp name=\"autocommit\">false</boolProp>\n"
			+ "<stringProp name=\"checkQuery\"></stringProp>\n"
			+ "<stringProp name=\"connectionAge\">50000</stringProp>\n"
			+ "<stringProp name=\"dataSource\">mySQL</stringProp>\n"
			+ "<stringProp name=\"dbUrl\">jdbc:mysql://${dbserver}:3306/DBNAME</stringProp>\n"
			+ "<stringProp name=\"driver\">com.mysql.jdbc.Driver</stringProp>\n"
			+ "<boolProp name=\"keepAlive\">true</boolProp>\n"
			+ "<stringProp name=\"password\">DBPASSWORD</stringProp>\n"
			+ "<stringProp name=\"poolMax\">10</stringProp>\n"
			+ "<stringProp name=\"timeout\">10000</stringProp>\n"
			+ "<stringProp name=\"trimInterval\">60000</stringProp>\n"
			+ "<stringProp name=\"username\">DBUSERNAME</stringProp>\n"
			+ "<stringProp name=\"transactionIsolation\">DEFAULT</stringProp>\n"
			+ "</JDBCDataSource>\n"
	        + "<hashTree/>\n"
			
	        //Result Tree for debugging script
	        
	        +"<ResultCollector guiclass=\"ViewResultsFullVisualizer\" testclass=\"ResultCollector\" testname=\"View Results Tree\" enabled=\"RESULTTREE_ENABLE\">\n"
			+"<boolProp name=\"ResultCollector.error_logging\">false</boolProp>\n"
			+"<objProp>\n"
			+"  <name>saveConfig</name>\n"
			+" <value class=\"SampleSaveConfiguration\">\n"
			+" <time>true</time>\n"
			+" <latency>true</latency>\n"
			+"<timestamp>true</timestamp>\n"
			+"<success>true</success>\n"
			+"<label>true</label>\n"
			+"<code>true</code>\n"
			+"<message>true</message>\n"
			+"<threadName>false</threadName>\n"
			+"<dataType>true</dataType>\n"
			+"<encoding>false</encoding>\n"
			+"<assertions>true</assertions>\n"
			+"<subresults>true</subresults>\n"
			+"<responseData>false</responseData>\n"
			+"<samplerData>false</samplerData>\n"
			+"<xml>false</xml>\n"
			+"<fieldNames>false</fieldNames>\n"
			+"<responseHeaders>false</responseHeaders>\n"
			+"<requestHeaders>false</requestHeaders>\n"
			+"<responseDataOnError>false</responseDataOnError>\n"
			+"<saveAssertionResultsFailureMessage>false</saveAssertionResultsFailureMessage>\n"
			+"<assertionsResultsToSave>0</assertionsResultsToSave>\n"
			+"<bytes>true</bytes>\n"
			+"</value>\n"
			+"</objProp>\n"
			+"<stringProp name=\"filename\">FILEPATH/RESULTTREE_LOGFILE</stringProp>\n"
			+"</ResultCollector>\n"
			+"<hashTree/>\n";
	
	
	public final static String jmxTestPlanEnd = "</hashTree>" + "</hashTree>" + "</jmeterTestPlan>";
		    
		    //ThreadGroup
	public final static String httpThreadgroupStart = "<ThreadGroup guiclass=\"ThreadGroupGui\" testclass=\"ThreadGroup\" testname=\"Thread Group\" enabled=\"true\">\n"
			+ "<stringProp name=\"ThreadGroup.on_sample_error\">continue</stringProp>\n"
			+ "<elementProp name=\"ThreadGroup.main_controller\" elementType=\"LoopController\" guiclass=\"LoopControlPanel\" testclass=\"LoopController\" testname=\"Loop Controller\" enabled=\"true\">\n"
			+ "<boolProp name=\"LoopController.continue_forever\">false</boolProp>\n"
			+ "<stringProp name=\"LoopController.loops\">${loopcount}</stringProp>" + "</elementProp>\n"
			+ "<stringProp name=\"ThreadGroup.num_threads\">${threads}</stringProp>\n"
			+ "<stringProp name=\"ThreadGroup.ramp_time\">1</stringProp>\n"
			+ "<longProp name=\"ThreadGroup.start_time\">1375209872000</longProp>\n"
			+ "<longProp name=\"ThreadGroup.end_time\">1375209872000</longProp>\n"
			+ "<boolProp name=\"ThreadGroup.scheduler\">false</boolProp>\n"
			+ "<stringProp name=\"ThreadGroup.duration\"></stringProp>\n"
			+ "<stringProp name=\"ThreadGroup.delay\"></stringProp>\n" 
			+ "</ThreadGroup>\n"
			+ "<hashTree>";
	
	public final static String httpThreadgroupEnd = "</hashTree>";
			
			

	public final static String TransactionControllerStart = "<TransactionController guiclass=\"TransactionControllerGui\" testclass=\"TransactionController\" testname=\"TRANSACTIONNAME\" enabled=\"true\">\n"
			+ "<boolProp name=\"TransactionController.parent\">true</boolProp>\n"
			+ "</TransactionController>\n"
			+ "<hashTree>";
			

	public final static String TransactionControllerEnd = "</hashTree>";
	
	public final static String httpSamplerStart = "<HTTPSamplerProxy guiclass=\"HttpTestSampleGui\" testclass=\"HTTPSamplerProxy\" testname=\"REQUESTNAME\" enabled=\"true\">\n"
			+ " <boolProp name=\"HTTPSampler.postBodyRaw\">FLAGPOSTRAWBODY</boolProp>\n"
			+ "<elementProp name=\"HTTPsampler.Arguments\" elementType=\"Arguments\" guiclass=\"HTTPArgumentsPanel\" testclass=\"Arguments\" testname=\"User Defined Variables\" enabled=\"true\">\n"
			+ "<collectionProp name=\"Arguments.arguments\">\n";
	
	public final static String httpSamplerEndClose = "</hashTree>\n";

	
	//for request having raw body and not parameters. one for each parameter
	public final static String httpSamplerBodyRawRequest = "<elementProp name=\"\" elementType=\"HTTPArgument\">\n"
    		+ "<boolProp name=\"HTTPArgument.always_encode\">false</boolProp>\n"
    		+ "<stringProp name=\"Argument.value\">HTTPBODYRAW</stringProp>\n"
    		+ "<stringProp name=\"Argument.metadata\">=</stringProp>\n"
    		+ "</elementProp>\n";

	
	//for request having parameters and not raw body
	public final static String httpSamplerArgumentsRequest = "<elementProp name=\"HTTPPARAMETER\" elementType=\"HTTPArgument\">\n"
			+ "<boolProp name=\"HTTPArgument.always_encode\">false</boolProp>\n"
			+ "<stringProp name=\"Argument.value\">HTTPVALUE</stringProp>\n"
			+ "<stringProp name=\"Argument.metadata\">=</stringProp>\n"
			+ "<boolProp name=\"HTTPArgument.use_equals\">true</boolProp>\n"
			+ "<stringProp name=\"Argument.name\">HTTPPARAMETER</stringProp>\n"
			+ "</elementProp>";
	
	//for request common properties
	public final static String httpSamplerPropRequest = "</collectionProp>"
			+ "</elementProp>\n"
			+ "<stringProp name=\"HTTPSampler.domain\"></stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.port\"></stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.connect_timeout\"></stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.response_timeout\"></stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.protocol\"></stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.contentEncoding\"></stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.path\">HTTPURL</stringProp>\n"
			+ "<stringProp name=\"HTTPSampler.method\">HTTPMETHOD</stringProp>\n"
			+ "<boolProp name=\"HTTPSampler.follow_redirects\">true</boolProp>\n"
			+ "<boolProp name=\"HTTPSampler.auto_redirects\">false</boolProp>\n"
			+ "<boolProp name=\"HTTPSampler.use_keepalive\">true</boolProp>\n"
			+ "<boolProp name=\"HTTPSampler.DO_MULTIPART_POST\">DOMULTIPARTPOST</boolProp>\n"
			+ "<stringProp name=\"HTTPSampler.implementation\">HttpClient4</stringProp>\n"
			+ "<boolProp name=\"HTTPSampler.monitor\">false</boolProp>\n"
			+ "<stringProp name=\"HTTPSampler.embedded_url_re\"></stringProp>\n";
			
			
	public final static String httpSamplerEndCloseRequest = "</HTTPSamplerProxy>\n";
			//+ "<hashTree>\n";
	
	public final static String hashtree = "<hashTree>\n";
	


	public final static String httpSamplerHeader = "<HeaderManager guiclass=\"HeaderPanel\" testclass=\"HeaderManager\" testname=\"HTTP Header Manager\" enabled=\"true\">\n"
		    +"  <collectionProp name=\"HeaderManager.headers\">\n"
		    +"  <elementProp name=\"\" elementType=\"Header\">\n"
		    +"<stringProp name=\"Header.name\">HEADERNAME</stringProp>\n"
		    +"<stringProp name=\"Header.value\">HEADERVALUE</stringProp>\n"
		    +" </elementProp>\n"
		    +" <elementProp name=\"\" elementType=\"Header\">\n"
		    +" <stringProp name=\"Header.name\"></stringProp>\n"
		    +" <stringProp name=\"Header.value\"></stringProp>\n"
		    +" </elementProp>\n"
		    +" </collectionProp>\n"
		    +" </HeaderManager>\n"
		    + "<hashTree/>\n";

	
	//public final static String httpSamplerHeaderEnd = "</hashTree>\n";
		

    
    //public final static String RegExtractorEnd = "</hashTree>\n";
    
    public final static String RegExtractor = "<RegexExtractor guiclass=\"RegexExtractorGui\" testclass=\"RegexExtractor\" testname=\"Reg Ex Extractor - REGNAME\" enabled=\"true\">\n"
    		+"<stringProp name=\"RegexExtractor.useHeaders\">false</stringProp>\n"
    		+"<stringProp name=\"RegexExtractor.refname\">REGEXVARNAME</stringProp>\n"
    		+"<stringProp name=\"RegexExtractor.regex\">REGEXPATTERN</stringProp>\n"
    		+"<stringProp name=\"RegexExtractor.template\">$1$</stringProp>\n"
    		+"<stringProp name=\"RegexExtractor.default\"></stringProp>\n"
    		+"<stringProp name=\"RegexExtractor.match_number\">REGEXORDINAL</stringProp>\n"
    		+"</RegexExtractor>\n"
    		+"<hashTree/>\n";
    		
    
    public final static String XPathExtractor = "<XPathExtractor guiclass=\"XPathExtractorGui\" testclass=\"XPathExtractor\" testname=\"XPATHVARNAME\" enabled=\"true\">\n"
    		+"<stringProp name=\"XPathExtractor.default\"></stringProp>"
    		+"<stringProp name=\"XPathExtractor.refname\">XPATHVARNAME</stringProp>\n"
    		+"<stringProp name=\"XPathExtractor.xpathQuery\">XPATHQUERY</stringProp>\n"
    		+"<stringProp name=\"XPathExtractor.validate\">false</stringProp>\n"
    		+"<stringProp name=\"XPathExtractor.tolerant\">true</stringProp>\n"
    		+"<stringProp name=\"XPathExtractor.namespace\">false</stringProp>\n"
    		+"</XPathExtractor>\n"
    		+"<hashTree/>\n";
    
    //public final static String XPathExtractorEnd = "</hashTree>\n";

	
	public final static String uniformRandomTimer = "<UniformRandomTimer guiclass=\"UniformRandomTimerGui\" testclass=\"UniformRandomTimer\" testname=\"Uniform Random Timer\" enabled=\"true\">\n"
			+ "<stringProp name=\"ConstantTimer.delay\">0</stringProp>\n"
			+ "<stringProp name=\"RandomTimer.range\">${randomTimer}</stringProp>\n"
			+ "</UniformRandomTimer>\n"
	        + "<hashTree/>";
	
	//JDBC Sampler, replaceAll SQLQUERY(raw sql query), SQLTYPE(substring of first string in the raw query, all upper case) later
	public final static String JDBCSampler = "<JDBCSampler guiclass=\"TestBeanGUI\" testclass=\"JDBCSampler\" testname=\"SQLQUERYNAME\" enabled=\"true\">\n"
			+ "<stringProp name=\"dataSource\">mySQL</stringProp>\n"
			+ "<stringProp name=\"query\">SQLQUERY\n"
			+ "</stringProp>\n"
    		+ "<stringProp name=\"queryArguments\"></stringProp>\n"
    		+ "<stringProp name=\"queryArgumentsTypes\"></stringProp>\n"
    		+ "<stringProp name=\"queryType\">SQLTYPE Statement</stringProp>\n"
    		+ "<stringProp name=\"variableNames\"></stringProp>\n"
    		+ "<stringProp name=\"resultVariable\"></stringProp>\n"
    		+ "</JDBCSampler>\n"
    		+ "<hashTree/>";

	public final static String SimpleDataWriter = "<ResultCollector guiclass=\"SimpleDataWriter\" testclass=\"ResultCollector\" testname=\"Test Result\" enabled=\"true\">\n"
			+ "<boolProp name=\"ResultCollector.error_logging\">false</boolProp>\n"
			+ "<objProp>>\n"
			+ "<name>saveConfig</name>\n"
			+ "<value class=\"SampleSaveConfiguration\">\n"
			+ "<time>true</time>\n"
        	+ "<latency>false</latency>\n"
        	+ "<timestamp>false</timestamp>\n"
        	+ "<success>false</success>\n"
        	+ "<label>true</label>\n"
        	+ "<code>false</code>\n"
        	+ "<message>false</message>\n"
        	+ "<threadName>false</threadName>\n"
        	+ "<dataType>false</dataType>\n"
        	+ "<encoding>false</encoding>\n"
        	+ "<assertions>false</assertions>\n"
        	+ "<subresults>false</subresults>\n"
        	+ "<responseData>false</responseData>>\n"
        	+ "<samplerData>false</samplerData>\n"
        	+ "<xml>false</xml>\n"
        	+ "<fieldNames>true</fieldNames>\n"
        	+ "<responseHeaders>false</responseHeaders>\n"
        	+ "<requestHeaders>false</requestHeaders>\n"
        	+ "<responseDataOnError>false</responseDataOnError>\n"
        	+ "<saveAssertionResultsFailureMessage>false</saveAssertionResultsFailureMessage>\n"
        	+ "<assertionsResultsToSave>0</assertionsResultsToSave>>\n"
        	+ "<bytes>true</bytes>\n"
        	+ "<threadCounts>true</threadCounts>\n"
        	+ "</value>\n"
        	+ "</objProp>\n"
        	+ "<stringProp name=\"filename\">${server}${__time(MMddyyyy_hhmm,)}</stringProp>\n"
        	+ "</ResultCollector>\n"
        	+ "<hashTree/>\n"
        	+ "<ResultCollector guiclass=\"ViewResultsFullVisualizer\" testclass=\"ResultCollector\" testname=\"View Results Tree\" enabled=\"true\">\n"
        	+ "  <boolProp name=\"ResultCollector.error_logging\">false</boolProp>\n"
        	+ "  <objProp>\n"
            + "    <name>saveConfig</name>\n"
            + "    <value class=\"SampleSaveConfiguration\">\n"
            + "      <time>true</time>\n"
            + "      <latency>true</latency>\n"
            + "      <timestamp>true</timestamp>\n"
            + "      <success>true</success>\n"
            + "      <label>true</label>\n"
            + "      <code>true</code>\n"
            + "      <message>true</message>\n"
            + "      <threadName>true</threadName>\n"
            + "      <dataType>true</dataType>\n"
            + "      <encoding>false</encoding>\n"
            + "      <assertions>true</assertions>\n"
            + "      <subresults>true</subresults>\n"
            + "      <responseData>false</responseData>\n"
            + "      <samplerData>false</samplerData>\n"
            + "      <xml>false</xml>\n"
            + "      <fieldNames>false</fieldNames>\n"
            + "      <responseHeaders>false</responseHeaders>\n"
            + "      <requestHeaders>false</requestHeaders>\n"
            + "      <responseDataOnError>false</responseDataOnError>\n"
            + "      <saveAssertionResultsFailureMessage>false</saveAssertionResultsFailureMessage>\n"
            + "      <assertionsResultsToSave>0</assertionsResultsToSave>\n"
            + "      <bytes>true</bytes>\n"
            + "    </value>\n"
            + "  </objProp>\n"
            + "  <stringProp name=\"filename\"></stringProp>\n"
            + "</ResultCollector>\n"
            + "<hashTree/>\n";
	
	
	public final static String ResultCollector = "<ResultCollector guiclass=\"ViewResultsFullVisualizer\" testclass=\"ResultCollector\" testname=\"View Results Tree\" enabled=\"true\">\n"
			+"<boolProp name=\"ResultCollector.error_logging\">false</boolProp>\n"
			+"<objProp>\n"
			+"  <name>saveConfig</name>\n"
			+" <value class=\"SampleSaveConfiguration\">\n"
			+" <time>true</time>\n"
			+" <latency>true</latency>\n"
			+"<timestamp>true</timestamp>\n"
			+"<success>true</success>\n"
			+"<label>true</label>\n"
			+"<code>true</code>\n"
			+"<message>true</message>\n"
			+"<threadName>false</threadName>\n"
			+"<dataType>true</dataType>\n"
			+"<encoding>false</encoding>\n"
			+"<assertions>true</assertions>\n"
			+"<subresults>true</subresults>\n"
			+"<responseData>false</responseData>\n"
			+"<samplerData>false</samplerData>\n"
			+"<xml>false</xml>\n"
			+"<fieldNames>false</fieldNames>\n"
			+"<responseHeaders>false</responseHeaders>\n"
			+"<requestHeaders>false</requestHeaders>\n"
			+"<responseDataOnError>false</responseDataOnError>\n"
			+"<saveAssertionResultsFailureMessage>false</saveAssertionResultsFailureMessage>\n"
			+"<assertionsResultsToSave>0</assertionsResultsToSave>\n"
			+"<bytes>true</bytes>\n"
			+"</value>\n"
			+"</objProp>\n"
			+"<stringProp name=\"filename\">/Users/212397664/logfile.log</stringProp>\n"
			+"</ResultCollector>\n"
			+"<hashTree/>\n";
  
	
	public final static String CSVDataSet = "<CSVDataSet guiclass=\"TestBeanGUI\" testclass=\"CSVDataSet\" testname=\"DESCRIPTION\" enabled=\"true\">\n"
			+ " <stringProp name=\"delimiter\">DELIMITER</stringProp>\n"
			+ " <stringProp name=\"fileEncoding\"></stringProp>\n"
			+ " <stringProp name=\"filename\">FILENAME</stringProp>\n"
			+ " <boolProp name=\"quotedData\">false</boolProp>\n"
			+ " <boolProp name=\"recycle\">true</boolProp>\n"
			+ " <stringProp name=\"shareMode\">SHAREMODE</stringProp>\n"
			+ " <boolProp name=\"stopThread\">false</boolProp>\n"
			+ " <stringProp name=\"variableNames\">PARAMS</stringProp>\n"
			+ " </CSVDataSet>\n"
			+ " <hashTree/>\n";
	
	public final static String randomVariable =	"<RandomVariableConfig guiclass=\"TestBeanGUI\" testclass=\"RandomVariableConfig\" testname=\"RANDOMVARNAME\" enabled=\"true\">\n"
			+ "<stringProp name=\"maximumValue\">RANDOM_MAX</stringProp>\n"
			+ "<stringProp name=\"minimumValue\">RANDOM_MIN</stringProp>\n"
			+ "<stringProp name=\"outputFormat\">nnnnnnn</stringProp>\n"
			+ "<boolProp name=\"perThread\">true</boolProp>\n"
			+ "<stringProp name=\"randomSeed\"></stringProp>\n"
			+ "<stringProp name=\"variableName\">RANDOMVARNAME</stringProp>\n"
			+ "</RandomVariableConfig>\n"
		  + "<hashTree/>\n";
	
	

	public enum LiaAppCorrelationRuleSettings {
		ticket("ticket", "${ticket}"), 
		form("form_UID", "${form_UID}"),
		dest_url("dest_url","${server}:8080"),
		postRandom("tinyMceEditor", "${__time(MMddyyyy_hhmm,)}_whocareswhateverisinthecontent");
		

		private final String name;
		private final String defaultValue;

		private LiaAppCorrelationRuleSettings(String strNewName, String strNewDefault) {
			this.name = strNewName;
			this.defaultValue = strNewDefault;
		}

		public String defaultValue() {
			return defaultValue;
		}

		public String getName() {
			return name;
		}

	}
}