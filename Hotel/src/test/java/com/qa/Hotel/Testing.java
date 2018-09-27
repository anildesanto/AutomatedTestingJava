package com.qa.Hotel;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import Utilities.Constants;
import cucumber.api.java.Before;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import io.restassured.specification.RequestSpecification;

public class Testing 
{
	private Response response;
	private RequestSpecification request;
	private ValidatableResponse jason;
	private JSONObject jObj = new JSONObject();
	
	@Test
	@Ignore
	public void testPost() 
	{
		RestAssured.baseURI = Constants.baseUri;
		RequestSpecification request = given();
		request.header(Constants.contentType, Constants.contentJasonType);
		
		jObj.put("name", "Ola");
		jObj.put("description", "Very basic, small rooms but clean");
		jObj.put("city", "Santa Ana");
		jObj.put("rating", "2");
		
		System.out.println(request.body(jObj.toString()));
		
		response = request.post("/");
		
		System.out.println(response.getStatusCode());	
	}
	@Test
	@Ignore
	public void testGet()
	{
		RestAssured.baseURI = Constants.baseUri;
		RequestSpecification request = given();
		request.header(Constants.contentType, Constants.contentJasonType);
		
		response = request.get("/");
		System.out.println(response.body().prettyPrint());
		
		JSONObject jObj = new JSONObject(response.body().asString());
		JSONArray jArray = jObj.getJSONArray("content");
		Integer id = 1;
		JSONObject foundObj = null;
		for (int i = 0; i < jArray.length(); i++) 
		{
			foundObj = jArray.getJSONObject(i);
			id = foundObj.getInt("id");
			System.out.println("Id is: "+ id);
			if(id.equals(i))
			{
				System.out.println("YAY! FOUND IT!");
				break;
			}
			else
			{
				foundObj = null;
			}
		}
		assertNotNull(foundObj);
	}
	
	@Test
	@Ignore
	public void testDeleteAll()
	{
		RestAssured.baseURI = Constants.baseUri;
		RequestSpecification request = given();
		request.header(Constants.contentType, Constants.contentJasonType);
		response = request.get("/");
		System.out.println(response.body().prettyPrint());
		
		JSONObject jObj = new JSONObject(response.body().asString());
		JSONArray jArray = jObj.getJSONArray("content");
		int id = 1;
		JSONObject foundObj = null;
		for (int i = 0; i < jArray.length(); i++) 
		{
			foundObj = jArray.getJSONObject(i);
			//JSONArray jAdressesArray = foundObj.getJSONArray("adresses");
			id = foundObj.getInt("id");
			//delete
			given().contentType(ContentType.JSON).when()
					.delete(Constants.baseUri+id);
		}
		assertTrue(jArray.get(0) == null);
	}
	
	
	@Test
	@Ignore
	public void testAddLoads()
	{
		RestAssured.baseURI = Constants.baseUri;
		RequestSpecification request = given();
		request.header(Constants.contentType, Constants.contentJasonType);
		response = request.get("/");
		System.out.println(response.body().prettyPrint());
		
		JSONObject jObj = new JSONObject(response.body().asString());
		JSONArray jArray = jObj.getJSONArray("content");
		
		//==============================
		JSONArray lotsArray = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "Ola");
		obj1.put("description", "oooh");
		obj1.put("city", "lalaaaa");
		obj1.put("rating", "2");
		//======================

		lotsArray.put(obj1);
		JSONObject obj2 = new JSONObject();
		obj2.put("name", "Okay");
		obj2.put("description", "loool");
		obj2.put("city", lotsArray);
		obj2.put("rating", "0");
		//lotsArray.put(obj1.toString());
		//======================
		//jObj.put("idk", lotsArray);
		System.out.println(request.body(obj2.toString()));
		
		response = request.post("/");
		
	}
	@Test
	//@Ignore
	public void testPostLoads()
	{
		RestAssured.baseURI = Constants.baseUri;
		RequestSpecification request = given();
		request.header(Constants.contentType, Constants.contentJasonType);
		response = request.get("/");
		System.out.println(response.body().prettyPrint());
		
		JSONObject jObj = new JSONObject(response.body().asString());
		JSONArray jArray = jObj.getJSONArray("content");
		
		//==============================
		JSONArray lotsArray = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("name", "Ola");
		obj1.put("description", "oooh");
		obj1.put("city", "lalaaaa");
		obj1.put("rating", "2");
		//======================

		lotsArray.put(obj1);
		JSONObject obj2 = new JSONObject();
		obj2.put("name", "Okay");
		obj2.put("description", "loool");
		obj2.put("city", "ooooh");
		obj2.put("rating", "0");
		lotsArray.put(obj2);
		//lotsArray.put(obj1.toString());
		//======================
		//jObj.put("idk", lotsArray);
		
		JSONObject finalObj = new JSONObject();
		finalObj.put("content", lotsArray);
		
		System.out.println(request.body(obj2.toString()));
		
		response = request.post("/");
		
	}
	
}
