package com.qa.Assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JenkinsProfilePage 
{
	@FindBy(xpath = "//*[@id=\"tasks\"]/div[4]/a[2]")
	private WebElement configureBtn;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/table/tbody/tr[17]/td[3]/input")
	private WebElement emailInput;
	
	@FindBy(id = "yui-gen5-button")
	private WebElement saveBtn;
	
	
	public Boolean changeEmail(String email)
	{
		emailInput.clear();
		emailInput.sendKeys(email);
		if(emailInput.getAttribute("value").equals(email))
			return true;
		else
			return false;
	}

	public Boolean checkEmailContent(String email)
	{
		if(emailInput.getAttribute("value").equals(email))
			return true;
		else
			return false;
	}
	public WebElement getConfigureBtn() 
	{
		return configureBtn;
	}


	public WebElement getSaveBtn() 
	{
		return saveBtn;
	}
	
}
