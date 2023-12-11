package com.business.APIResponse;

import com.business.MLWallet.URI.MLWallet_BaseURL;
import com.business.MLWallet.URI.MLWallet_Endpoints;
import com.utility.ExtentReporter;
import com.utility.LoggingUtils;
import com.utility.Utilities;
import io.restassured.response.ValidatableResponse;

import java.util.HashMap;

import static com.excel.ExcelWriteData.DemoExcel;
import static com.utility.Utilities.prop;

public class CashOutToBank {
    static LoggingUtils logger = new LoggingUtils();

    public static ValidatableResponse cashOutToBank(Object[][] data){

        try{
            String url = MLWallet_BaseURL.adminBaseURL.concat(MLWallet_Endpoints.cashOutToBankEndPoint);
            logger.info("Url :" + url);

            ExtentReporter.extentLogger("url", url);
            String transactionId = Utilities.generateRandomString(15);
            System.out.println(transactionId);
            String path = System.getProperty("user.dir");
            String filePath;
            filePath = path + "//src//main//java//com//Datasheet//CashInFromBank.xlsx";

            DemoExcel(filePath,"CashoutToBank",transactionId,1,2);

            HashMap<String,Object> req_body = new HashMap<>();
            req_body.put("id", (String) data[0][0]);
            req_body.put("transactionId", (String) data[0][1]);
            req_body.put("walletNo", (String) data[0][2]);
            req_body.put("walletUsr", (String) data[0][3]);
            req_body.put("firstName", (String) data[0][4]);
            req_body.put("middleName", (String) data[0][5]);
            req_body.put("lastName", (String) data[0][6]);
            req_body.put("phoneNo", (String) data[0][7]);
            req_body.put("procId", (String) data[0][8]);
            req_body.put("bankAccount", (String) data[0][9]);
            req_body.put("shortName", (String) data[0][10]);
            req_body.put("longName", (String) data[0][11]);
//            req_body.put("runDate", (String) data[0][12]);
            req_body.put("runDate", "2023-12-11");
            req_body.put("amount", (String) data[0][13]);
            req_body.put("currency", (String) data[0][14]);
            req_body.put("email", (String) data[0][15]);
            req_body.put("description", (String) data[0][16]);
            req_body.put("addressLine1", (String) data[0][17]);
            req_body.put("city", (String) data[0][18]);
            req_body.put("province", (String) data[0][19]);
            req_body.put("nationality", (String) data[0][20]);
            req_body.put("accessKey", (String) data[0][21]);

            String req=String.valueOf(req_body);
            ExtentReporter.extentLogger("req_body", "Request :"+req);

            HashMap<String, Object> headers = new HashMap<>();
            headers.put("Authorization", "Bearer " + prop.getproperty("AccessToken"));

            ValidatableResponse response = Utilities.postMethodAPI(headers, req_body, url);

            String Resp = response.extract().body().asString();
            logger.info("Response Body= " + Resp);
            ExtentReporter.extentLogger(" ", "Response Body= " + Resp);

            return response;
        }catch(Exception e){

        }

        return null;
    }
}
