Feature: PLACE API

@ADDPLACEAPI @REGRESSION
Scenario Outline:: To verfy whether the place has been added successfully added using ADD PLACE API
	
	Given BaseURI and Add Place Payload "<name>", "<address>","<language>"
	When  User calls "AddPlaceAPI" using HTTP "POST" method
	Then  API calls got successfull with a status code 200
	And   The "status" in the response is "OK"
	And   The "scope" in the response is "APP"
	And   Verfiy place id created maps to "<name>" using "GetPlaceAPI"

	
Examples:
		|name         |address                  |language  |
		|WC HOUSE     |221 B Baker Street       |English   |
		|BC HOUSE     |222 B Baker Park         |French    |
		
@DELETEPLACEAPI	@REGRESSION	
Scenario: To verify whether the delete functionality is working as expected
	Given BaseURI and Delete Payload
	When  User calls "DeletePlaceAPI" using HTTP "POST" method
	And   The "status" in the response is "OK"