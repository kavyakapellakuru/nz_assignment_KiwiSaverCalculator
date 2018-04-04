/**
 * KiwiSaver Scheme and Retirement Calculator Page Class
 * 
 * @author Kavyaka Pellakuru
 */
package com.Automation.pages;

import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

public class KiwiSaverSchemePage extends PageObject {

	WebActions action;

	@FindBy(linkText = "Click here to get started.")
	private WebElement getStartedButton;

	@FindBy(xpath = "//iframe[contains(@src,'/calculator-widgets/kiwisaver-calculator/')]")
	private WebElement kiwiSaverCalculatorIframe;

	/*
	 * Click on 'Click here to Get Started button' under Westpac KiwiSaver
	 * Scheme Retirement Calculator text
	 */
	public void clickOnGetStartedButton() {

		try {

			/* Explicit wait before clicking the element */
			WebElement getStartedClick = waitFor(getStartedButton);
			action.ScrollIntoView(getStartedClick);
			getStartedClick.click();

			/* Switching to KiwiSaver Calculator iFrame */
			WebElement iFrame = waitFor(kiwiSaverCalculatorIframe);

			if (iFrame.isDisplayed()) {
				action.switchToFrame(kiwiSaverCalculatorIframe);

			}
		}

		catch (Exception e) {

			e.printStackTrace();
		}

	}

}
