package com.qa.Assessment;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utilities.Constants;

public class JenkinsLogInPage 
{
	@FindBy(xpath = "//*[@id=\"j_username\"]")
	private WebElement nameInput;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/div/form/table/tbody/tr[2]/td[2]/input")
	private WebElement passInput;
	
	@FindBy(id = "yui-gen1-button")
	private WebElement logInBtn;
	
	
	
	public void LogIn()
	{
		nameInput.sendKeys(Constants.name);
		passInput.sendKeys(Constants.pass);
		logInBtn.submit();
	}
}

