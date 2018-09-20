package com.qa.PHPTravels;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.PageFactory;

public class TestBooking 
{
	private String path = "C:/chromedriver_win32/chromedriver.exe";
	private String driverKey = "webdriver.chrome.driver";
	private WebDriver driver;
	private Actions action;
	
	@Before
	public void initialise()
	{
		System.setProperty(driverKey, path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		action = new Actions(driver);
	}
	
	@Test
	public void test() throws InterruptedException 
	{
		driver.get(TravelHomePage.url);
		TravelHomePage travelHomePage = PageFactory.initElements(driver, TravelHomePage.class);
		travelHomePage.inputSearch(driver, action,"London", "27/09/2018", "29/09/2018", "2 Adult 1 Child");
	}
	
	@After
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(4000);
		driver.quit();
	}

}
