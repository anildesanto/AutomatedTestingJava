package com.qa.Assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JenkinsManagePage 
{
	@FindBy(xpath = "//*[@id=\"main-panel\"]/h1")
	private WebElement manageJenkinsHeader;
	
	@FindBy(xpath = "//*[@id=\"management-links\"]/tbody/tr[16]/td[2]/div[1]/a")
	private WebElement manageUsersBtn;
	
	public WebElement getManageUsersBtn() 
	{
		return manageUsersBtn;
	}

	public WebElement getManageJenkinsHeader() 
	{
		return manageJenkinsHeader;
	}

}
