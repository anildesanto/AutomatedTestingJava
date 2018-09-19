package com.qa.TheDemoSite.Demo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

@RunWith(Parameterized.class)
public class TestCreateAccountAndLogIn extends DemoTestManager
{
	private String name1;
	private String pass1;
	private static Object[][]  objs = new Object[3][4];
	@Parameters
	public static Collection <Object[]> data()
	{
//		getDataFromFile("DemoData.xlsx");
//		getDataFromFile("DemoLogInData.xlsx");
		TestCreateAccountAndLogIn obj = new TestCreateAccountAndLogIn("ffff", "ffff", "ffff", "ffff");
		Object[] objlist = {obj.name,obj.pass, obj.name1, obj.pass1};
		objs[0] = objlist;
		objs[1] = objlist;
		objs[2] = objlist;
		
		return Arrays.asList(objs
		);
	}
	
	@BeforeClass
	public static void initialise()
	{
		getDataFromFile("DemoData.xlsx");
		report = new ExtentReports(saveDirectory+"DemoRegisterAnLogInReport.html", true);
	}
	@Before
	public void setup()
	{
		System.setProperty(driverKey, path);
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
		System.out.println(name);
		testingCreateUser();
		testingUserLogIn();
	}
	public void testingCreateUser()
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
		test.log(LogStatus.INFO, "Username: "+name+ " Password: "+ pass + " Creating User...");
//		check = demoAddUserPage.getUserName().getAttribute("value").equals("");
//		reporting("User created Successfully", "Unable to create User");
		
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
		test.log(LogStatus.INFO, "Username: "+name1+ " Password:"+ pass1 + "Logging In...");
		check = demoLogInPage.getLogInStatus().getText().equals(DemoLogInPage.success);
		reporting("User Logged In Successfully", "Username: "+ name1 + " Not Found. Unable to Log In User");

		Thread.sleep(1000);
	}
	@After
	public void tearDown()
	{
		report.endTest(test);
		driver.close();
	}
}
