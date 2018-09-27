package com.qa.PostManOMDBb;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

import Utilities.Constants;
import cucumber.api.PendingException;
import cucumber.api.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CucumberSteps 
{
	private Response response;
	private ValidatableResponse jason;
	private RequestSpecification request;
	String title = "Guardians of the Galax";
	
	@Given("^a film exists with the Title Guardians of the Galaxy Two$")
	public void a_film_exists_with_the_Title_Guardians_of_the_Galaxy_Two()
	{
		request = given().contentType(ContentType.JSON);
		response = request.when().get(Constants.url+"&t="+title);
		response.then().body("Error", equalTo(null));
		System.out.println("response: " + response.prettyPrint());
	}

	@When("^a user retrieves the film by the title Guardians of the Galaxy Two$")
	public void a_user_retrieves_the_film_by_the_title_Guardians_of_the_Galaxy_Two()
	{
		//response = request.when().get(Constants.url+"&t="+title);
		response.getBody();
		System.out.println("response: " + response.prettyPrint());
	}

	@Then("^the status code reads (\\d+)$")
	public void the_status_code_reads(int arg1)
	{
	   jason = response.then().statusCode(arg1);
	}

	@Given("^a film exists with the Title IT$")
	public void a_film_exists_with_the_Title_IT()
	{
		request = given().contentType(ContentType.JSON);
	}

	@When("^a user retrieves the film by the title IT$")
	public void a_user_retrieves_the_film_by_the_title_IT()
	{
		response = request.when().get(Constants.url+"&t=IT");
	}

	@Then("^the Rated Field is equal to R$")
	public void the_Rated_Field_is_equal_to_R() throws Throwable 
	{
		jason =  response.then().body("Rated", equalTo("R"));   
	}

	@Given("^a film exists with the Title \"([^\"]*)\"$")
	public void a_film_exists_with_the_Title(String arg1)
	{
		request = given().contentType(ContentType.JSON);
		response = request.when().get(Constants.url+"&t="+arg1);
		jason = response.then().body("Title", equalTo(arg1));
	}

	@When("^a user retrieves the film by the title \"([^\"]*)\"$")
	public void a_user_retrieves_the_film_by_the_title(String arg1) 
	{
		jason =  response.then().body("Title", equalTo(arg1));   
	}

	@Then("^the Rated Field is equal to \"([^\"]*)\"$")
	public void the_Rated_Field_is_equal_to(String arg1)
	{
		jason = response.then().body("Rated", equalTo(arg1));
	}
}
