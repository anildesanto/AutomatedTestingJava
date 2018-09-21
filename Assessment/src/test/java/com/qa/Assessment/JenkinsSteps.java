package com.qa.Assessment;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.*;

import Utilities.Constants;
import Utilities.ExcelUtils;
import Utilities.HelperMethods;
import cucumber.api.PendingException;
import cucumber.api.java.*;
import cucumber.api.java.en.*;

public class JenkinsSteps 
{
	public ChromeDriver driver;
	public ExtentTest test;
	public Boolean check = false;
	public ExtentReports report;
	public Actions action;
	JenkinsLogInPage jenkinsLogInPage;
	JenkinsHomePage jenkinsHomePage;
	JenkinsManagePage jenkinsManagePage;
	JenkinsManageUsersPage jenkinsManageUsersPage;
	JenkinsAddUserPage jenkinsAddUserPage;
	JenkinsProfilePage jenkinsProfilePage;
	int length;
	String userN;
	WebElement found;
	String testTitle;
	
	@Before
	public void setup()
	{
		System.setProperty(Constants.driverKey, Constants.path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		report = TestRunner.report;
		action = new Actions(driver);
		ExcelUtils.setExcelFile(Constants.saveDataDirectory + Constants.fileTestData, 0);
		jenkinsLogInPage = PageFactory.initElements(driver, JenkinsLogInPage.class);
		jenkinsHomePage = PageFactory.initElements(driver, JenkinsHomePage.class);
		jenkinsManagePage = PageFactory.initElements(driver, JenkinsManagePage.class);
		jenkinsManageUsersPage = PageFactory.initElements(driver, JenkinsManageUsersPage.class);
		jenkinsAddUserPage = PageFactory.initElements(driver, JenkinsAddUserPage.class);
		jenkinsProfilePage = PageFactory.initElements(driver, JenkinsProfilePage.class);
		ExcelUtils.setExcelFile(Constants.saveDataDirectory + Constants.fileTestData, 0);
	}
	public void reporting(Boolean check, String passMessage, String failMessage)
	{
		ExcelUtils.setCellData(testTitle+ " "+ (Constants.count++), Constants.count, 0);
			
		if(check)
		{
			test.log(LogStatus.PASS, passMessage);
			ExcelUtils.setCellData("Test Passed", Constants.count, 1);
			
		}
		else
		{
			test.log(LogStatus.FAIL, failMessage);
			ExcelUtils.setCellData("Test Failed", Constants.count, 1);
		}
		assertTrue(check);
	}
	public void enterDetails(String userN, String pass, String pass1, String name, String mail)
	{
		driver.get(Constants.jenkinsManageUsersPage);
		//(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(jenkinsManageUsersPage.getUsersTable())); 
		length  = jenkinsManageUsersPage.getNurmbersOfUser();
		//System.out.println("People List Length" + length);
		jenkinsManageUsersPage.setNurmbersOfUser(length);
		jenkinsManageUsersPage.getCreateUserBtn().click();
		test.log(LogStatus.INFO, "Loading Form...");
		driver.get(Constants.jenkinsAddUserPage);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(jenkinsAddUserPage.getCreateUserForm())); 
		test.log(LogStatus.INFO, "Form Loaded");
		jenkinsAddUserPage.enterDetails(test,userN, pass, pass, name, mail);
		check = jenkinsAddUserPage.checkDetailsEntered();
		reporting(check, "Details Entered", "Failed to Enter Details" );
	    // Write code here that turns the phrase above into concrete actions

	}
	public void signIn(String title)
	{
		testTitle = title;
		test = report.startTest(testTitle);
		
		test.log(LogStatus.INFO, "Loading HomePage...");
		driver.get(Constants.jenkinsLogInPage);
		jenkinsLogInPage.LogIn();
		test.log(LogStatus.INFO, "HomePage Loaded");
	}
	public void visible()
	{
		test.log(LogStatus.INFO, "Looking for user: "+ userN);
		driver.get(Constants.jenkinsManageUsersPage);
		test.log(LogStatus.INFO, "Loading Manage users Page...");
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(jenkinsManageUsersPage.getUsersTable())); 
		test.log(LogStatus.INFO, "Manage Users Page Loaded");
		if(jenkinsManageUsersPage.findUserOnScreen(driver,userN) != null)
		{
			found = jenkinsManageUsersPage.findUserOnScreen(driver,userN);
			check = true;
		}
		else
		{
			check = false;
		}
		reporting(check, "User Displayed In Page", "User not located" );
	}
	@Given("^that you are on the create UserScreen$")
	public void that_you_are_on_the_create_UserScreen() throws InterruptedException
	{
		signIn( "Add A user to Database");
		test.log(LogStatus.INFO, "Loading Manage Jenkins Page...");
		jenkinsHomePage.getManageJenkinsBtn().click();
		test.log(LogStatus.INFO, "Manage Jenkins Page Loaded");
		HelperMethods.scroll(action, true, jenkinsManagePage.getManageJenkinsHeader(), 15);
		jenkinsManagePage.getManageUsersBtn().click();
		check = jenkinsManageUsersPage.getManageUsersHeader().isDisplayed();
		reporting(check, "Manage Users Page Loaded", "Failed to Load Manage Users Page" );
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("^the User details are entered on the Create UserScreen$")
	public void the_User_details_are_entered_on_the_Create_UserScreen()
	{
		//========= Form Entries =================
		userN = "lot3288822l";
		String pass = "lol1234";
		String name = "Lol Jokes";
		String mail = "lol23888tt@hotmail.com";
		//===========================
		enterDetails(userN, pass, pass, name, mail);
	}

	@When("^the details are submitted on the Create UserScreen$")
	public void the_details_are_submitted_on_the_Create_UserScreen()
	{
		if(jenkinsAddUserPage.checkDetailsEntered())
		{
			test.log(LogStatus.INFO, "Creating User...");
			jenkinsAddUserPage.create();
			(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(jenkinsManageUsersPage.getUsersTable())); 
			check = jenkinsManageUsersPage.userCreated();
		}
		else
		{
			test.log(LogStatus.WARNING, "Details were not entered");
			check = false;
		}
	    // Write code here that turns the phrase above into concrete actions
		reporting(check, "User Created Successfully", "Failed to Create user" );
	}

	@Then("^the Username should be visible on the UsersScreen$")
	public void the_Username_should_be_visible_on_the_UsersScreen()
	{
		visible();
	}

	
	

	@When("^the User details \"([^\"]*)\" username, \"([^\"]*)\" password, \"([^\"]*)\" confirm Password, \"([^\"]*)\" Fullname and \"([^\"]*)\" EmailAddress are entered on the Create UserScreen$")
	public void the_User_details_username_password_confirm_Password_Fullname_and_EmailAddress_are_entered_on_the_Create_UserScreen(String userN, String pass, String pass1, String name, String mail)
	{
		this.userN = userN;
		enterDetails(userN, pass, pass1, name, mail);
	}

	@Then("^the \"([^\"]*)\" username should be visible on the UsersScreen$")
	public void the_username_should_be_visible_on_the_UsersScreen(String arg1)
	{
		this.userN = arg1;
		visible();
	}

	@Given("^the \"([^\"]*)\" username is visible on the UsersScreen$")
	public void the_username_is_visible_on_the_UsersScreen(String arg1)
	{
		this.userN = arg1;
		signIn( "View the details of a User on a database");
		visible();
	}

	@When("^the \"([^\"]*)\" username is clicked on the UserScreen$")
	public void the_username_is_clicked_on_the_UserScreen(String arg1)
	{
		userNameClicked();
	}
	public void userNameClicked()
	{
		if(jenkinsManageUsersPage.findUserOnScreen(driver,userN) != null)
		{
			found.click();
			check = true;
		}
		else
		{
			check = false;
		}
		reporting(check, "Username Clicked", "Username not located" );
	}
	@Then("^the User Profile should display the \"([^\"]*)\" username on the ProfileScreen$")
	public void the_User_Profile_should_display_the_username_on_the_ProfileScreen(String arg1)
	{
		check = driver.findElement(By.id("description")).isDisplayed();
		reporting(check, "Username Displayed on Profile Screen", "Username not located" );
	}

	@Given("^the \"([^\"]*)\" Username's profile page has been loaded$")
	public void the_Username_s_profile_page_has_been_loaded(String arg1) 
	{
		userN = arg1;
		signIn( "View the details of a User on a database");
		visible();
		userNameClicked();
		check = driver.findElement(By.id("description")).isDisplayed();
		reporting(check, "Username Displayed on Profile Screen", "Username not located" );
		
	}

	@Given("^the configure button has been clicked on the profile page$")
	public void the_configure_button_has_been_clicked_on_the_profile_page() throws Throwable 
	{
		testTitle = "Updating the email address of a User";
		test = report.startTest(testTitle);
		jenkinsProfilePage.getConfigureBtn().click();
		check = driver.findElement(By.xpath("//*[@id=\"main-panel\"]/form/table/tbody/tr[17]/td[2]")).isDisplayed();
		reporting(check, "Configure Button was clicked", "Cannot find Configure button" );
	}

	@When("^I change the old email address on the Configure Page to a new email address \"([^\"]*)\"$")
	public void i_change_the_old_email_address_on_the_Configure_Page_to_a_new_email_address(String arg1) 
	{
		test.log(LogStatus.INFO, "Entering Email...");
		check = jenkinsProfilePage.changeEmail(arg1);
		reporting(check, "Email Entered", "Could not enter email" );
	}

	@When("^I save the changes to the Configure Page$")
	public void i_save_the_changes_to_the_Configure_Page()
	{
		jenkinsProfilePage.getSaveBtn().submit();
	}

	@Then("^the Configure Page should show the new email address \"([^\"]*)\"$")
	public void the_Configure_Page_should_show_the_new_email_address(String arg1)
	{
		visible();
		userNameClicked();
		jenkinsProfilePage.getConfigureBtn().click();
		check = jenkinsProfilePage.checkEmailContent(arg1);
		reporting(check, "Email Was Successfuly altered", "Could not change email" );
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