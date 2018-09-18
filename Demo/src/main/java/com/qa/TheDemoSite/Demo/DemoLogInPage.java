package com.qa.TheDemoSite.Demo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class DemoLogInPage 
{
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/p/input")
	private WebElement userName; 

	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/p/input")
	private WebElement passWord; 
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/p/input")
	private WebElement logInButton;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/big/blockquote/blockquote/font/center/b")
	private WebElement logInStatus;
	
	public static String success = "**Successful Login**";
	public static String url = "http://thedemosite.co.uk/login.php";
	
	public WebElement getLogInStatus()
	{
		return logInStatus;
	}
	public WebElement getLogInButton()
	{
		return logInButton;
	}
	
	public void logInUser(String name, String password)
	{
		userName.sendKeys(name);
		passWord.sendKeys(password);
		logInButton.submit();
	}
}
