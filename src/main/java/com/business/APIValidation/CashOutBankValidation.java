package com.business.APIValidation;

import com.utility.ExtentReporter;
import com.utility.LoggingUtils;
import com.utility.Validation;
import io.restassured.response.ValidatableResponse;
import com.business.*;
import java.io.IOException;

import static com.business.APIValidation.CashInFromBankValidation.dataProvider;


public class CashOutBankValidation extends Exception{
    public static LoggingUtils log = new LoggingUtils();
    public void cashOutBankWithMandatoryAndOptionalFields_COTB_TC_01() throws Exception {
        Object[][] data = dataProvider.cashoutToBank("user_200");
        ValidatableResponse response = com.business.APIResponse.CashOutToBank.cashOutToBank(data);

        //Status Code Validation
        int responseBody = response.extract().statusCode();
        Validation.validatingStatusCode(responseBody, 200, "Validating 200 Success Response");

        String runDate = response.extract().body().jsonPath().get("runDate");
        log.info("Run Date : "+runDate);
        ExtentReporter.extentLogger("Run Date","Run Date : "+runDate);

        String exeDate = response.extract().body().jsonPath().get("exeDate");
        log.info("Expiry Date : "+exeDate);
        ExtentReporter.extentLogger("Expiry Date","Expiry Date : "+exeDate);


        String refNo = response.extract().body().jsonPath().get("refNo");
        log.info("Reference Number : "+refNo);
        ExtentReporter.extentLogger("Reference Number","Reference Number : "+refNo);

        Validation.assertEqualsInt(response.extract().body().jsonPath().get("respCode"), 1,"Response code");
        Validation.assertEquals(response.extract().body().jsonPath().get("respMsg"),"Success","Response Message");


    }
}
