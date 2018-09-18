package com.qa.Practice;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class BingPageTest 
{
	private String path = "C:/chromedriver_win32/chromedriver.exe";
	private String driverKey = "webdriver.chrome.driver";
	private String url = "http://www.bing.com";
	private ChromeDriver driver;
	
	@Before
	public void setup()
	{
		System.setProperty(driverKey, path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@Test
	public void bingSearhBox() 
	{
		String input = "Selenium";
		driver.get(url);
		PracticePage page = PageFactory.initElements(driver, PracticePage.class);
		page.searchFor(input);
		WebElement checkElement = driver.findElement(By.xpath("//*[@id=\"sb_form_q\"]"));
		assertEquals(input, checkElement.getAttribute("value"));
	}
	
	@After
	public void tearDown() throws InterruptedException
	{
		Thread.sleep(3000);
		driver.close();
	}

}
