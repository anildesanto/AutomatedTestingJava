package com.qa.Assessment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class JenkinsManageUsersPage 
{
	@FindBy(xpath = "//*[@id=\"main-panel\"]/h1")
	private WebElement manageUsersHeader;
	
	@FindBy(xpath = "//*[@id=\"tasks\"]/div[3]/a[2]")
	private WebElement createUserBtn;

	@FindBy(xpath= "//*[@id=\"people\"]/tbody")
	private WebElement usersTable;
	
	public static int nurmbersOfUser;

	public WebElement getCreateUserBtn() 
	{

		return createUserBtn;
	}
	public WebElement getManageUsersHeader() 
	{
		return manageUsersHeader;
	}
	public Boolean userCreated()
	{
		System.out.println("User table size1: "+ usersTable.getSize().height + " User table size2: "+ nurmbersOfUser);
		//List<WebElement> list = driver.findElements(By.className("model-link inside"));
		//System.out.println("User Amount: "+ list.size());

		if(usersTable.getSize().height > nurmbersOfUser)
			return true;
		else
			return false;
	}
	public WebElement findUserOnScreen(WebDriver driver, String name)
	{
		System.out.println("============== Checking =============");
		String outer ="http://localhost:8080/securityRealm/user/"+name+"/";
		List<WebElement> found = usersTable.findElements(By.className("inside"));
		for (WebElement webElement : found) 
		{
			System.out.println("Found: " + webElement.getAttribute("href"));
			System.out.println("Matching? " + outer + " " + webElement.getAttribute("href").equals(outer));
			if(webElement.getAttribute("href").toLowerCase().equals(outer.toLowerCase()))
				return webElement;
		}
		return null;
	}
	public int getNurmbersOfUser() 
	{
		System.out.println("User table size: "+ usersTable.getSize().height);
		return usersTable.getSize().height;
	}
	public void setNurmbersOfUser(int num) 
	{
		System.out.println("User table size2: "+ usersTable.getSize().height);
		nurmbersOfUser = num;
	}
	public WebElement getUsersTable() 
	{
		return usersTable;
	}
}
