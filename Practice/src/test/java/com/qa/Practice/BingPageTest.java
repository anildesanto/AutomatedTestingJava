package com.qa.Practice;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.*;

public class BingPageTest 
{
	private String path = "C:/chromedriver_win32/chromedriver.exe";
	private String driverKey = "webdriver.chrome.driver";
	private String url = "http://www.bing.com";
	private ChromeDriver driver;
	private static ExtentReports report;
	private ExtentTest test;
	private static String saveDirectory = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\Practice\\TestReports\\";
	
	@BeforeClass
	public static void initialise()
	{
		//save has a html file and override it everytime its run
		report = new ExtentReports(saveDirectory+"BingWebPageTest.html", true);
	}
	
	@Before
	public void setup()
	{
		System.setProperty(driverKey, path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void bingSearh() 
	{
		test = report.startTest("bingSearh pass test");
		String input = "Selenium";
		driver.get(url);
		test.log(LogStatus.INFO, "Bing Opened");
		PracticePage page = PageFactory.initElements(driver, PracticePage.class);
		page.searchFor(input);
		test.log(LogStatus.INFO, "Search Page loaded");
		WebElement checkElement = driver.findElement(By.xpath("//*[@id=\"sb_form_q\"]"));
		if(checkElement.getAttribute("value").equals(input))
		{
			test.log(LogStatus.PASS, "Search " + input + " was found");
		}
		else
		{
			test.log(LogStatus.FAIL, "Search " + input + " was found");
		}
		assertEquals(input, checkElement.getAttribute("value"));
		HelperMethods.screenshot(driver,saveDirectory);
	}
	@Test
	public void bingSearh2() 
	{
		test = report.startTest("bingSearh fail test");
		String input = "lool";
		driver.get(url);
		test.log(LogStatus.INFO, "Bing Opened");
		PracticePage page = PageFactory.initElements(driver, PracticePage.class);
		page.searchFor(input);
		test.log(LogStatus.INFO, "Search Page loaded");
		WebElement checkElement = driver.findElement(By.xpath("//*[@id=\"sb_form_q\"]"));
		String value = checkElement.getAttribute("value") + "TestFail";
		if(value.equals(input))
		{
			test.log(LogStatus.PASS, "Search " + input + " was found");
		}
		else
		{
			test.log(LogStatus.FAIL, "Search " + input + " was expected but "+ value + " was found instead");
		}
		assertEquals(input,value);
	}
	
	@After
	public void tearDown() throws InterruptedException
	{
		report.endTest(test);
		Thread.sleep(3000);
		driver.close();
	}
	
	@AfterClass
	public static void finish()
	{
		//save all tests report
		report.flush();
	}

}
