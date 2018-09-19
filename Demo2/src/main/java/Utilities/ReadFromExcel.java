package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.qa.Demo2.TestCreateAccountAndLogIn;

public class ReadFromExcel 
{
	
	public static Object[][] getDataFromFile(String saveDataDirectory, String fileName)
	{
		Object[][] objs = null;
		TestCreateAccountAndLogIn obj = null;
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

		String userName = null;
		String password = null;
		String userName1 = null;
		String password1 = null;
		int nrOfRows = workbook.getSheetAt(0).getPhysicalNumberOfRows();
		objs = new Object [nrOfRows][4];
		for (int i = 0; i < nrOfRows; i++) 
		{
			Row row = workbook.getSheetAt(0).getRow(i);
			for (int b = 0; b < row.getPhysicalNumberOfCells(); b++) 
			{
				
				if(b == 0)
				{
					userName = row.getCell(b).getStringCellValue();
					
				}
				else if(b == 1)
				{
					password = row.getCell(b).getStringCellValue();
				}
				else if(b == 2)
				{
					userName1 = row.getCell(b).getStringCellValue();
				}
				else
				{
					//row.getCell(i).setCellValue("potato");
					password1 = row.getCell(b).getStringCellValue();
				}

				
			}
			System.out.println(userName  +" "+ password + " " + userName1 + " " + password1);
			obj = new TestCreateAccountAndLogIn(userName, password, userName1, password1);
			Object[] objlist = {obj.getName() ,obj.getPass() , obj.getName1(), obj.getPass1()};
			objs[i] = objlist;
		}
		return objs;
	}
}
