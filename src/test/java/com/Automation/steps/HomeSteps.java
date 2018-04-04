/**
 * Westpac NZ Home Page Steps interface
 * 
 * @author Kavyaka Pellakuru
 */
package com.Automation.steps;

import com.Automation.pages.HomePage;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class HomeSteps extends ScenarioSteps {

	HomePage homePage;

	@Step("User launches 'Westpac NZ' application")
	public void launchApplication() {

		homePage.open();

	}

	@Step("User hovers over KiwiSaver tab")
	public void hoverKiwiSaverTab() {

		homePage.hoverKiwiSaverTab();

	}

	@Step("User selects KiwiSaver Calculator button")
	public void selectKiwiSaverCalculator() {

		homePage.selectKiwiSaverCalculator();

	}

}
