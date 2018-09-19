package com.qa.TheDemoSite.Demo;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.chrome.ChromeDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class DemoTestManager 
{
	protected static final String saveDirectory = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\Demo\\TestReports\\";
	protected static final String saveDataDirectory = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\Demo\\TestData\\";
	protected  String path = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\WebDriverLocation\\chromedriver.exe";
	protected  String driverKey = "webdriver.chrome.driver";
	protected ChromeDriver driver;
	public String name;
	public String pass;
	
//data
	protected static HashMap<String,String> users;
//get test Data
	protected static void getDataFromFile(String fileName)
	{
		users = new HashMap<String,String>();
		FileInputStream file = null;
		try
		{
			file  = new FileInputStream(saveDataDirectory+fileName);
		}
		catch(FileNotFoundException e)
		{	
		}
		XSSFWorkbook workbook = null;
		try
		{
			workbook = new XSSFWorkbook(file);
		}
		catch(IOException e)
		{	
		}
		for (Row row : workbook.getSheetAt(0))
		{
			String userName = "";
			String password = "";
			for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) 
			{
				if(i == 0)
				{
					userName = row.getCell(i).getStringCellValue();
					
				}
				else
				{
					//change all password to potato
					row.getCell(i).setCellValue("potato");
					password = row.getCell(i).getStringCellValue();
				}
			}
			users.put(userName, password);
			
		}
		
	}
//reporting
	protected static ExtentReports report;
	protected ExtentTest test;
	protected Boolean check;
	
	protected void reporting(String passMessage, String failMessage)
	{
		if(check)
		{
			test.log(LogStatus.PASS,passMessage);
			
		}
		else
		{
			test.log(LogStatus.FAIL, failMessage);
		}
		assertTrue(failMessage,check);
//screenshot
		HelperMethods.screenshot(driver,saveDirectory);
	}
	
	@AfterClass
	public static void finish()
	{
		report.flush();
	}
	
}
