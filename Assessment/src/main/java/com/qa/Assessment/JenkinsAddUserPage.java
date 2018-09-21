package com.qa.Assessment;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Utilities.Constants;
import Utilities.ExcelUtils;
public class JenkinsAddUserPage 
{
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]")
	private WebElement createUserForm;
	
	//========== Form Entries ==========
	@FindBy(id = "username")
	private WebElement username;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[2]/td[2]/input")
	private WebElement password;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[3]/td[2]/input")
	private WebElement passConfirm;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[4]/td[2]/input")
	private WebElement fullName;
	
	@FindBy(xpath = "//*[@id=\"main-panel\"]/form/div[1]/table/tbody/tr[5]/td[2]/input")
	private WebElement email;
	
	@FindBy(id = "yui-gen3-button")
	private WebElement createBtn;
	
	//================ Methods ==========
	public void enterDetails(ExtentTest test, String userN, String pass, String pass2, String name, String mail)
	{
		ExcelUtils.setCellData("Test Passed", Constants.count, 2);
		String logg ="Username: " + userN +" Password: " +pass+" Password Comfirmation: "+ pass2+ " Name: "+name+" Email: "+mail;
		test.log(LogStatus.INFO,  "Entering details...");
		test.log(LogStatus.INFO, logg);
		username.sendKeys(userN);
		password.sendKeys(pass);
		passConfirm.sendKeys(pass2);
		fullName.sendKeys(name);
		email.sendKeys(mail);
	}
	public Boolean checkDetailsEntered()
	{
		if(!username.getAttribute("value").equals("") &&  !password.getAttribute("value").equals("") 
				&& !passConfirm.getAttribute("value").equals("") && !fullName.getAttribute("value").equals("")
				&& !email.getAttribute("value").equals(""))
			return true;
		else
			return false;
	}
	public void create()
	{
		createBtn.submit();
	}
	//======== Getters ==========
	public WebElement getCreateUserForm() 
	{
		return createUserForm;
	}
	public WebElement getCreateBtn() 
	{
		return createBtn;
	}


	public WebElement getUsername() 
	{
		return username;
	}


	public WebElement getPassword() 
	{
		return password;
	}


	public WebElement getPassConfirm() 
	{
		return passConfirm;
	}


	public WebElement getFullName() 
	{
		return fullName;
	}


	public WebElement getEmail() 
	{
		return email;
	}



	
	
}
