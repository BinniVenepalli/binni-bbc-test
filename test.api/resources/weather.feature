Feature: Test stability of weather api
  I want to see how stable my api is 
  so I can relay on it while performing any request using it

  @test
  Scenario: Get the Location
    Given I send the request for location api
    Then I should see the status code is 200
    And I should see the correct location in response
    And I save the woeid for my location
   
  
 	@test
   Scenario Outline: Get the weateher forcast for next few day 
    Given I send the request for weather forcast using woeid
    Then I check the wether forcast for <days> is <expectedWeather>
   
    
    Examples: 
      | days 							| expectedWeather	| 
      | Today's Weather 	| Heavy Cloud   	|  
      | Tomorrow's weather| Showers			  	|     
			| Tomorrow+1 weather| Clear						|
			| Tomorrow+2 weather| Light Cloud			|
		
		@manual	
		Scenario: Get the weather forcast based on woeid
    Given I send the request for weather forcast using woeid
    Then I should see the weather forcast for next 5 days 
    And the response has the status code 200
    
    @manual
    Scenario: Verify response for incorrect loction
    Given I send request got search api with *** 
    Then I should see the rsponse has the status code 200
    And I should not see any location response back from the API
    
    @manual
    Scenario: Validate sreatch withour supplying location/search query 
    Given I send request got search api without giving any query
    Then I should not see any location response back from the API
    And I should see message for access denied 
    
    @manual 
    Scenario: Validate weather api without no woeid
     Given I send the request for weather forcast without woeid
     Then I should not see any weather report
     And I ahouls see 404 status code
    
    