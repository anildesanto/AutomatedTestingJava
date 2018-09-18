package com.qa.WebDriver.WbDriver;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverTest 
{
	private String path = "C:/chromedriver_win32/chromedriver.exe";
	private String driverKey = "webdriver.chrome.driver";
	private String url = "https://www.google.co.uk/search?q=cats&rlz=1C1CHBF_enGB814GB814&oq=cats&aqs=chrome..69i57j0l5.2334j0j9&sourceid=chrome&ie=UTF-8";
	ChromeDriver driver;
	
	@Before
	public void initialise()
	{
		System.setProperty(driverKey, path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Test
	public void test1()
	{
		driver.get(url);
		WebElement img = driver.findElement(By.xpath("//*[@id=\"hdtb-msb-vis\"]/div[2]/a"));
		img.click();
		WebElement selectedCat = driver.findElement(By.xpath("//*[@id=\"rg_s\"]/div[30]/a/img"));
		//selectedCat.click();
		assertTrue(selectedCat.isEnabled());
		
	}
	@After
	public void tearDown()
	{
		driver.quit();
	}
	
	
	
}
