package com.Datasheet;

import com.utils.Excel;
import org.testng.annotations.DataProvider;

import java.io.IOException;


public class CashInFromBank {
	public static String excelPath()
	{
		String os = System.getProperty("os.name");
		String path = System.getProperty("user.dir");
		String filePath;
		filePath = path + "//src//main//java//com//Datasheet//CashInFromBank.xlsx";
		return filePath;
	}
	@DataProvider(name = "MLWALLET")
	public static Object[][] cashInFromBank(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "CashInFromBank", testCaseName);
	}

	@DataProvider(name = "MLWALLET1")
	public static Object[][] cashoutToBank(String testCaseName) throws IOException{
		return Excel.getTestData(excelPath(), "CashoutToBank", testCaseName);
	}


}
