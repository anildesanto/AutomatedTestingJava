package com.qa.AdvancedTestingAssessment;


import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.Constants;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.*;

public class CreateOwnerSelinium 
{
	private ChromeDriver driver;
	private ExtentTest test;
	private Boolean check = false;
	private ExtentReports report;
	private Actions action;
	private AddOwnerPage addOwnerPage;
	private OwnersPage ownersPage;
	
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
	@Before
	public void setup()
	{
		System.setProperty(Constants.driverKey, Constants.path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		report = TestRunner.report;
		action = new Actions(driver);
		addOwnerPage = PageFactory.initElements(driver, AddOwnerPage.class);
		ownersPage = PageFactory.initElements(driver, OwnersPage.class);
	}
	
	@Given("^an admin using page \"([^\"]*)\"$")
	public void an_admin_using_page(String arg1)
	{
		test = report.startTest("Test");
		test.log(LogStatus.INFO, "Loading Page...");
		driver.get(arg1);
		
	}

	@When("^I add new owner with firstName \"([^\"]*)\", lastName \"([^\"]*)\", address \"([^\"]*)\", city \"([^\"]*)\" and telephone \"([^\"]*)\"$")
	public void i_add_new_owner_with_firstName_lastName_address_city_and_telephone(String firstName, String lastName, String address, String city, String telephone)
	{
		test.log(LogStatus.INFO, "Creating owner...");
		addOwnerPage.addOwner(firstName, lastName, address, city, telephone);
		test.log(LogStatus.INFO, "Owner with First Name: "+firstName+" Last Name: "+lastName+ " Address: "+address + " City: "+ city+ " Telephone: "+telephone);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(ownersPage.getOwnersListHeader())); 
		check = ownersPage.getOwnersListHeader().isDisplayed();
		reporting(check, "New Owner Added Successfully", "Failed to Create Owner");
	}

	@When("^I go to the owners page \"([^\"]*)\"$")
	public void i_go_to_the_owners_page(String website)
	{
		test.log(LogStatus.INFO, "Loading Owners page...");
		driver.get(website);
		test.log(LogStatus.INFO, "Owners page Loaded...");
	}

	@Then("^I find the owner with firstName \"([^\"]*)\" and lastName \"([^\"]*)\"$")
	public void i_find_the_owner_with_firstName_and_lastName(String firstName, String lastname)
	{
		check = ownersPage.findOwnerByName(firstName+ " "+lastname);
		reporting(check, "New Owner Added Successfully", "Failed to Create Owner");
	}

	@When("^I find the owner with firstName \"([^\"]*)\" and click on it$")
	public void i_find_the_owner_with_firstName_and_click_on_it(String arg1) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^update its details with lastName firstName \"([^\"]*)\" \"([^\"]*)\", address \"([^\"]*)\", city \"([^\"]*)\" and telephone \"([^\"]*)\"$")
	public void update_its_details_with_lastName_firstName_address_city_and_telephone(String arg1, String arg2, String arg3, String arg4, String arg5) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the correct details are now shown$")
	public void the_correct_details_are_now_shown() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@When("^add a new pet with name \"([^\"]*)\", birthday \"([^\"]*)\" and  type\"([^\"]*)\" and save it and$")
	public void add_a_new_pet_with_name_birthday_and_type_and_save_it_and(String arg1, String arg2, String arg3) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}

	@Then("^the correct details are now shown in the page$")
	public void the_correct_details_are_now_shown_in_the_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}


	@After
	public void tearDown() throws InterruptedException 
	{
		//Thread.sleep(3000);
		report.endTest(test);
		report.flush();
		driver.close();
	}

}
