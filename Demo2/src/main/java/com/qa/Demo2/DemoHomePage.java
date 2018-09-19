package com.qa.Demo2;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class DemoHomePage 
{
	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")
	private WebElement addUserButton;
	
	public static String url = "http://thedemosite.co.uk/";
	
	public WebElement getAddButton()
	{
		return addUserButton;
	}
}
