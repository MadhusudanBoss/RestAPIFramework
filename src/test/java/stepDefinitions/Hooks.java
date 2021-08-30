package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before("@AddPlace")
	public void beforeScenario1() {
		System.out.println("This is before method for @AddPlace");
	}
	
	@After("@AddPlace")
	public void afterScenario1() {
		System.out.println("This is after method for @AddPlace");
	}
	
	@Before("@DeletePlace")
	public void beforeScenario() throws Throwable {
		System.out.println("This is beforeScenario which is getting executed for @DeletePlace");
		// write a code which gives us the place_id. Here this method should be executed, when place_id is NULL.
		StepDefinitions1 stepDef = new StepDefinitions1();
		if(StepDefinitions1.place_id==null) {
		stepDef.add_place_payload_with("Ajama", "Hindi", "Norway");
		stepDef.user_calls_with_http_request("addPlaceAPI", "POST");
		stepDef.verify_place_id_created_maps_to_something_using_something("Ajama", "getPlaceAPI");
    	}
	}
	
	@After("@DeletePlace")
	public void afterScenario() {
		System.out.println("This is after scenario which is being executed for @DeletePlace");
	}
	
	
}
