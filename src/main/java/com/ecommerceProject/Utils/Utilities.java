package com.ecommerceProject.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Random;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {
	
	public static final int IMPLICIT_WAIT_TIME = 10;
	public static final int PAGE_LOAD_TIME=5;
	
	
	public static String generateEmail()
    {
    	Date date = new Date();
    	return "hp.garvit.1997"+date.toString().replace(" ","_").replace(":", "_")+"@gmail.com";
    }
	
	public static String randomPhnumber()
	{
		Random num = new Random();
		long ph = Math.abs(num.nextLong());
		String ph_num = Long.toString(ph);
		String ten_digit_ph_num = ph_num.substring(0, 10);
		return ten_digit_ph_num;
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName)
	{
		File excelFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\ecommerceProject\\testdata\\EcommProject_TestData.xlsx");
		XSSFWorkbook workbook=null;
		try 
		{
			FileInputStream fis = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fis);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		XSSFSheet sheet=workbook.getSheet(sheetName);
		
		int rows=sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i=0;i<rows;i++)
		{
			XSSFRow row = sheet.getRow(i+1);
			
			for(int j=0;j<cols;j++)
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType)
				{
				case STRING:
					data[i][j]=cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j]=Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j]=cell.getBooleanCellValue();
					break;
				default:
					break;
				}
			}
		}
		return data;
	}

}
