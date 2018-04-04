/**
 * KiwiSaver Retirement Calculator Page Class
 * 
 * @author Kavyaka Pellakuru
 */

package com.Automation.pages;

import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;

public class RetirementCalculatorPage extends PageObject {

	WebActions action;

	/* Use of Page Factory for storing locators */

	@FindBy(xpath = "//iframe[contains(@src,'/calculator-widgets/kiwisaver-calculator/')]")
	private WebElement kiwiSaverCalculatorIframe;

	@FindBy(xpath = "//div[@class='ng-isolate-scope'][@help-id='CurrentAge']/button")
	private WebElement currentAgeHelpIcon;

	@FindBy(xpath = "//div[@help-id='CurrentAge']/descendant::p[contains(text(), 'This calculator has an age limit of')]")
	private WebElement currentAgeHelpTextLocation;

	@FindBy(xpath = "//div[@model='ctrl.data.CurrentAge']/descendant::input")
	private WebElement currentAgeInputBox;

	@FindBy(xpath = "//div[@ng-model='ctrl.data.EmploymentStatus']/descendant::i[@class='ir dropdown-arrow']")
	private WebElement employmentStatusDropdown;

	@FindBy(xpath = "//div[@ng-model='ctrl.data.EmploymentStatus']/descendant::ul/li/descendant::span[contains(text(),'Employed')]")
	private WebElement employedStatus;

	@FindBy(xpath = "//label[contains(text(), 'Prescribed investor rate (PIR)')]")
	private WebElement PIRLabel;

	@FindBy(xpath = "//div[@ng-model='ctrl.data.PIRRate']/descendant::i[@class='ir dropdown-arrow']")
	private WebElement PIRDropdown;

	@FindBy(xpath = "//div[@model='ctrl.data.AnnualIncome']/descendant::input")
	private WebElement salaryInputBox;

	@FindBy(xpath = "//div[@model='ctrl.data.KiwiSaverBalance']/descendant::input")
	private WebElement currentKiwiSaverBalanceInputBox;

	@FindBy(xpath = "//div[@model='ctrl.data.VoluntaryContributions']/descendant::input")
	private WebElement voluntaryContributionsInputBox;

	@FindBy(xpath = "//div[@model='ctrl.data.VoluntaryContributions']/descendant::i[@class='ir dropdown-arrow']")
	private WebElement voluntaryContributionsDropdown;

	@FindBy(xpath = "//div[@model='ctrl.data.SavingsGoal']/descendant::input")
	private WebElement savingsGoalInputBox;

	@FindBy(xpath = "//button[@ng-click='ctrl.showResultsPanel()']")
	private WebElement retirementProjectionsButton;

	@FindBy(xpath = "//span[@class='result-value result-currency ng-binding']")
	private WebElement estimatedBalance;

