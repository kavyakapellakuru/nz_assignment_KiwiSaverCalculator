/**
 * KiwiSaver Retirement Calculator Test Data set-up
 * and browser initializer Class 
 * @author Kavyaka Pellakuru
 */
package com.Automation.cucumber;

import java.io.File;

import com.Automation.pages.WebActions;

import cucumber.api.Scenario;
import cucumber.api.java.Before;

public class InitializerClass {

	String ScenarioName = "";
	public static String TestDataIdentifier = "";

	@Before
	public void setUp(Scenario result) throws Exception {
		
		ScenarioName = result.getName();

		TestDataIdentifier = ScenarioName;
		

		WebActions.testData = WebActions.getData("KiwiSaver_TestData", TestDataIdentifier);

		File file = new File("Drivers/chromedriver.exe");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());

	}

}
