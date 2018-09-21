package com.qa.Assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JenkinsHomePage 
{
	@FindBy(xpath = "//*[@id=\"tasks\"]/div[4]/a[2]")
	private WebElement manageJenkinsBtn;

	public WebElement getManageJenkinsBtn() 
	{
		return manageJenkinsBtn;
	}
	
	
}