	/*
	 * Verifying if all fields in the KiwiSaver calculator have got the
	 * information icon present
	 */
	public boolean verifyHelpIconsDisplayed(String expectedHelpIcons) {
		Boolean status = null;
		try {

			/*
			 * Selecting Employed status in order to expand hidden fields in
			 * application to verify all help icons
			 */
			WebElement empDropdown = waitFor(employmentStatusDropdown);
			action.JavaScriptClick(empDropdown);
			WebElement employmentStatus = waitFor(employedStatus);
			employmentStatus.click();

			/* Fetching the count of all help icons in KiwiSaver Calculator */
			int iconcount_actual = getDriver().findElements(By.className("icon")).size();

			/*
			 * Fetching the help-id attribute of all help icons and storing the
			 * values in a list
			 */
			List<WebElement> helpids_WebElements = getDriver()
					.findElements(By.xpath("//i[@class='icon']/ancestor::div[@cl-help-toggle='']"));
			List<String> helpids_application = new ArrayList<String>();

			for (WebElement actual_ids : helpids_WebElements) {
				helpids_application.add(actual_ids.getAttribute("help-id"));
			}

			/*
			 * Fetching the expected help-ids from excelsheet and storing in a
			 * list
			 */

			String contexts[] = expectedHelpIcons.split(";");

			int iconcount_expected = contexts.length;

			List<String> helpids_expected = new ArrayList<String>();

			for (String expected_ids : contexts) {
				helpids_expected.add(expected_ids.toString());

			}

			/*
			 * comparing the list of help-ids fetched from application with the
			 * expected list fetched from excel sheet
			 */
			if (iconcount_actual == iconcount_expected) {

				status = helpids_application.containsAll(helpids_expected);

			}

			else {

				status = false;

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	/* Verification of Current Age field help text */
	public boolean verifyCurrentAgeHelpText(String expectedInfoText) {

		Boolean status = null;
		try {
			currentAgeHelpIcon.click();

			WebElement helpTextLocation = waitFor(currentAgeHelpTextLocation);

			if (helpTextLocation.isDisplayed()) {

				status = action.verifyTextAtLocation(currentAgeHelpTextLocation, expectedInfoText);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return status;
	}

	/* User enters current age */
	public void enterCurrentAge(String currentAge) {

		try {
			WebElement ageInput = waitFor(currentAgeInputBox);

			ageInput.sendKeys(currentAge);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/* User selects Employment Status */
	public void selectEmploymentStatus(String empStatus, String Sal, String memberContribution) {

		try {
			action.JavaScriptClick(employmentStatusDropdown);

			if (empStatus.equalsIgnoreCase("Employed")) {

				/*
				 * Selecting Employment Status based on the value passed from
				 * excel by using 'dynamic xpath'
				 */

				WebElement employmentStatus = waitFor(action.findWebElement(By
						.xpath("//div[@ng-model='ctrl.data.EmploymentStatus']/descendant::ul/li/descendant::span[contains(text(),'"
								+ empStatus + "')]")));

				employmentStatus.click();

				/*
				 * Wait for salary input text box to load and enter salary
				 */
				WebElement salaryInput = action.waitFor(salaryInputBox);
				salaryInput.sendKeys(Sal);

				/*
				 * Select KiwiSaver Member Contribution based on the value
				 * passed from excel by using 'dynamic xpath'
				 */
				action.findWebElement(By
						.xpath("//div[@ng-model='ctrl.data.KiwiSaverMemberContribution']/descendant::input[contains(@value,'"
								+ memberContribution + "')]"))
						.click();

			}

			/*
			 * Selection of Employment Status Other than 'Employed' by using
			 * 'dynamic xpath'
			 */

			else {

				WebElement employmentStatus = waitFor(action.findWebElement(By
						.xpath("//div[@ng-model='ctrl.data.EmploymentStatus']/descendant::ul/li/descendant::span[contains(text(),'"
								+ empStatus + "')]")));

				employmentStatus.click();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/* Selecting Prescribed Investor Rate value */

	public void selectPIR(String PIR) {

		try {
			/* Scrolling into the view of PIR Label */
			action.ScrollIntoView(PIRLabel);

			action.JavaScriptClick(PIRDropdown);

			/*
			 * Selecting PIR based on the value passed from excel by using
			 * 'dynamic xpath'
			 */

			WebElement PIRValue = waitFor(action.findWebElement(
					By.xpath("//div[@ng-model='ctrl.data.PIRRate']/descendant::ul/descendant::span[contains(text(),'"
							+ PIR + "')]")));
			PIRValue.click();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/* To enter Current KiwiSaver Balance */
	public void enterKiwiSaverBalance(String kiwiSaverBalance) {

		try {
			/*
			 * As 'Current KiwiSaver balance' Input field is Optional, value is
			 * entered only if it is provided in excel sheet
			 */

			if (!kiwiSaverBalance.equals("")) {

				currentKiwiSaverBalanceInputBox.sendKeys(kiwiSaverBalance);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/* To enter Voluntary Contribution amount & Frequency */
	public void enterVoluntaryContributions(String volContribution, String contributionFrequency) {

		try {
			/*
			 * As 'Voluntary Contributions' Input field is Optional, value is
			 * entered only if it is provided in excel sheet
			 */

			if (!volContribution.equals("")) {

				voluntaryContributionsInputBox.sendKeys(volContribution);

				/*
				 * Entering the Frequency of Voluntary Contribution based on the
				 * value passed from excel by using 'dynamic xpath'
				 */

				action.JavaScriptClick(voluntaryContributionsDropdown);
				WebElement volContributionFrequency = waitFor(action.findWebElement(By
						.xpath("//div[@model='ctrl.data.VoluntaryContributions']/descendant::ul/li/descendant::span[contains(text(),'"
								+ contributionFrequency + "')]")));

				volContributionFrequency.click();

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/* Selecting Risk Profile */
	public void selectRiskProfile(String riskProfile) {

		try {

			/*
			 * Selecting Risk Profile based on the value passed from excel by
			 * using 'dynamic xpath'
			 */

			WebElement riskProfileValue = waitFor(action.findWebElement(
					By.xpath("//div[@ng-model='ctrl.data.RiskProfile']/descendant::span[contains(text(), '"
							+ riskProfile + "')]/ancestor::span/preceding-sibling::span/input")));

			riskProfileValue.click();
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/* To enter Savings Goal */
	public void enterSavingsGoal(String savingsGoal) {

		try {

			/*
			 * As 'Savings Goal' Input field is Optional, value is entered only
			 * if it is provided in excel sheet
			 */

			if (!savingsGoal.equals("")) {

				savingsGoalInputBox.sendKeys(savingsGoal);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/*
	 * Capturing the projected balance displayed, and passing the value to the
	 * calling class to copy the value into excel sheet
	 */
	public String captureProjectedBalance() {
		String projectedBalance = null;
		try {
			if (retirementProjectionsButton.isEnabled()) {
				action.JavaScriptClick(retirementProjectionsButton);
				WebElement estimatedBal = action.waitFor(estimatedBalance);

				projectedBalance = estimatedBal.getText();

			} else {

				throw new Exception("Projected Balance Button is not enabled, Please fill all the required fields");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return projectedBalance;

	}
}
