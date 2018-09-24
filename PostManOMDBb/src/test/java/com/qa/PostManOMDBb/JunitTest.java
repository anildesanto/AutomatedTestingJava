package com.qa.PostManOMDBb;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.parser.Parser;
import org.junit.Test;

import Utilities.Constants;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.*;
import io.restassured.specification.*;

public class JunitTest   
{
	private Response response;
	private ValidatableResponse json;
	private JSONArray lol;
	private RequestSpecification request;
	@Test
	public void test() 
	{
		 //String title = pm.response.json().Search[0].Title;
		 
//	    given()
//        	.contentType(ContentType.JSON)
//        		.when()
//        			.get(Constants.url+"&s=movie")
//        				.then().statusCode(200);

//		  request = given().contentType(ContentType.JSON);
//		  response = request.when().get(Constants.url+"&t=IT");
//		  System.out.println("response: " + response.prettyPrint());
//		  
//		  response.then().body("Year", equalTo("2017"));
		  
		  request = given().contentType(ContentType.JSON);
		  response =  request.when().get(Constants.url+"&s=movie");
		  ArrayList<String> list = new ArrayList<String>();
		  JSONObject obj = new JSONObject (response.body().asString());
		  //System.out.println(obj.get("Search"));
		  JSONArray jArray = obj.getJSONArray("Search");
		  for (Object object : jArray) 
		  {
			  JSONObject o = (JSONObject) object;
			  System.out.println(o.getString("Title"));
				
		  }
		 
		  
	}
}
