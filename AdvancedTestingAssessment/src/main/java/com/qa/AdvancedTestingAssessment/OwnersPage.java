package com.qa.AdvancedTestingAssessment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OwnersPage 
{
	@FindBy(xpath = "/html/body/app-root/app-owner-list/div/div/h2")
	private WebElement ownersListHeader;

	@FindBy(xpath = "/html/body/app-root/app-owner-list/div/div/div")
	private WebElement ownersTable;
	
	public WebElement getOwnersListHeader() 
	{
		return ownersListHeader;
	}
	
	public Boolean findOwnerByName(String name)
	{
		List<WebElement> ownersList = ownersTable.findElements(By.className("ownerFullName"));

		for (int i = 1; i <= ownersList.size(); i++) 
		{
			WebElement foundElement = ownersList.get(i).findElement(By.partialLinkText("/petclinic/owners/"+i));
			System.out.println("Value is:"+foundElement.getAttribute("textContent"));
			if(foundElement.getAttribute("textContent").equals(name))
			{
				return true;
			}
		}
		return false;
	}
}
