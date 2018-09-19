package com.qa.ShoppingWebsite.Shopping;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.*;

public class ShoppingTest 
{
	private String path = "C:/chromedriver_win32/chromedriver.exe";
	private String driverKey = "webdriver.chrome.driver";
	private ChromeDriver driver;
	private Actions action;
	private static ExtentReports report;
	private ExtentTest test;
	private static String saveDirectory = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\Shopping\\TestReports\\";
	private String itemName;
	private Boolean check;
	
	@BeforeClass
	public static void initialise()
	{
		report = new ExtentReports(saveDirectory+"ShoppingTestReport.html", true);
	}
	
	@Before
	public void setup()
	{
		System.setProperty(driverKey, path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		action = new Actions(driver);
	}
	
	@Test
	public void searchTestFail()
	{
		test = report.startTest("Find Specific clothing name on page FAIL");
		itemName = "Printed Summer Dres44s";
		//================
		driver.get(ShoppingHomePage.url);
		test.log(LogStatus.INFO, "Shopping Home Page loaded");
		ShoppingHomePage shoppingHomePage = PageFactory.initElements(driver, ShoppingHomePage.class);
		shoppingHomePage.search(itemName, driver, action);
		test.log(LogStatus.INFO, "Shopping Search Page loaded");
		test.log(LogStatus.INFO, "Searching...");
		DemoSearchPage demoSearchPage = PageFactory.initElements(driver, DemoSearchPage.class);
		check = demoSearchPage.foundResults(itemName,test);
		//========== reporting =========
		reporting();
		
	}
	@Test
	public void searchTestPass()
	{
		test = report.startTest("Find Specific clothing name on page PASS");
		itemName = "Printed Summer Dress";
		//================
		driver.get(ShoppingHomePage.url);
		test.log(LogStatus.INFO, "Shopping Home Page loaded");
		ShoppingHomePage shoppingHomePage = PageFactory.initElements(driver, ShoppingHomePage.class);
		shoppingHomePage.search(itemName, driver, action);
		test.log(LogStatus.INFO, "Shopping Search Page loaded");
		test.log(LogStatus.INFO, "Searching...");
		DemoSearchPage demoSearchPage = PageFactory.initElements(driver, DemoSearchPage.class);
		check = demoSearchPage.foundResults(itemName,test);
		//========== reporting =========
		reporting();
		
	}
	
	public void reporting()
	{
		if(check)
		{
			test.log(LogStatus.PASS, "Found Item: "+ itemName);
			
		}
		else
		{
			test.log(LogStatus.FAIL, "Could Not Find Item: " +itemName);
		}
		assertTrue("Product Not Found",check);
		HelperMethods.screenshot(driver,saveDirectory);
	}
	@After
	public void tearDown()
	{
		report.endTest(test);;
		driver.close();
	}
	@AfterClass
	public static void finish()
	{
		report.flush();
	}
	

}
