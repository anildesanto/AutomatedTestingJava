package com.qa.AdvancedTestingAssessment;

import static io.restassured.RestAssured.given;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.Constants;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static org.junit.Assert.*;
public class DeleteSteps 
{
	public static ExtentReports report = new ExtentReports(Constants.saveDirectory+"DeleteStepsReport.html", true);
	private ExtentTest test;
	private Boolean check = false;
	private Response response;
	private RequestSpecification request;
	private ValidatableResponse jason;
	private int id;
	@Given("^an owner with id \"([^\"]*)\"$")
	public void an_owner_with_id(int id) throws Throwable
	{
		test = report.startTest("Deleting");
		this.id = id;
		request = given();
		request.header(Constants.contentType, Constants.contentJasonType);
		reporting(true, "Start Search for owner with id: "+id, "Failed to begin Search for owner with id: "+id);	
	}

	@When("^the owner is deleted$")
	public void the_owner_is_deleted()
	{
		response = request.when()
				.delete(Constants.baseUri+"owners"+"/"+id);
		 System.out.println(response.body().prettyPrint());
		reporting(true, "Searching owner with id: "+id, "Owner with id not found: "+id);
	}

	@Then("^the owner is not found$")
	public void the_owner_is_not_found() throws Throwable
	{
		jason = response.then().statusCode(204);
		reporting(true, "Owner Deleted Succsessfully", "Unable to Delete Owner");
	}
	public void reporting(Boolean check, String passMessage, String failMessage)
	{
			
		if(check)
		{
			test.log(LogStatus.PASS, passMessage);
		}
		else
		{
			test.log(LogStatus.FAIL, failMessage);
		}
		assertTrue(check);
	}

	@After
	public void tearDown() throws InterruptedException 
	{
		report.endTest(test);
		report.flush();
	}
}
