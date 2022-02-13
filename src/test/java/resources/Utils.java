package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification req;
	
	public   RequestSpecification reqSpec() throws IOException {
		
		PrintStream print = new PrintStream(new FileOutputStream("Log.txt"));
		req = new RequestSpecBuilder()
				  .setBaseUri(getProperty("BaseURI"))
				  .setContentType("application/json")
				  .addQueryParam("key", "qaclick123")
				  .addFilter(RequestLoggingFilter.logRequestTo(print))
				  .addFilter(ResponseLoggingFilter.logResponseTo(print))
				  .build();
		
		return req;
		
		
	}
	
	public static String getProperty(String key) throws IOException {
		
		Properties prop = new Properties();
		String File = System.getProperty("user.dir")+"//src//test//java//resources//Property.properties";
		FileInputStream file = new FileInputStream(File);
		prop.load(file);
		return prop.getProperty(key);
		
	}
	
	public static String getJson(String res, String key) {
		
		JsonPath js = new JsonPath(res);
		return js.get(key).toString();
		
	}

	
	
}
