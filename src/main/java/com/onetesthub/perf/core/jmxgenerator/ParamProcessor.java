package com.onetesthub.perf.core.jmxgenerator;

import java.util.ArrayList;
import java.util.HashMap;

import com.onetesthub.perf.core.processor.response.ResponseIdentifier;

public class ParamProcessor {

	private HashMap<String, String> paramMap;
	private HashMap<String, String> valueMap;
	private HashMap<String, String> regexMap;
	
	//Extract response param processor
	private HashMap<String, ResponseIdentifier> respRegexMap;

	private String urlPath;

	private String paramType;

	public ParamProcessor() {

		paramMap = new HashMap<String, String>();
		valueMap = new HashMap<String, String>();
		regexMap = new HashMap<String, String>();
		
		respRegexMap = new HashMap<String, ResponseIdentifier>();
		
	}

	public void jmxReplaceParam(String paramVar, String paramName,
			String paramType) {
		
		if (paramType.contains("param")) {
			this.paramType = "param";
			paramMap.put(paramVar, paramName);
		} else if (paramType.contains("value")) {
			this.paramType = "value";
			valueMap.put(paramVar, paramName);
		}
		else if (paramType.contains("regex")) {
			this.paramType = "regex";
			regexMap.put(paramVar, paramName);
		}
		else {
			System.out.println("Error ::::::Invalid paramType !!!!!!");
		}

	}
	
	public void jmxSaveResponseParam(String respParamName, String respRegex,
			String respOrd) {
		
		ResponseIdentifier responseIdentifier = new ResponseIdentifier();
		
		responseIdentifier.setParamName(respParamName);
		responseIdentifier.setRegex(respRegex);
		responseIdentifier.setOrd(respOrd);
		
		respRegexMap.put(respParamName, responseIdentifier);
		
		
	}

	public String getParamType() {
		return paramType;
	}

	public HashMap<String, String> getParamMap() {

		return paramMap;
	}

	public HashMap<String, String> getValueMap() {

		return valueMap;
	}
	
	public HashMap<String, String> getRegexMap() {

		return regexMap;
	}
	
	public HashMap<String, ResponseIdentifier> getRespRegexMap() {

		return respRegexMap;
	}

	public void jmxReplaceUrlPath(String paramUrlPath) {

		this.urlPath = paramUrlPath;

	}

	public String getUrlPathParam() {

		return urlPath;
	}

	public void clearMap() {

		if (paramMap != null) {

			paramMap.clear();

		}

		if (urlPath != null) {

			urlPath = null;

		}

		if (valueMap != null) {

			valueMap.clear();

		}
		
		if (regexMap != null) {

			regexMap.clear();

		}
		
		if (respRegexMap != null) {

			respRegexMap.clear();

		}
		
		
		

	}

}
