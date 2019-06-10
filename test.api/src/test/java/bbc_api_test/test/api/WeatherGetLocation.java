package bbc_api_test.test.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;



public class WeatherGetLocation {
	
//	get the location by name 
	 
	 String baseurl = "https://www.metaweather.com/api";
	 String searchLocationEndpoint = "/location/search/";
	 String location = "London";
	 int woeid;
	 String woeidSearchEndpoint = "/location/"+woeid ;
	 Response resp = null;  
	 String actualLocation = null;
	 String actualTodayWeather = null;
	 String actualTomorrowWeather = null;
	 String actualDayAfterTomorrowWeather = null ;
	 String actual2daysAfterTommorowWeather = null;
	 
	 
	
	public void getLocation() {
		
		 resp = (Response) given().
						parameter("query", location).
						when().
						get(baseurl + searchLocationEndpoint + location).
						then().
						contentType(ContentType.JSON);
				
		
		

}
	
	
	 public void statusCode () {
		 
//			Assert.assertEquals(resp.getStatusCode(), 200);
		 
		 if (resp.getStatusCode()==200) {
				
				System.out.println("Search API is working fine");
				
			} else {
				
				System.out.println("Search API is down");
			}
		 
	}
	 
	 public void checkLocation() {
		 

		 actualLocation = resp.
								then().
								contentType(ContentType.JSON).
								extract().
								path("[0].title");
		
	
		if (actualLocation.equalsIgnoreCase(location)) {
			
			System.out.println("Seached right location. Test Pass");
			
		} else {
			
			System.out.println("Incorrect serach location. Test fail ");
			
		}
		
	 }
	
	 public int getWoeid() {
		 
		 		woeid = resp.
					then().
					contentType(ContentType.JSON).
					extract().
					path("[0].woeid");
		 		return woeid;
		 
	 }
	
	public void getWeatherForcast() {
		
		resp = when().
				get(baseurl + woeidSearchEndpoint);
		
	}
		
	public void checkWeather(String days, String expectedWeather) {
		
		if (days == "Today's Weather") {
		  
			actualTodayWeather = resp.
				then().contentType(ContentType.JSON).
				extract().
				path("consolidated_weather.[0].weather_state_name, arguments");
		 
		 Assert.assertEquals(actualTodayWeather, expectedWeather,
					"Today's weather is not as expected");
		
		} else if (days == "Tommorrow's Weather") {
		  
			actualTomorrowWeather = resp.
				then().contentType(ContentType.JSON).
				extract().
				path("consolidated_weather.[1].weather_state_name, arguments");
		 
		 Assert.assertEquals(actualTomorrowWeather, expectedWeather,
					"Tomorrow's weather is not as expected");
		
		} else if (days == "Tomorrow+1 Weather") {
		 
			actualDayAfterTomorrowWeather = resp.
				then().contentType(ContentType.JSON).
				extract().
				path("consolidated_weather.[2].weather_state_name, arguments");
		 
		 Assert.assertEquals(actualDayAfterTomorrowWeather, expectedWeather,
					"Tomorrow+1 day weather is not as expected");
		
		} else {
		 
			actual2daysAfterTommorowWeather = resp.
				then().contentType(ContentType.JSON).
				extract().
				path("consolidated_weather.[3].weather_state_name, arguments");
		 Assert.assertEquals(actual2daysAfterTommorowWeather, expectedWeather,
					"Tomorrow+2 days weather is not as expected");
		}
	

	}
	
}
