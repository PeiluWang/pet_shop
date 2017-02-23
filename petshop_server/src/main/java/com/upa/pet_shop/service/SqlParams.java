package com.upa.pet_shop.service;

import java.util.HashMap;

public class SqlParams{

	private HashMap<String, Object> params;
	
	public SqlParams(){
		params = new HashMap<String, Object>();
	}
	
	public SqlParams setParam(String key, Object value){
		params.put(key, value);
		return this;
	}

	public HashMap<String, Object> getParams(){
		return params;
	}

}
