package com.onetesthub.perf.core.jmxgenerator;

import com.onetesthub.perf.core.proxy.DriverDefault;

import net.lightbody.bmp.proxy.ProxyServer;

public class BaseProcessor {
	
	ProcessEntry procesentry;
	
	
	public BaseProcessor(ProxyServer server, ParamProcessor paramProcessor)
	{
		
		procesentry = new ProcessEntry(server, paramProcessor);
		
	}
	
	public void startJMX() {

		JMXProcessor.preJMX();

	}

	public void endJMX() {

		JMXProcessor.postJMX();
		

	}
	
	public void clearlog()
	{
		procesentry.cleanup();
		
		
	}
	
	public void logRequest(String pageName)
	{
		
		procesentry.processRequest(pageName);
		
	}
	
	

}
