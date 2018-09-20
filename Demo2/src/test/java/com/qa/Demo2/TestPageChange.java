package com.qa.Demo2;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.qa.Demo2.DemoHomePage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import Utilities.Constants;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class TestPageChange 
{

	public static ChromeDriver driver;
	public Boolean check = false;
	private static Actions action;

	@BeforeClass
	public static void setup()
	{
		System.setProperty(Constants.driverKey, Constants.path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		action = new Actions(driver);
	}
	@Test
	public void test() throws InterruptedException 
	{
		driver.get(DemoHomePage.url);
		DemoHomePage demoHomePage = PageFactory.initElements(driver, DemoHomePage.class);

		
		demoHomePage.scroll(action,true, demoHomePage.getIndexBox(), 5);
		demoHomePage.scroll(action,true, demoHomePage.getHeaderBox(), 5);
		demoHomePage.scroll(action,false, demoHomePage.getPage(), 5);
	}
	
	@AfterClass
	public static void tearDown() throws InterruptedException 
	{
		driver.close();
	}
}
