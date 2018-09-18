package com.qa.ShoppingWebsite.Shopping;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class DemoSearchPage 
{
	@FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")
	private WebElement chosenItem;
	
	@FindBy(xpath = "//*[@id=\"center_column\"]")
	private WebElement grid;
	
	public Boolean foundResults(String name)
	{
		List<WebElement> listOfProducts = grid.findElements(By.className("product-name"));
		for (WebElement e : listOfProducts) 
		{
			System.out.println("Found: " + e.getText());
			if(e.getText().toLowerCase().equals(name.toLowerCase()))
				return true;
		}
		return false;	
	}
	public WebElement getChosenItem()
	{
		return chosenItem;
	}
}
