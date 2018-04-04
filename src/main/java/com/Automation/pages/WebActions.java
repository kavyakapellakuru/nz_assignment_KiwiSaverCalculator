/**
 * KiwiSaver Retirement Calculator 
 * Common functions Class
 * @author Kavyaka Pellakuru
 */
package com.Automation.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebElement;

import net.thucydides.core.pages.PageObject;

public class WebActions extends PageObject {

	public static HashMap<String, String> testData = new HashMap<String, String>();

	/**
	 * Method to retrieve test data from excel sheet
	 * 
	 * @param String
	 *            sheetName
	 * @param String
	 *            rowKeyword
	 * @return HashMap
	 * @throws Exception
	 */
	public static HashMap<String, String> getData(String sheetName, String rowKeyword) throws Exception {
		HashMap<String, String> iunputData = new HashMap<String, String>();

		/* Read the file */
		FileInputStream file = new FileInputStream(new File("TestDataSheet/TestData.xls"));

		/* Get the workbook instance for XLS file */
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		/* Get first sheet from the workbook */
		HSSFSheet sheet = workbook.getSheet(sheetName);

		/* Get row and column count */
		int rowCount = sheet.getPhysicalNumberOfRows();

		int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

		/* Form Hashmap with column name and value. */
		for (int i = 0; i < rowCount; i++) {
			if (sheet.getRow(i).getCell(0).toString().equals(rowKeyword)
					&& sheet.getRow(i).getCell(1).toString().equals("yes")) {
				for (int k = 0; k < columnCount; k++) {

					iunputData.put(sheet.getRow(0).getCell(k).toString(),
							sheet.getRow(i).getCell(k).getStringCellValue());
				}
				break;
			}
		}
		workbook.close();
		file.close();

		return iunputData;
	}

	/**
	 * Method to write data into excel sheet
	 * 
	 * @param String
	 *            sheetName
	 * @param String
	 *            rowName
	 * @param HashMap
	 *            custDetails
	 * @throws Exception
	 */
	public static void putData(String sheetName, String rowName, HashMap<String, String> custDetails) throws Exception {

		/* Read the file. */
		FileInputStream file = new FileInputStream(new File("TestDataSheet/TestData.xls"));

		/* Get the workbook instance for XLS file. */
		HSSFWorkbook workbook = new HSSFWorkbook(file);

		/* Get first sheet from the workbook. */
		HSSFSheet sheet = workbook.getSheet(sheetName);

		/* Get total number of rows and columns count. */
		int rowCount = sheet.getPhysicalNumberOfRows();
		int columnCount = sheet.getRow(0).getPhysicalNumberOfCells();

		int rowMatch, cellMatch;
		for (rowMatch = 0; rowMatch < rowCount; rowMatch++) {

			if (sheet.getRow(rowMatch).getCell(0).toString().equals(rowName))
				break;
		}

		/* Get exact cell number to insert data. */
		for (cellMatch = 1; cellMatch < columnCount; cellMatch++) {

			/* set data at specified row and column number. */
			Cell cell = sheet.getRow(rowMatch).getCell(cellMatch);

			if (!cell.getStringCellValue().equals("NA"))
				cell.setCellValue(custDetails.get(sheet.getRow(0).getCell(cellMatch).toString()));
		}

		/* save the file and close. */
		FileOutputStream outFile = new FileOutputStream(new File("TestDataSheet/TestData.xls"));
		workbook.write(outFile);
		workbook.close();
		outFile.close();
	}

	/**
	 * Method to Scroll into view of element
	 * 
	 * @param WebElement
	 *            element
	 * @throws Exception
	 */

	public void ScrollIntoView(WebElement element) throws Exception {

		JavascriptExecutor jse = (JavascriptExecutor) getDriver();
		jse.executeScript("arguments[0].scrollIntoView(true)", element);

	}

	/**
	 * Method to perform JavaScript Click
	 * 
	 * @param WebElement
	 *            element
	 * @throws Exception
	 */

	public void JavaScriptClick(WebElement element) throws Exception {

		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click();", element);

	}

	/**
	 * Method to verify text at a given location
	 * 
	 * @param WebElement
	 *            element
	 * @param String
	 *            msg
	 * @return boolean
	 * @throws Exception
	 */
	public boolean verifyTextAtLocation(WebElement element, String msg) throws Exception {

		String actualText = element.getText();

		String expectedText = msg;

		return actualText.trim().equals(expectedText);
	}

	/**
	 * Method to switch to a particular frame in web application
	 * 
	 * @param WebElement
	 *            element
	 * @throws Exception
	 */
	public void switchToFrame(WebElement element) throws Exception {
		getDriver().switchTo().frame(element);

	}

	/**
	 * Method to return WebElement
	 * 
	 * @param By
	 *            locator
	 * @return WebElement
	 * @throws Exception
	 */
	public WebElement findWebElement(By locator) throws Exception {
		WebElement elem = null;

		elem = getDriver().findElement(locator);

		return elem;

	}

}
