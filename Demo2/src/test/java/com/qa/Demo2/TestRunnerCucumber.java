package com.qa.Demo2;

import org.junit.runner.RunWith;

import com.relevantcodes.extentreports.ExtentReports;

import Utilities.Constants;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:")
public class TestRunnerCucumber 
{
	public static ExtentReports report = new ExtentReports(Constants.saveDirectory+"Demo2CucumberTestReport.html", true);
}
