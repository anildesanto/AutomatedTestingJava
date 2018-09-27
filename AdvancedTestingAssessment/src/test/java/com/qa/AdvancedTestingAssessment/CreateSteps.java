package com.qa.AdvancedTestingAssessment;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import Utilities.Constants;
import cucumber.api.PendingException;
import cucumber.api.java.*;
import cucumber.api.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CreateSteps 
{

	private Response response;
	private RequestSpecification request;
	private ValidatableResponse jason;
	private JSONObject jObj = new JSONObject();
	
	@Before
	public void tearDown()
	{
		RestAssured.baseURI = Constants.baseUri+"owners";
		request = given();
		request.header(Constants.contentType, Constants.contentJasonType);
	
	}
	@Given("^an owner with firstName \"([^\"]*)\", lastName \"([^\"]*)\", address \"([^\"]*)\", city \"([^\"]*)\" and telephone \"([^\"]*)\" is added$")
	public void an_owner_with_firstName_lastName_address_city_and_telephone_is_added(String firstName, String lastName, String address, String city, String telephone)
	{
		JSONArray petsArray = new JSONArray();
//		JSONObject pet1 = new JSONObject();
//		JSONObject pet2 = new JSONObject();
//		 "birthDate": "2018-09-27T09:49:30.034Z",
//	      "id": 0,
//	      "name": "string",
//	      "owner": {},
//	      "type": {
//	        "id": 0,
//	        "name": "string"
//		JSONObject owner = new JSONObject();
//		JSONObject type = new JSONObject();
//		pet1.put("birthDate", "2018-09-27");
//		pet1.put("name", "doggy");
//		pet1.put("owner", owner);
//		pet1.put("type", type);
		//petsArray.put(pet1);
		//==================================
		jObj.put("firtsName", firstName);
		jObj.put("lastName",lastName);
		jObj.put("address", address);
		jObj.put("city", city);
		jObj.put("telephone",telephone);
		jObj.put("pets",petsArray);

		//System.out.println(request.get("/").body().prettyPrint());
		//add obj to body
		request.body(jObj.toString());
		response = request.post("/");
		System.out.println(response.body().prettyPrint());
		System.out.println(response.getStatusCode());	
	}

	@When("^the admin selects the owner by \"([^\"]*)\"$")
	public void the_admin_selects_the_owner_by(String arg1)
	{
		
	}

	@Then("^the owner \"([^\"]*)\" along with other details are displayed$")
	public void the_owner_along_with_other_details_are_displayed(String arg1)
	{
		//jason = response.then().body("Error", equalTo(null));
	}
}
