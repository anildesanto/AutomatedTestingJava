package com.qa.AdvancedTestingAssessment;


import org.junit.runner.RunWith;

import com.relevantcodes.extentreports.ExtentReports;

import Utilities.Constants;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:")
public class TestRunner 
{
	public static ExtentReports report = new ExtentReports(Constants.saveDirectory+"AddOwnerSeliniumReport.html", true);
}