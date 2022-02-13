package stepDefination;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Resources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class StepDefination extends Utils{
	
	RequestSpecification req;
	static Response res;
	String response;
	TestDataBuild data  = new TestDataBuild();
	static String place_id;
	
	
	@Given("BaseURI and Add Place Payload {string}, {string},{string}")
	public void base_uri_and_add_place_payload(String name, String addr, String lang) throws IOException {
		
		req = given().spec(reqSpec()).body(data.getData(name, addr, lang));
	    
	}
	
	@When("User calls {string} using HTTP {string} method")
	public void user_calls_using_http_method(String resource, String method) {
		
		//resource selection
		Resources resAPI = Resources.valueOf(resource);
		System.out.println(resAPI.getResource());
		
	    if(method.equalsIgnoreCase("post"))
	    	res = req.when().post(resAPI.getResource());
	    else if(method.equalsIgnoreCase("get"))
	    	res = req.when().get(resAPI.getResource());
	    else if(method.equalsIgnoreCase("delete"))
	    	res = req.when().delete(resAPI.getResource());
		
	}
	
	@Then("API calls got successfull with a status code {int}")
	public void api_calls_got_successfull_with_a_status_code(Integer int1) {
	    
		assertEquals(200, res.getStatusCode());
		
	}
	
	
	@Then("The {string} in the response is {string}")
	public void the_in_the_response_is(String key, String expres) {
		
		response = res.asString();
		String actRes = Utils.getJson(response, key);
		assertEquals(actRes, expres);
	    
	}
	
	@Then("Verfiy place id created maps to {string} using {string}")
	public void verfiy_place_id_created_maps_to_using(String expname, String resource) throws IOException {
	    
		response = res.asString();
		place_id = Utils.getJson(response, "place_id");
		req = given().spec(reqSpec()).queryParam("place_id", place_id);
		user_calls_using_http_method(resource, "get");
		
		String actName = Utils.getJson(res.asString(), "name");
		
		assertEquals(expname, actName);
		
		
	}
	
	@Given("BaseURI and Delete Payload")
	public void base_uri_and_delete_payload() throws IOException {
	    
		req = given().spec(reqSpec()).body(TestDataBuild.deletePayload(place_id));
		
	}





}
