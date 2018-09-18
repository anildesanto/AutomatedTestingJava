package com.qa.TheDemoSite.Demo;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class TestCreateUser 
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
	public void createUser()
	{
		driver.get(DemoHomePage.url);
		//=========================== Create Account ==============
		DemoHomePage demoHomePage = PageFactory.initElements(driver, DemoHomePage.class);
		demoHomePage.getAddButton().click();
		DemoAddUserPage demoAddUserPage = PageFactory.initElements(driver, DemoAddUserPage.class);
		demoAddUserPage.registerUser(name, pass);
		assertEquals("", demoAddUserPage.getUserName().getAttribute("value"));
	}
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
