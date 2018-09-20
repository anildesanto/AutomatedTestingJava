package com.qa.Demo2;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;

public class DemoHomePage 
{
	@FindBy(xpath = "/html/body/div/center/table/tbody/tr[2]/td/div/center/table/tbody/tr/td[2]/p/small/a[3]")
	private WebElement addUserButton;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/div[2]/center/table/tbody/tr[1]/td/div")
	private WebElement indexBox;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/div[2]/center/table/tbody/tr[2]/td/div[2]")
	private WebElement headerBox;
	
	@FindBy(xpath = "/html/body/table/tbody/tr/td[1]/div[1]")
	private WebElement page;
	
	public WebElement getPage() 
	{
		return page;
	}

	public static String url = "http://thedemosite.co.uk/";
	
	public WebElement getAddButton()
	{
		return addUserButton;
	}

	public WebElement getIndexBox() 
	{
		return indexBox;
	}

	public WebElement getHeaderBox() 
	{
		return headerBox;
	}

	public void scroll(Actions action,Boolean down, WebElement e, int times) throws InterruptedException
	{
		for (int i = 0; i < times; i++) 
		{
			if(down)
				action.moveToElement(e).click().sendKeys(Keys.ARROW_DOWN).perform();
			else
				action.moveToElement(e).click().sendKeys(Keys.UP).perform();
			
			Thread.sleep(500);
			
		}
	}
}
