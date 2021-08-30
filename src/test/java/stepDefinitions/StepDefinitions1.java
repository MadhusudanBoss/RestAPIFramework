package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import static org.junit.Assert.*;

import com.demo.java.APIResources;
import com.demo.java.TestDataBuild;
import com.demo.java.Utils;
import com.demo.pojo.AddPlace;
import com.demo.pojo.Location;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class StepDefinitions1 extends Utils{
	
	ResponseSpecification resspec;
	RequestSpecification res;
	Response response,responseGet;
	TestDataBuild data = new TestDataBuild();
	static String place_id; // Here we made place_id as static because initiialy this variable will be applicable for POST and GET methods, since for deleting the API we need same variable hence make it as static so that we can use it for all the operations.
	
	JsonPath js ;
	APIResources resourceAPI;
	
	@Given("^Add place payload with \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_place_payload_with(String name, String language, String address) throws IOException  {
			
					 res=given().spec(requestSpecification())
					.body(data.addPlaceAPI(name, language, address));

		}


	@When("^User calls \"([^\"]*)\" with \"([^\"]*)\" http request$")
	public void user_calls_with_http_request(String resource, String httpmethod) {
			
			 resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			 //Here we are basically,calling the constructor with value of resource which you pass.
			  resourceAPI = APIResources.valueOf(resource);
			 System.out.println(resourceAPI.getResource());
			 
			 if(httpmethod.equalsIgnoreCase("POST"))
			 response =res.when().post(resourceAPI.getResource());
					//then().spec(resspec).extract().response();
			 else if(httpmethod.equalsIgnoreCase("GET")) {
				 response =res.when().get(resourceAPI.getResource());
			 }else if(httpmethod.equalsIgnoreCase("DELETE")) {
				 response =res.when().get(resourceAPI.getResource());
			 }
		}
	
	@Then("^the API call got success with status code 200$")
		public void the_api_call_got_success_with_status_code() {
			
			Assert.assertEquals(response.getStatusCode(),200);
		}
		
	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
		public void in_response_body_is(String key, String expectedValue) {
			
			/*String resp1 = response.asString();
			 js = new JsonPath(resp1);
			 place_id=js.get("place_id");
			 System.out.println("The place id is :" + place_id);*/
			assertEquals(getJsonPath(response,key),expectedValue);
		}
		
	
	@And("^verify place id created maps to \"([^\"]*)\" using \"([^\"]*)\"$")
    public void verify_place_id_created_maps_to_something_using_something(String name, String resource) throws Throwable {
		System.out.println("We are entering into the GET METHOD");
		// first of all we need the place id then oly we can pass it as an argument for GET method.
		 place_id=getJsonPath(response,"place_id");
		 System.out.println("Place id is :" + place_id);
		//have request specification.
		 res=given().spec(requestSpecification()).queryParam("place id", place_id);
		 user_calls_with_http_request(resource, "GET");
		 String Name=getJsonPath(response,"name");
		 System.out.println("The Name is :" + Name);
		Assert.assertEquals(Name, name);
    }

	
	@Given("^DeletePlace Payload$")
    public void deleteplace_payload() throws Throwable {
		System.out.println("We are inside the deleteAPI method");
		res=given().spec(requestSpecification())
				.body("{\r\n" + 
						"    \"place_id\":\""+place_id+"\"\r\n" + 
						"}");
		
    }
}
 