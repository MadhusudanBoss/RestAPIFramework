package com.demo.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	static RequestSpecification req;
	
	public RequestSpecification requestSpecification() throws IOException {
		
		// here intially req object it null so, it executes for the 1st time.
		if (req == null) {
		PrintStream stream = new PrintStream(new FileOutputStream("logging.txt"));
		//here we are using RequestLoggingFilter for logging into the text files. instea of log().all(), we use filter at framework level.
		req =new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUrl")).addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(stream)).addFilter(ResponseLoggingFilter.logResponseTo(stream))
				.setContentType(ContentType.JSON).build();
		//return req;
		}
		return req;
		
	}
	
	public static String getGlobalValues(String URL) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\mramdas\\eclipse-workspace\\RestMavenFramework\\src\\main\\java\\com\\demo\\java\\global.properties");
		prop.load(fis);
		
		return prop.getProperty(URL);
	}
	
	
	public String getJsonPath(Response response,String key) {
		    String resp = response.asString();
		    JsonPath js = new JsonPath(resp);
		    return js.get(key).toString();
	}
}
