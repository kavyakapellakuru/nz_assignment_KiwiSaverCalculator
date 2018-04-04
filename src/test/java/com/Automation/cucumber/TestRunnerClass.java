/**
 * KiwiSaver Retirement Calculator Runner Class
 * 
 * @author Kavyaka Pellakuru
 */
package com.Automation.cucumber;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/KiwiSaver_Featurefile/", tags = {"@Test_KiwiSaver"})
public class TestRunnerClass {

}
