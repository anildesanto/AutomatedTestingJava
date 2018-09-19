package com.qa.TheDemoSite.Demo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.*;

public class TestLogIn extends DemoTestManager
{
	@BeforeClass
	public static void initialise()
	{
		report = new ExtentReports(saveDirectory+"DemoLogInTestReport.html", true);
	}
	@Before
	public void setup()
	{
		System.setProperty(driverKey, path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		name = "Anilde";
		pass = "lolol";
	}
	@Test
	public void logIn()
	{
		test =  report.startTest("Log In user");
		//============== Log In ==========
		driver.get(DemoLogInPage.url);
		test.log(LogStatus.INFO, "Log In Page Loaded");
		DemoLogInPage demoLogInPage = PageFactory.initElements(driver, DemoLogInPage.class);
		demoLogInPage.logInUser(name, pass);
		test.log(LogStatus.INFO, "User Details entered");
		test.log(LogStatus.INFO, "Logging in...");
		check = demoLogInPage.getLogInStatus().getText().equals(DemoLogInPage.success);
		reporting("User Logged In Successfully", "Unable to Log In User");
	}
	@After
	public void tearDown()
	{
		report.endTest(test);
		driver.quit();
	}
}
