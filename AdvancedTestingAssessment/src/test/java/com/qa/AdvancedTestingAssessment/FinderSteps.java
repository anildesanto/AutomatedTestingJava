package com.qa.AdvancedTestingAssessment;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.Constants;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class FinderSteps
{
	private Response response;
	private RequestSpecification request;
	private ValidatableResponse jason;
	private String lastName;
	private int id;
	public static ExtentReports report = new ExtentReports(Constants.saveDirectory+"DeleteStepsReport.html", true);
	private ExtentTest test;
	private Boolean check = false;
	@Given("^the owners list$")
	public void the_owners_list()
	{
		test = report.startTest("Find owner By last Name");
		test.log(LogStatus.INFO, "Starting search...");
		request = given();
		request.header(Constants.contentType, Constants.contentJasonType);
	}

	@When("^the admin searches the owner by \"([^\"]*)\"$")
	public void the_admin_searches_the_owner_by(String lastName) 
	{
		test.log(LogStatus.INFO, "Finding Owner with last name: "+ lastName);
		this.lastName = lastName;
		  response = request.when().get(Constants.baseUri+"owners"+"/*/lastname/"+lastName);
		 // System.out.println(response.body().prettyPrint());
	}

	@Then("^the owner is found$")
	public void the_owner_is_found()
	{
		jason = response.then().statusCode(200);
		test.log(LogStatus.PASS, "Owner Found!");
	}
	
	@Given("^the pets list$")
	public void the_pets_list()
	{
		test = report.startTest("Find Pet By ID");
		request = given();
		request.header(Constants.contentType, Constants.contentJasonType);
	}

	@When("^the admin searches the pet by \"([^\"]*)\"$")
	public void the_admin_searches_the_pet_by(int id)
	{
		test.log(LogStatus.INFO, "Finding Pet with ID: "+ id);
		this.id = id;
		  response = request.when().get(Constants.baseUri+"pets/"+id);
		  //System.out.println(response.body().prettyPrint());
	}

	@Then("^the pet is found$")
	public void the_pet_is_found()
	{
		jason = response.then().statusCode(200);
		test.log(LogStatus.PASS, "Pet Found!");
	}
	@After
	public void tearDown() throws InterruptedException 
	{
		report.endTest(test);
		report.flush();
	}

}
