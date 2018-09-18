package com.qa.TheDemoSite.Demo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class TestLogIn 
{
	private String path = "C:/chromedriver_win32/chromedriver.exe";
	private String driverKey = "webdriver.chrome.driver";
	private ChromeDriver driver;
	String name;
	String pass;
	String success;
	
	@Before
	public void initialise()
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
		//============== Log In ==========
		driver.get(DemoLogInPage.url);
		DemoLogInPage demoLogInPage = PageFactory.initElements(driver, DemoLogInPage.class);
		demoLogInPage.logInUser(name, pass);
		assertEquals("LogIn Successfull",DemoLogInPage.success, demoLogInPage.getLogInStatus().getText());
	}
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
