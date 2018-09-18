package com.qa.ShoppingWebsite.Shopping;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class ShoppingTest 
{
	private String path = "C:/chromedriver_win32/chromedriver.exe";
	private String driverKey = "webdriver.chrome.driver";
	private ChromeDriver driver;
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
	public void searchTest1()
	{
		String itemName = "dress";
		//================
		driver.get(ShoppingHomePage.url);
		ShoppingHomePage shoppingHomePage = PageFactory.initElements(driver, ShoppingHomePage.class);
		shoppingHomePage.search(itemName, driver, action);
		driver.get("http://automationpractice.com/index.php?controller=search&orderby=position&orderway=desc&search_query="+itemName+"&submit_search=");
		assertTrue(driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")).isDisplayed());
		//assertTrue("Product Found",demoSearchPage.foundResults());
	}
	@Test
	public void searchTest2()
	{
		String itemName = "Printed Summer Dress";
		//================
		driver.get(ShoppingHomePage.url);
		ShoppingHomePage shoppingHomePage = PageFactory.initElements(driver, ShoppingHomePage.class);
		shoppingHomePage.search(itemName, driver, action);
		DemoSearchPage demoSearchPage = PageFactory.initElements(driver, DemoSearchPage.class);
		assertTrue("Product Not Found",demoSearchPage.foundResults(itemName));
	}
	@After
	public void tearDown() throws InterruptedException
	{
		driver.quit();
	}
	

}
