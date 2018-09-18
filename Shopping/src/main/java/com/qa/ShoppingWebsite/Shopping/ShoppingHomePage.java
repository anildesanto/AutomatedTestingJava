package com.qa.ShoppingWebsite.Shopping;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;

public class ShoppingHomePage 
{
	@FindBy(id = "search_query_top")
	private WebElement searchBar;
	
	public static String url = "http://automationpractice.com/index.php";
	
	public void search(String text, WebDriver driver, Actions action)
	{
		searchBar.sendKeys(text);
		action.sendKeys(Keys.ENTER);
	}
}
