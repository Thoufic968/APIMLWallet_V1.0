package com.utility;

import java.util.Iterator;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryCount_old implements IRetryAnalyzer
{


	//Counter to keep track of retry attempts
	private int MAX_RETRY_COUNT = 2; // Maximum number of retries

	private int retryCount = 1;

	//	@Override
	//	public boolean retry(ITestResult result) {
	//
	//
	//		for (int i = retryCount; i < MAX_RETRY_COUNT; i++) {
	//			if (retryCount < MAX_RETRY_COUNT) {
	////				System.out.println("Retrying " + result.getName() + " again and the count is " + (retryCount));
	//				retryCount++;
	//				System.out.println("i: "+i);
	//				System.out.println("true");
	//
	//				return true;
	//			}
	//			
	//		}
	//		System.out.println("-Retrying " + result.getName() + " again and the count is " + (retryCount-1));
	//		System.out.println("false");
	//		return false;
	//	} 


//	public static void main(String[] args) {
//		retry();
//	}
	
	@Override
	public boolean retry(ITestResult result) {
		int i =0;
		if (retryCount < MAX_RETRY_COUNT) {
			for ( i = retryCount; i < MAX_RETRY_COUNT; i++) {
				
				//retryCount++;
				System.out.println("i: "+i);
				System.out.println("true");
			}
			System.out.println("Retrying " + result.getName() + " again and the count is " + (i-1));
			return true;

		}else
		{
			//			System.out.println("-Retrying " + result.getName() + " again and the count is " + (retryCount-1));
			System.out.println("false");
			//	return false;
		}
		return false;

	} 
	

}
