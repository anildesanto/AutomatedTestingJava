package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Constants 
{
	public static final String saveDirectory = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\Demo2\\TestReports\\";
	public static final String saveDataDirectory = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\Demo2\\TestData\\";
	public static final String path = "C:\\Users\\Admin\\Desktop\\AutomatedTestingJava\\WebDriverLocation\\chromedriver.exe";
	public static final String driverKey = "webdriver.chrome.driver";
	public static  ChromeDriver driver = new ChromeDriver();
	public static final String fileTestData = "DemoWriteTestPass.xlsx";
}
