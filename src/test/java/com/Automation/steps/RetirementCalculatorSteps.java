/**
 * KiwiSaver Retirement Calculator Steps interface
 * 
 * @author Kavyaka Pellakuru
 */
package com.Automation.steps;

import com.Automation.pages.RetirementCalculatorPage;
import com.Automation.pages.WebActions;

import net.thucydides.core.annotations.Step;

import net.thucydides.core.steps.ScenarioSteps;

@SuppressWarnings("serial")
public class RetirementCalculatorSteps extends ScenarioSteps {

	RetirementCalculatorPage kiwiSaverPage;
	WebActions action;

	@Step("Verify Information Icons are displayed for all fields on kiwisaver Retirement Calculator screen")
	public boolean verifyHelpIconsDisplayed(String expectedHelpIcons) {

		boolean status = kiwiSaverPage.verifyHelpIconsDisplayed(expectedHelpIcons);
		return status;

	}

	@Step("Verify 'Current Age' field Information Text")
	public Boolean verifyCurrentAgeHelpText(String expectedInfoText) {

		Boolean status = kiwiSaverPage.verifyCurrentAgeHelpText(expectedInfoText);
		return status;

	}

	@Step("User enters Current Age")
	public void enterCurrentAge(String currentAge) {

		kiwiSaverPage.enterCurrentAge(currentAge);

	}

	@Step("User selects Employment Status")
	public void selectEmploymentStatus(String empStatus, String Sal, String memberContribution) {

		kiwiSaverPage.selectEmploymentStatus(empStatus, Sal, memberContribution);

	}

	@Step("User selects PIR")
	public void selectPIR(String PIR) {

		kiwiSaverPage.selectPIR(PIR);

	}

	@Step("User enters Current KiwiSaver Balance")
	public void enterKiwiSaverBalance(String kiwiSaverBalance) {

		kiwiSaverPage.enterKiwiSaverBalance(kiwiSaverBalance);

	}

	@Step("User enters Voluntary Contributions")
	public void enterVoluntaryContributions(String volContribution, String contributionFrequency) {

		kiwiSaverPage.enterVoluntaryContributions(volContribution, contributionFrequency);

	}

	@Step("User selects Risk Profile")
	public void selectRiskProfile(String riskProfile) {

		kiwiSaverPage.selectRiskProfile(riskProfile);

	}

	@Step("User enters Savings Goal")
	public void enterSavingsGoal(String savingsGoal) {

		kiwiSaverPage.enterSavingsGoal(savingsGoal);

	}

	@Step("Calculate and capture Projected Balance")
	public String captureProjectedBalance() {

		String projectedBalance = kiwiSaverPage.captureProjectedBalance();
		return projectedBalance;

	}
}
