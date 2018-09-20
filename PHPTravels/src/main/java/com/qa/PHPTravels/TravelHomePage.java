package com.qa.PHPTravels;

import java.text.SimpleDateFormat;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TravelHomePage 
{
	@FindBy(xpath = "//*[@id=\"s2id_autogen8\"]/a")
	private WebElement locationBox;
	
	@FindBy(xpath = "//*[@id=\"dpd1\"]/div/input")
	private WebElement startDateBox;
	
	@FindBy(xpath = "//*[@id=\"dpd2\"]/div/input")
	private WebElement endDateBox;
	
	@FindBy(xpath = "//*[@id=\"travellersInput\"]")
	private WebElement peopleBox;
	
	@FindBy(xpath = "//*[@id=\"HOTELS\"]/form/div[5]/button")
	private WebElement searchButton;
	
	//========================
	@FindBy(xpath = "//*[@id=\"select2-drop\"]/ul/li/ul/li[1]/div/span")
	private WebElement london;
	
	@FindBy(xpath = "//*[@id=\"body-section\"]/div[5]/div/div[3]/div[1]/div/table/tbody/tr/td/div[3]/a")
	private WebElement selectedHotelDetails;
	
	@FindBy(xpath = "//*[@id=\"body-section\"]/div[2]/div/div/div[2]/div/span[1]/strong/span")
	private WebElement page;
	
	@FindBy(xpath = "//*[@id=\"ROOMS\"]/div/button")
	private WebElement bookNowButton;
	
	//
	
	public static String url = "https://www.phptravels.net/";
	
	public void inputSearch(WebDriver driver, Actions action,String location, String  start, String  end, String people) throws InterruptedException
	{
		action.moveToElement(locationBox).click().sendKeys(location).perform();
		(new WebDriverWait(driver, 10)) .until(ExpectedConditions.visibilityOf(london)); 
		action.moveToElement(london).click();
		
		action.moveToElement(startDateBox).click().sendKeys(start).sendKeys(Keys.ENTER).perform();
		
		
		action.moveToElement(endDateBox).click().sendKeys(end).perform();
		
		peopleBox.clear();
		action.moveToElement(peopleBox).click().sendKeys(people).perform();
		
		searchButton.submit();
		
		(new WebDriverWait(driver, 10)) .until(ExpectedConditions.visibilityOf(selectedHotelDetails)); 
		selectedHotelDetails.click();
		
		scroll(action,true,page,30);
		action.moveToElement(bookNowButton).click();
	}

	public void scroll(Actions action,Boolean down, WebElement e, int times) throws InterruptedException
	{
		for (int i = 0; i <= times; i++) 
		{
			if(down)
				action.sendKeys(Keys.ARROW_DOWN).perform();
			else
				action.moveToElement(e).sendKeys(Keys.UP).perform();
			
			Thread.sleep(50);
			
		}
	}
	
}
