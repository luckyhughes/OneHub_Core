package com.onetesthub.perf.core.jmxgenerator;

import java.io.File;
import java.io.IOException;

import net.lightbody.bmp.core.har.Har;

import com.onetesthub.perf.core.config.ConfigProcessor;

public class HarWriter {
	
	private static boolean scriptDebug;
	
	public boolean scriptDebugEnabled() {
		
		String debugValue = ConfigProcessor.getscriptDebug();
		
		if(debugValue.equals("true")) {
			
			scriptDebug = true;
			return scriptDebug;
		}
		
		else {
			
			scriptDebug = false;
			return scriptDebug;
			
		}
	}
	
	
	public File getFile() {
		
		File harfile = new File(ConfigProcessor.getharFilePath());
		return harfile;
	}
	
	public void writeToHar(Har har) {
		
		try {
			har.writeTo(getFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
