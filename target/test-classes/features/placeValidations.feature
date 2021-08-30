Feature: Validating the ADD place API's

@AddPlace 
Scenario Outline: Verify if place is being successfully added using ADD place API
	Given Add place payload with "<name>" "<language>" "<address>"
	When User calls "addPlaceAPI" with "post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place id created maps to "<name>" using "getPlaceAPI"
	
	
Examples:
	| name      | language     | address           |
	|VishwasVijay  | German       | 211 church street |
	
	
@DeletePlace
Scenario: Verify if delete place API is working correctly
	Given DeletePlace Payload
	When User calls "deletePlaceAPI" with "post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
