package com.business.APIValidation;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertNotNull;

import com.Datasheet.CashInFromBank;

import com.utility.Validation;

import io.restassured.response.ValidatableResponse;
import org.testng.asserts.SoftAssert;

public class CashInFromBankValidation {


	static CashInFromBank dataProvider = new CashInFromBank();
	static SoftAssert soft=new SoftAssert();

	public static void cashInFromBankRespCodeAndMsgValidation_CIFB_TC_01() throws Exception {

		Object[][] data = dataProvider.cashInFromBank("user_200");
		ValidatableResponse response = com.business.APIResponse.CashInFromBank.cashInFromBank(data);

		//Status Code Validation
		int responseBody = response.extract().statusCode();
		Validation.validatingStatusCode(responseBody, 200, "Validating 200 Success Response");

		//Response validation
		Validation.assertEqualsInt(response.extract().body().jsonPath().get("respCode"), 1,"Response code");
		Validation.assertEquals(response.extract().body().jsonPath().get("respMsg"),"Success","Response Message");
	}
}


