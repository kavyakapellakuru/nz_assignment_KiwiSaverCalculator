/**
 * KiwiSaver Scheme and Retirement Calculator Steps interface
 * 
 * @author Kavyaka Pellakuru
 */
package com.Automation.steps;

import com.Automation.pages.KiwiSaverSchemePage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class KiwiSaverSchemeSteps extends ScenarioSteps {

	KiwiSaverSchemePage schemePage;

	@Step("User clicks on 'Click here to get started' button")
	public void clickOnGetStartedButton() {

		schemePage.clickOnGetStartedButton();

	}

}
