package com.demo.pojo;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JSONConverter {
	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js1 =new JsonPath(response);
		return js1;
	}

	
	
}
