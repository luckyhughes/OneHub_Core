package com.onetesthub.perf.adapter.ui;

import net.lightbody.bmp.proxy.ProxyServer;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.onetesthub.perf.api.ui.PerfWebDriver;
import com.onetesthub.perf.core.config.ConfigProcessor;
import com.onetesthub.perf.core.jmxgenerator.BaseProcessor;
import com.onetesthub.perf.core.jmxgenerator.JMXProcessor;
import com.onetesthub.perf.core.jmxgenerator.ParamProcessor;
import com.onetesthub.perf.core.jmxgenerator.ProcessEntry;
import com.onetesthub.perf.core.proxy.DriverDefault;
import com.onetesthub.perf.csvconfigprocessor.CSVDataPrep;


public class BaseUIPerfTest {

	//public static void main(String[] args) {
		// Create a new instance of the Firefox driver
		// Notice that the remainder of the code relies on the interface,
		// not the implementation.
		protected static PerfWebDriver driver = null;
		protected ProxyServer server;
		private BaseProcessor baseprocessor;
		private CSVDataPrep csvdataprep = new CSVDataPrep();
		protected ParamProcessor jmx = new  ParamProcessor();
		protected static String PageName;
		protected static String baseUrl;
		protected static int webDriverTimeout=30;
		protected WebDriverWait driverWait;
		
		static Logger log = Logger.getLogger(BaseUIPerfTest.class.getName());

		
		@BeforeClass
		public void BasePerformanceTest()
		{
		
		System.out.println("In BeforeClass");
		//DriverDefault.initialize();
		driver = DriverDefault.getDriver();
		server = DriverDefault.getProxy();
		baseUrl = DriverDefault.getBaseUrl();
		
		System.out.println("BaseUIPerfTest BaseUrl is: " + baseUrl);
		
		baseprocessor = new BaseProcessor(server, jmx);
		JMXProcessor.preJMX();
		csvdataprep.init();
		preResult();
		
		//log.info("Total time for page " + PageName + "is  :" + ttime);
	
		}
		
		public void preResult(){
			
			log.info("<html>");
			log.info("<body>");
			log.info("<br>");
			log.info("<font color=\"#0B2161\"> <b>Asset Management UI Performance Result</b> </font>");
			
			log.info("<table border=\"1\">");
			log.info("<tr bgcolor=\"#F5A9A9\">");
			log.info("<th>Page Name</th>");
			log.info("<th>Response Time in sec</th>");
			log.info("</tr>");

			
		}
		
		public void postResult(){
			
			log.info("</table>");
			log.info("<br>");
			log.info("</body>");
			log.info("</html>");
			
		}
		
		@BeforeMethod
		public void clear()
		{
		System.out.println("Page Name is : " + PageName);
		
		//baseprocessor.clearlog();
		
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		}
		
		
		@AfterMethod
		public void log() throws InterruptedException
		{
		
		Thread.sleep(5000);
			
		System.out.println("In @AfterMethod");
		
		System.out.println("Logging Requests");
		
		baseprocessor.logRequest(PageName);
		
		System.out.println("Har requests cleanup");
		
		baseprocessor.clearlog();
		
		System.out.println("Page title is: " + driver.getTitle());
		
		}
		
		@AfterClass
		public void clean() throws InterruptedException
		{		
			System.out.println("In AfterClass");
			
			postResult();
			
			Thread.sleep(2000);
			System.out.println("----------Stopping Proxy -------");
			try {
				server.stop();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			JMXProcessor.postJMX();
			
			System.out.println("Stopping Proxy");
			
			DriverDefault.stopProxy();
			
		}

}
