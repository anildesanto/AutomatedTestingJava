package com.qa.PHPTravels;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class TravelHomePage 
{
	@FindBy(xpath = "//*[@id=\"select2-drop\"]/div/input")
	private WebElement locationBox;
	
	@FindBy(xpath = "//*[@id=\"dpd1\"]/div/input")
	private WebElement startDateBox;
	
	@FindBy(xpath = "//*[@id=\"dpd2\"]/div/input")
	private WebElement endDateBox;
	
	@FindBy(xpath = "//*[@id=\"travellersInput\"]")
	private WebElement peopleBox;
	
	@FindBy(xpath = "//*[@id=\"HOTELS\"]/form/div[5]/button")
	private WebElement searchButton;
	
	public static String url = "https://www.phptravels.net/";
	
	public void inputSearch(String location, String start, String end, String people)
	{
		locationBox.sendKeys(location);
		locationBox.sendKeys(Keys.ENTER);
		
		startDateBox.sendKeys(start);
		startDateBox.sendKeys(Keys.ENTER);

		endDateBox.sendKeys(end);
		endDateBox.sendKeys(Keys.ENTER);
		
		peopleBox.sendKeys(people);
		peopleBox.sendKeys(Keys.ENTER);
		searchButton.click();
	}
	
}
