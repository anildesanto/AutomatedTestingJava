package com.qa.Practice;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PracticePage 
{
	@FindBy(id = "sb_form_q")
	private WebElement searchBox;
	
	@FindBy(id = "sb_form_go")
	private WebElement searchButton;
	
	public void searchFor(String text)
	{
		searchBox.sendKeys(text);
		searchButton.submit();
	}
}
