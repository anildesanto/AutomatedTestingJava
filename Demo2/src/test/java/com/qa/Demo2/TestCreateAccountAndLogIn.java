package com.qa.Demo2;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.Constants;
import Utilities.HelperMethods;
import Utilities.ReadFromExcel;

@RunWith(Parameterized.class)
public class TestCreateAccountAndLogIn
{
	private String name;
	private String pass;
	private String name1;
	private String pass1;
	private static Object[][]  objs;
	public static  ChromeDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;
	public static Boolean check;
	
	//==========================
	public String getName() 
	{
		return name;
	}

	public String getPass() 
	{
		return pass;
	}

	public String getName1() 
	{
		return name1;
	}

	public String getPass1() 
	{
		return pass1;
	}

	public static Object[][] getObjs() 
	{
		return objs;
	}

	public static void setObjs(Object[][] objs) 
	{
		TestCreateAccountAndLogIn.objs = objs;
	}
	@Parameters
	public static Collection <Object[]> data()
	{
//		getDataFromFile("DemoData.xlsx");
//		getDataFromFile("DemoLogInData.xlsx");
		objs = ReadFromExcel.getDataFromFile(Constants.saveDataDirectory, "Demo2Data.xlsx");
		return Arrays.asList(objs
		);
	}
	
	@BeforeClass
	public static void initialise()
	{
	
		report = new ExtentReports(Constants.saveDirectory+"Demo2RegisterAnLogInReport.html", true);
	}
	@Before
	public void setup()
	{
		System.setProperty(Constants.driverKey, Constants.path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
	}
	public TestCreateAccountAndLogIn(String n, String p,String n1, String p1)
	{
		name = n;
		pass = p;
		name1 = n1;
		pass1 = p1;
	}
	@Test
	public void createUserAndLogIn() throws InterruptedException
	{
		try 
		{
			testingCreateUser();
		} catch (UnhandledAlertException e) 
		{
			test.log(LogStatus.WARNING, "Input Needs to be 4 Characters long");
			test.log(LogStatus.FAIL, "Failed to create User");
			assertTrue(false);
		}
		testingUserLogIn();
	}
	public void testingCreateUser() throws InterruptedException
	{
		test =  report.startTest("Create user");
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
	public void testingUserLogIn() throws InterruptedException
	{
		test =  report.startTest("Log In user");
		//============== Log In ==========
		driver.get(DemoLogInPage.url);
		test.log(LogStatus.INFO, "Log In Page Loaded");
		DemoLogInPage demoLogInPage = PageFactory.initElements(driver, DemoLogInPage.class);
		demoLogInPage.logInUser(name1, pass1);
		test.log(LogStatus.INFO, "User Details entered");
		test.log(LogStatus.INFO, "Username: "+name1+ " Password: "+ pass1);
		test.log(LogStatus.INFO,"Logging In...");
		check = demoLogInPage.getLogInStatus().getText().equals(DemoLogInPage.success);
		reporting("User Logged In Successfully", "Username: "+ name1 + " Not Found. Unable to Log In User");
		report.endTest(test);
	}
	protected void reporting(String passMessage, String failMessage)
	{
		if(check)
		{
			test.log(LogStatus.PASS,passMessage);	
		}
		else
		{
			test.log(LogStatus.FAIL, failMessage);
		}
		assertTrue(failMessage,check);
		//screenshot
		HelperMethods.screenshot(driver,Constants.saveDirectory);
	}
	
	@After
	public void tearDown()
	{
		report.endTest(test);
		driver.close();
	}
	@AfterClass
	public static void finish()
	{
		report.flush();
	}
}
