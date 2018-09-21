package com.qa.Demo2;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.Constants;
import Utilities.ExcelUtils;
import Utilities.HelperMethods;
import cucumber.api.PendingException;
import cucumber.api.java.*;
import cucumber.api.java.en.*;

public class Demo2StepsCucumber 
{
	public ChromeDriver driver;
	public ExtentTest test;
	public Boolean check = false;
	public ExtentReports report;
	public String testTitle;
	@Before
	public void setup()
	{
		System.setProperty(Constants.driverKey, Constants.path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		report = TestRunnerCucumber.report;
		ExcelUtils.setExcelFile(Constants.saveDataDirectory + Constants.fileTestData, 0);
	}
	@Given("^\"([^\"]*)\" is entered as username And \"([^\"]*)\" is entered as password  And I click a button$")
	public void is_entered_as_username_And_is_entered_as_password_And_I_click_a_button(String name, String pass) throws InterruptedException
	{
		try 
		{
			testingCreateUser(name, pass);
		} catch (UnhandledAlertException e) 
		{
			test.log(LogStatus.WARNING, "Input Needs to be 4 Characters long");
			test.log(LogStatus.FAIL, "Failed to create User");
			assertTrue(false);
		}
		report.endTest(test);
	}
	@When("^I go to the website and try to Log in with \"([^\"]*)\" as username and \"([^\"]*)\" as password$")
	public void i_go_to_the_website_and_try_to_Log_in_with_as_username_and_as_password(String name, String pass)
	{
		try 
		{
			testingUserLogIn(name, pass);
		} catch (UnhandledAlertException e) 
		{
			test.log(LogStatus.WARNING,"Username: "+ name +" with Password: "+ pass + " Not Found. Unable to Log In User");
			test.log(LogStatus.FAIL, "Failed to Log In User");
			assertTrue(false);
		}
		report.endTest(test);
	}

	@Then("^I log in and see account$")
	public void i_log_in_and_see_account()
	{
		assertTrue("Error",check);
	}
	
	protected void reporting(String passMessage, String failMessage)
	{
		ExcelUtils.setCellData(testTitle+ " "+Constants.count, Constants.count, 0);
		if(check)
			ExcelUtils.setCellData("Test Passed", Constants.count++, 1);
		else
			ExcelUtils.setCellData("Test Failed", Constants.count++, 1);
		
		assertTrue(failMessage,check);
		//screenshot
		//HelperMethods.screenshot(driver,Constants.saveDirectory);
	}
	
	public void testingCreateUser(String name, String pass)
	{
		testTitle  = "Create user";
		test =  report.startTest(testTitle);
		driver.get(DemoHomePage.url);
		//=========================== Create Account ==============
		test.log(LogStatus.INFO, "Home Page Loaded");
		DemoHomePage demoHomePage = PageFactory.initElements(driver, DemoHomePage.class);
		demoHomePage.getAddButton().click();
		test.log(LogStatus.INFO, "Add User Page Loaded");
		DemoAddUserPage demoAddUserPage = PageFactory.initElements(driver, DemoAddUserPage.class);
		demoAddUserPage.registerUser(name, pass);
		test.log(LogStatus.INFO, "User Details entered");
		test.log(LogStatus.INFO, "Username: "+name+ " Password: "+ pass);
		test.log(LogStatus.INFO," Creating User...");
		check = demoAddUserPage.getUserName().getAttribute("value").equals("");
		reporting("User created Successfully", "Unable to create User");
	}
	public void testingUserLogIn(String name, String pass)
	{
		testTitle  = "Log In user";
		test =  report.startTest(testTitle);
		//============== Log In ==========
		driver.get(DemoLogInPage.url);
		test.log(LogStatus.INFO, "Log In Page Loaded");
		DemoLogInPage demoLogInPage = PageFactory.initElements(driver, DemoLogInPage.class);
		demoLogInPage.logInUser(name, pass);
		test.log(LogStatus.INFO, "User Details entered");
		test.log(LogStatus.INFO, "Username: "+name+ " Password: "+ pass);
		test.log(LogStatus.INFO,"Logging In...");
		check = demoLogInPage.getLogInStatus().getText().equals(DemoLogInPage.success);
		reporting("User Logged In Successfully", "Username: "+ name + " Not Found. Unable to Log In User");
	}
	@After
	public void tearDown() throws InterruptedException 
	{
		report.endTest(test);
		report.flush();
		driver.close();
	}

}