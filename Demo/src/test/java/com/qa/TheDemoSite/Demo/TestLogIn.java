package com.qa.TheDemoSite.Demo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLogIn 
{
	private String path = "C:/chromedriver_win32/chromedriver.exe";
	private String driverKey = "webdriver.chrome.driver";
	private String url = "http://thedemosite.co.uk/";
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
		String name = "Anilde";
		String pass = "lolol";
		String success = "**Successful Login**";
		driver.get(url);
		//=========================== Create Account ==============
		WebElement addUser = driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]"));
		addUser.click();
		WebElement userName = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input"));
		userName.sendKeys(name);
		WebElement userPass = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input"));
		userPass.sendKeys(pass);
		WebElement saveButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input"));
		saveButton.click();
		//assertEquals("",userName.getCssValue("value").trim());
		WebElement loginPage = driver.findElement(By.xpath("/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[4]"));
		loginPage.click();
		//============================= Log In ================
		WebElement userNameLogIn = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input"));
		userNameLogIn.sendKeys(name);
		WebElement userPassLogIn = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input"));
		userPassLogIn.sendKeys(pass);
		WebElement loginButton = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input"));
		loginButton.click();
		//=== check
		WebElement loginMessage = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b"));
		assertEquals("LogIn Successfull",success.toLowerCase().trim(), loginMessage.getText().toLowerCase().trim());
	}
	@After
	public void tearDown()
	{
		driver.quit();
	}
}
