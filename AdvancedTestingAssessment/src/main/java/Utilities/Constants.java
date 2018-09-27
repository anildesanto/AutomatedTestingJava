package Utilities;

import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.RestAssured;

public class Constants 
{
	public static final String saveDirectory = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\AdvancedTestingAssessment\\TestReports\\";
	public static final String saveDataDirectory = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\AdvancedTestingAssessment\\TestData\\";
	public static final String path = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\WebDriverLocation\\chromedriver.exe";
	public static final String driverKey = "webdriver.chrome.driver";
	public static  ChromeDriver driver = new ChromeDriver();
	public static final String fileTestData = "TestPass.xlsx";
	
	public static final String addOwnerPage = "http://10.0.10.10:4200/petclinic/owners/add";
	public static final String OwnersPage = "http://10.0.10.10:4200/petclinic/owners";
	
	//===================================
	public static final String contentJasonType = "application/json";
	public static final String contentType = "Content-Type";
	public static final String baseUri = "http://10.0.10.10:9966/petclinic/api/";
	
}
