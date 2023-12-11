package com.MLWalletScripts;

import com.Datasheet.CashInFromBank;
import com.business.APIValidation.CashInFromBankValidation;
import com.utility.LoggingUtils;
import org.testng.annotations.Test;

import com.utility.ExtentReporter;

public class CashInFromBankScripts {

	static LoggingUtils logger = new LoggingUtils();
	private static CashInFromBankValidation cashInFromBankValidation;


	static CashInFromBank dataProvider = new CashInFromBank();

	@Test
	public void cashInFromBankRespCodeAndMsgValidation_CIFB_TC_01() throws Exception {
		ExtentReporter.HeaderChildNode("CIFB_TC_01, Cash in from Bank respCode and respMsg validation");
		cashInFromBankValidation = new CashInFromBankValidation();
		cashInFromBankValidation.cashInFromBankRespCodeAndMsgValidation_CIFB_TC_01();
		logger.info("CIFB_TC_01,Cash in from Bank respCode and respMsg validation");
		ExtentReporter.extentLoggerPass("CIFB_TC_01,Cash in from Bank respCode and respMsg validation");
		}
	}



