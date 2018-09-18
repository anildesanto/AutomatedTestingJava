package com.qa.TheDemoSite.Demo;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class DemoAddUserPage 
{
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[1]/td[2]/p/input")
	private WebElement userName; 

	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[2]/td[2]/p/input")
	private WebElement passWord; 
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/form/div/center/table/tbody/tr/td[1]/div/center/table/tbody/tr[3]/td[2]/p/input")
	private WebElement saveButton;
	
	
	public static String url = "http://thedemosite.co.uk/";
	
	public WebElement getUserName()
	{
		return userName;
	}
	public void registerUser(String name, String password)
	{
		userName.sendKeys(name);
		passWord.sendKeys(password);
		saveButton.submit();
	}
	
}
