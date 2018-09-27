package com.qa.Hotel;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import Utilities.Constants;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class HotelSteps 
{
	private Response response;
	private RequestSpecification request;
	private ValidatableResponse jason;
	private JSONObject jObj = new JSONObject();
	String details = "";
	@Before
	public void tearDown()
	{
		RestAssured.baseURI = Constants.baseUri;
		request = given();
		request.header(Constants.contentType, Constants.contentJasonType);
		
	}
	@Given("^a hotel with name \"([^\"]*)\", description \"([^\"]*)\", city \"([^\"]*)\" and \"([^\"]*)\"$")
	public void a_hotel_with_name_description_city_and(String name, String description, String city, String rating)
	{
		request.header(Constants.contentType, Constants.contentJasonType);
		jObj.put("name", name);
		jObj.put("description", description);
		jObj.put("city", city);
		jObj.put("rating",rating);
		//add obj to body
		request.body(jObj.toString());
		
		response = request.post("/");
		
		System.out.println(response.getStatusCode());	
	}

	@When("^a user searches the hotel by the name \"([^\"]*)\"$")
	public void a_user_searches_the_hotel_by_the_name(String name)
	{
		response = request.get("/");
		JSONObject jObj = new JSONObject(response.body().asString());
		JSONArray jArray = jObj.getJSONArray("content");
		String n = "";
		JSONObject foundObj = null;
		System.out.println(response.body().toString());
		for (int i = 0; i < jArray.length(); i++) 
		{
			foundObj = jArray.getJSONObject(i);
			n = foundObj.getString("name");
			if(n.equals(name))
			{
				details +="\nHotel Name: "+ n;
				details +="\nDescription: "+ foundObj.getString("description");
				details +="\nCity: "+ foundObj.getString("city");
				details +="\nRating: "+ foundObj.getInt("rating");
				break;
			}
			else
			{
				foundObj = null;
			}
		}
		assertNotNull(foundObj);
	}


}
