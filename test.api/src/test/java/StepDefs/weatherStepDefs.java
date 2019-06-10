package StepDefs

import bbc_api_test.test.api.WeatherGetLocation;
import cucumber.api.java.en.Given;

public class weatherStepDefs {
	
	WeatherGetLocation weather = new WeatherGetLocation();
	
	@Given("^I send the request for location api$")
	public void requestLocation(){
		weather.getLocation();
	}
	
	@Given("^I should see the status code is 200$")
	public void statuscode200(){
		weather.statusCode();
	}
	
	@Given("^I should see the correct location in response$")
	public void correctLocation(){
		weather.checkLocation();
	}
	
	@Given("^I save the woeid for my location$")
	public void saveWoeid(){
		weather.getWoeid();
	}
	
	@Given("^I send the request for weather forcast using woeid$")
	public void weatherForcast(){
		weather.getWeatherForcast();
	}
	
	
	@Given("^I check the wether forcast for \"(.*?)\" is \"(.*?)\"$")
	public void expectedWeather(String days, String expectedWeather){
		weather.checkWeather(days);
	}
	
	
	
}
