package com.qa.TheDemoSite.Demo;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.HashMap;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.*;
import org.junit.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.*;

import net.bytebuddy.description.modifier.SynchronizationState;

public class TestCreateUser extends DemoTestManager
{
	@BeforeClass
	public static void initialise()
	{
		report = new ExtentReports(saveDirectory+"DemoCreateUserTestReport.html", true);
	}
	@Before
	public void setup()
	{
		System.setProperty(driverKey, path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
//		name = "Anilde";
//		pass = "lolol";
		
	}
	@Test
	public void createUser() throws InterruptedException
	{
		getDataFromFile("DemoData.xlsx");
		Set<String> userNames = users.keySet();
		for (String names : userNames) 
		{
			System.out.println(names);
			name = names;
			pass = users.get(names);
			testing1();
			Thread.sleep(1000);
		}
	}
	
	public void testing1()
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
		test.log(LogStatus.INFO, "Creating User...");
		check = demoAddUserPage.getUserName().getAttribute("value").equals("");
		reporting("User created Successfully", "Unable to create User");
		
	}
	
	@After
	public void tearDown()
	{
		report.endTest(test);
		driver.close();
	}
}
