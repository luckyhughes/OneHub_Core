package com.onetesthub.perf.core.proxy;

public enum FilterRequestsType {
	
	PNG(".png"), ICO(".ico"), CSS(".css"), JPS(".jpg"), GIF(".gif"), WOFF(".woff"),JS(".js");
	
	private String reqType;
	
	FilterRequestsType(String reqType)
	{
		this.reqType = reqType;
	}
	
	public String getRequestsType(){
		
		return reqType;
		
	}

}
