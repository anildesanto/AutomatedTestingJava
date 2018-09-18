package com.qa.PHPTravels;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

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
	
	@FindBy(xpath = "//*[@id=\"body-section\"]/div[2]/div/form/div[5]/button")
	private WebElement searchButton;
	
	public static String url = "https://www.phptravels.net/hotels";
	
	public void inputSearch(String location, String start, String end, String people)
	{
		//locationBox.sendKeys(location);
		startDateBox.sendKeys(start);
		endDateBox.sendKeys(end);
		peopleBox.sendKeys(people);
		searchButton.submit();
	}
	
}
