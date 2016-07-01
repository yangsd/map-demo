package com.map.demo.util;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {

	private JsonUtil(){}

	public static <T> T parseObject(String json,Class<T> clazz){
		return JSON.parseObject(json, clazz);
	}

	public static String toJSONString(Object object){
		return JSON.toJSONString(object);
	}
}
