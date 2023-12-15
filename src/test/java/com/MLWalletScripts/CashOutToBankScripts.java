package com.MLWalletScripts;

import com.Datasheet.CashInFromBank;
import com.business.APIValidation.CashInFromBankValidation;
import com.business.APIValidation.CashOutBankValidation;
import com.utility.ExtentReporter;
import com.utility.LoggingUtils;
import org.testng.annotations.Test;

public class CashOutToBankScripts {

    static LoggingUtils logger = new LoggingUtils();
    private static CashOutBankValidation cashOutBankValidation;

    @Test(priority = 1)
    public void cashOutBankWithMandatoryAndOptionalFields_COTB_TC_01() throws Exception {
        ExtentReporter.HeaderChildNode("Cash out bank with mandatory and optional fields");
        cashOutBankValidation = new CashOutBankValidation();
        cashOutBankValidation.cashOutBankWithMandatoryAndOptionalFields_COTB_TC_01();
        logger.info("Cash out bank with mandatory and optional fields validated");
        ExtentReporter.extentLoggerPass("Cash out bank with mandatory and optional fields validated");
    }

    @Test(priority = 2)
    public void cashOutBankWithEmptyId_COTB_TC_04() throws Exception {
        ExtentReporter.HeaderChildNode("Cash out to bank with empty id field");
        cashOutBankValidation = new CashOutBankValidation();
        cashOutBankValidation.cashOutBankWithEmptyId_COTB_TC_04();
        logger.info("Cash out to bank with empty id field Error message and error code validated");
        ExtentReporter.extentLoggerPass("Cash out to bank with empty id field Error message and error code validated");
    }


}
