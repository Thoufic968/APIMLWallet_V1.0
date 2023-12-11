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

    @Test()
    public void cashOutBankWithMandatoryAndOptionalFields_COTB_TC_01() throws Exception {
        ExtentReporter.HeaderChildNode("Cash out bank with mandatory and optional fields");
        cashOutBankValidation = new CashOutBankValidation();
        cashOutBankValidation.cashOutBankWithMandatoryAndOptionalFields_COTB_TC_01();
        logger.info("Cash out bank with mandatory and optional fields validated");
        ExtentReporter.extentLoggerPass("Cash out bank with mandatory and optional fields validated");
    }


}
