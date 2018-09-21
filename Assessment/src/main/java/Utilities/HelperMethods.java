package Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

public class HelperMethods 
{

	public static void screenshot(WebDriver driver, String directory) 
	{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;

		File source = ts.getScreenshotAs(OutputType.FILE);

		String destination = directory
				+ "Pass" + dateName + ".png";

		File finalDestination = new File(destination);

		try 
		{
			FileHandler.copy(source, finalDestination);
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public static void scroll(Actions action,Boolean down, WebElement e, int times) throws InterruptedException
	{
		action.moveToElement(e).click().perform();
		for (int i = 0; i < times; i++) 
		{
			if(down)
				action.sendKeys(Keys.ARROW_DOWN).perform();
			else
				action.sendKeys(Keys.UP).perform();
			
			Thread.sleep(50);
			
		}
	}
}
