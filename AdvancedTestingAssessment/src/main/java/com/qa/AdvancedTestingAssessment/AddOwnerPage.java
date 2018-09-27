package com.qa.AdvancedTestingAssessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddOwnerPage 
{
	@FindBy(id = "firstName")
	private WebElement firstName;
	
	@FindBy(id = "lastName")
	private WebElement lastName;
	
	@FindBy(id = "address")
	private WebElement address;
	
	@FindBy(id = "city")
	private WebElement city;
	
	@FindBy(id = "telephone")
	private WebElement telephone;
	
	@FindBy(xpath = "/html/body/app-root/app-owner-add/div/div/form/div[7]/div/button[2]")
	private WebElement addButton;
	
	private OwnersPage ownersPage;
	
	public void addOwner(String firstName, String lastName, String address, String  city, String telephone)
	{
		this.firstName.sendKeys(firstName);
		this.lastName.sendKeys(lastName);
		this.address.sendKeys(address);
		this.city.sendKeys(city);
		this.telephone.sendKeys(telephone);
		addButton.submit();
	}
}
