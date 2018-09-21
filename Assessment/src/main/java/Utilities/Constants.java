package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Constants 
{
	public static final String saveDirectory = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\Assessment\\TestReports\\";
	public static final String saveDataDirectory = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\Assessment\\TestData\\";
	public static final String path = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\WebDriverLocation\\chromedriver.exe";
	public static final String driverKey = "webdriver.chrome.driver";
	public static  ChromeDriver driver = new ChromeDriver();
	public static final String fileTestData = "JenkinsWriteTestPass.xlsx";
	public static int count = 0;
	//======= Log In =========
	public static final String name = "anilde";
	public static final String pass = "santo1515";
	//======== URLs ===========
	
	public static final String jenkinsLogInPage = "http://localhost:8080/login?from=%2F";
	public static final String jenkinsHomePage = "http://localhost:8080/";
	public static final String jenkinsManagePage = "http://localhost:8080/manage";
	public static final String jenkinsManageUsersPage = "http://localhost:8080/securityRealm/";
	public static final String jenkinsAddUserPage = "http://localhost:8080/securityRealm/addUser";
	
}
