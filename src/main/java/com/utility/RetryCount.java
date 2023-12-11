package com.utility;

import java.util.Iterator;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryCount implements IRetryAnalyzer
{

	public static int testCounter=0;
	int counter = 0;
	int retryLimit = 1;
	
	
	@Override
	public boolean retry(ITestResult result) {
		if (counter < retryLimit) {
			counter++;
			testCounter++;
			ExtentReporter.extent.removeTest(ExtentReporter.test);
			return true;
		}
//		testCounter=0;
//		testCounter--;
//		ExtentReporter.extentLogger("", "2nd");
		return false;
	}

}
