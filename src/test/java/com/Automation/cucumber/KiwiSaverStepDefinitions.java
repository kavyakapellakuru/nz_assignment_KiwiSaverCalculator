/**
 * KiwiSaver Retirement Calculator Step Definitions Class
 * 
 * @author Kavyaka Pellakuru
 */

package com.Automation.cucumber;

import org.junit.Assert;

import com.Automation.pages.WebActions;
import com.Automation.steps.HomeSteps;
import com.Automation.steps.KiwiSaverSchemeSteps;
import com.Automation.steps.RetirementCalculatorSteps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import net.thucydides.core.annotations.Steps;

public class KiwiSaverStepDefinitions {

	@Steps

	RetirementCalculatorSteps kiwiSaverSteps;
	
	@Steps
	KiwiSaverSchemeSteps schemeSteps;
	
	@Steps
	HomeSteps homeSteps;

	@Given("^User launches 'Westpac NZ' application$")
	public void launchApplication()  {

		homeSteps.launchApplication();

	}

	@And("^Navigates to 'KiwiSaver Retirement Calculator' screen$")
	public void navigateToKiwiSaverScreen()  {

		homeSteps.hoverKiwiSaverTab();
		homeSteps.selectKiwiSaverCalculator();
		schemeSteps.clickOnGetStartedButton();

	}

	@Then("^Verify the display of Information icons for all fields$")
	public void verifyHelpIconsDisplayed()  {

		Boolean status = kiwiSaverSteps.verifyHelpIconsDisplayed(WebActions.testData.get("ExpectedHelpIcons"));
		Assert.assertTrue("Help icons are not displayed as expected", status);

	}

	@And("^Validate Information text for Current Age field$")
	public void verifyCurrentAgeHelpText()  {

		Boolean status = kiwiSaverSteps.verifyCurrentAgeHelpText(WebActions.testData.get("CurrentAgeHelpText"));
		Assert.assertTrue("Current Age Information text is not displayed as expected", status);
	}

	@Then("^Verify User is able to calculate projected balances at retirement$")
	public void calculateProjectedBalance()  {

		kiwiSaverSteps.enterCurrentAge(WebActions.testData.get("CurrentAge"));
		kiwiSaverSteps.selectEmploymentStatus(WebActions.testData.get("EmploymentStatus"),
				WebActions.testData.get("Salary"), WebActions.testData.get("MemberContribution"));
		kiwiSaverSteps.selectPIR(WebActions.testData.get("PIR"));
		kiwiSaverSteps.enterKiwiSaverBalance(WebActions.testData.get("KiwiSaverBalance"));
		kiwiSaverSteps.enterVoluntaryContributions(WebActions.testData.get("VoluntaryContribution"),
				WebActions.testData.get("VoluntaryContributionFrquency"));
		kiwiSaverSteps.selectRiskProfile(WebActions.testData.get("RiskProfile"));
		kiwiSaverSteps.enterSavingsGoal(WebActions.testData.get("SavingsGoal"));

		String projectedBalance = kiwiSaverSteps.captureProjectedBalance();

		WebActions.testData.put("ProjectedBalance", projectedBalance);
		try {
			WebActions.putData("KiwiSaver_TestData", WebActions.testData.get("TestCase"), WebActions.testData);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
