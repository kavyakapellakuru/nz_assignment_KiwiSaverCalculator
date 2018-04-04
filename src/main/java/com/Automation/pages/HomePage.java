/**
 * Westpac NZ Home Page Class
 * 
 * @author Kavyaka Pellakuru
 */
package com.Automation.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

public class HomePage extends PageObject {

	@FindBy(linkText = "KiwiSaver")
	private WebElement kiwiSaverTab;

	@FindBy(linkText = "KiwiSaver calculators")
	private WebElement kiwiSaverCalculatorButton;

	/* Hover over KiwiSaver tab on Westpac Home page */

	public void hoverKiwiSaverTab() {

		try {
			Actions action = new Actions(getDriver());
			action.moveToElement(kiwiSaverTab).build().perform();
		}

		catch (Exception e) {

			e.printStackTrace();
		}

	}

	/* select KiwiSaver Calculator button under KiwiSaver tab */
	public void selectKiwiSaverCalculator() {

		try {
			/* Explicit wait before clicking the element */
			WebElement calculatorButton = waitFor(kiwiSaverCalculatorButton);
			calculatorButton.click();

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

}
