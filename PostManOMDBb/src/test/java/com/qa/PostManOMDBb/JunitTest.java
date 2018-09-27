package com.qa.PostManOMDBb;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

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
	private RequestSpecification request;
	
	@Test
	public void authorize() 
	{
	    given()
        	.contentType(ContentType.JSON)
        		.when()
        			.get(Constants.url+"&s=movie")
        				.then().statusCode(200);

	}
	@Test
	public void jason() 
	{
		  request = given().contentType(ContentType.JSON);
		  response = request.when().get(Constants.url+"&t=IT");
		  System.out.println("response: " + response.prettyPrint());	  
		  response.then().body("Year", equalTo("2017"));
	}
	@Test
	public void listOfJasons() 
	{  
		  request = given().contentType(ContentType.JSON);
		  response =  request.when().get(Constants.url+"&s=movie");
		  
		  JSONObject obj = new JSONObject (response.body().asString());
		  //System.out.println(obj.get("Search"));
		  System.out.println("response: " + response.prettyPrint());	  
		  Boolean found= false;
		  String title ="";
		  JSONArray jArray = obj.getJSONArray("Search");
		  
		  for (int i = 0; i < jArray.length(); i++) 
		  {
			  title  = jArray.getJSONObject(i).getString("Title");
			  if(title.equals("Scary Movie"))
			  {
				  found = true;
				  //break;
			  }
			  System.out.println(title); 
		  }
		  
//		  for (Object object : jArray) 
//		  {
//			  JSONObject o = (JSONObject) object;
//			  title  = o.getString("Title");
//			  if(title.equals("Scary Movie"))
//			  {
//				  found = true;
//				  break;
//			  }
//			  System.out.println(title);
//		  }
		 
		 assertTrue(found);
	}
}
