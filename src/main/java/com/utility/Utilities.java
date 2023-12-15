package com.utility;

import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.excel.ExcelWriteData;
import com.business.MLWallet.URI.MLWallet_BaseURL;
import com.business.MLWallet.URI.MLWallet_Endpoints;


import com.propertyfilereader.PropertyFileReader;
import org.json.simple.JSONObject;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import com.google.common.collect.Ordering;
//import com.propertyfilereader.PropertyFileReader;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;


public class Utilities extends ExtentReporter {

	private Utilities() {
	}
	public static String x_client_version="1.2.8";


	/** Time out */
	private static int timeout;

	/** Retry Count */
	private static int retryCount;

	/** Test data property file**/
	public static PropertyFileReader prop = new PropertyFileReader(".//properties//testData.properties");

	// public static ExtentReporter extent = new ExtentReporter();

	@SuppressWarnings("rawtypes")
	public static TouchAction touchAction;

	private static SoftAssert softAssert = new SoftAssert();

	public static boolean relaunch = false;

	public static String CTUserName;

	public static String CTPWD;

	public static String setPlatform = "";

	// logging for extent report
	public static LoggingUtils log = new LoggingUtils();

	/** The Constant logger. */
	// final static Logger logger = Logger.getLogger("rootLogger");
	static LoggingUtils logger = new LoggingUtils();

	/** The Android driver. */
	public AndroidDriver<AndroidElement> androidDriver;

	/** window handler */
	static ArrayList<String> win = new ArrayList<>();

	/** The Android driver. */
//	public IOSDriver<WebElement> iOSDriver;

	public static int getTimeout() {
		return 30;
	}

	/*
	 * public static void setTimeout(int timeout) { this.timeout = timeout; }
	 */

	public static int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

//	static WebDriverWait wait;
//
//	public static JavascriptExecutor js;
//	public static Actions action;
	public static String username = "";
	public static String password = "";


	/**
	 * @return true if keyboard is displayed
	 * @throws IOException
	 */
	public boolean checkKeyboardDisplayed() throws IOException {
		boolean mInputShown = false;
		try {
			String cmd = "adb shell dumpsys input_method | grep mInputShown";
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String outputText = "";
			while ((outputText = br.readLine()) != null) {
				if (!outputText.trim().equals("")) {
					String[] output = outputText.split(" ");
					String[] value = output[output.length - 1].split("=");
					String keyFlag = value[1];
					if (keyFlag.equalsIgnoreCase("True")) {
						mInputShown = true;
					}
				}
			}
			br.close();
			p.waitFor();
		} catch (Exception e) {
			System.out.println(e);
		}
		return mInputShown;
	}


	public static void waitTime(int x) {
		try {
			Thread.sleep(x);
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * Finding the duplicate elements in the list
	 *
	 * @param mono
	 * @param content
	 * @param dosechang
	 * @param enteral
	 */
	public List<String> findDuplicateElements(List<String> mono) {

		List<String> duplicate = new ArrayList<String>();
		Set<String> s = new HashSet<String>();
		try {
			if (mono.size() > 0) {
				for (String content : mono) {
					if (s.add(content) == false) {
						int i = 1;
						duplicate.add(content);
						System.out.println("List of duplicate elements is" + i + content);
						i++;
					}
				}
			}
		} catch (Exception e) {
			// System.out.println(e);
		}
		return duplicate;
	}

	/**
	 * @param contents
	 * @return the list without duplicate elements
	 */
	public List<String> removeDuplicateElements(List<String> contents) {

		LinkedHashSet<String> set = new LinkedHashSet<String>(contents);
		ArrayList<String> listWithoutDuplicateElements = new ArrayList<String>();
		try {

			if (contents.size() > 0) {
				listWithoutDuplicateElements = new ArrayList<String>(set);
			}

		} catch (Exception e) {
			// System.out.println(e);
		}
		return listWithoutDuplicateElements;
	}

	/**
	 * @param sorted
	 * @return true if the list is sorted
	 * @return false if the list is not sorted
	 */
	public boolean checkListIsSorted(List<String> ListToSort) {

		boolean isSorted = false;

		if (ListToSort.size() > 0) {
			try {
				if (Ordering.natural().isOrdered(ListToSort)) {
					ExtentReporter.extentLogger("Check sorting", "List is sorted");
					logger.info("List is sorted");
					isSorted = true;
					return isSorted;
				} else {
					ExtentReporter.extentLogger("Check sorting", ListToSort + " " + "List is not sorted");
					logger.info(ListToSort + "List is notsorted");
					isSorted = false;
				}
			} catch (Exception e) {
				// System.out.println(e);
			}
		} else {
			logger.info("The size of the list is zero");
			ExtentReporter.extentLogger("",
					ListToSort + " " + "There are no elements in the list to check the sort order");
		}
		return isSorted;
	}


	/**
	 * Function for hard sleep
	 *
	 * @param seconds
	 */
	public void sleep(int seconds) {
		try {
			int ms = 1000 * seconds;
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}
	}

	/**
	 * Function to generate random integer of specified maxValue
	 *
	 * @param maxValue
	 * @return
	 */
	public String generateRandomInt(int maxValue) {
		Random rand = new Random();
		int x = rand.nextInt(maxValue);
		String randomInt = Integer.toString(x);
		return randomInt;
	}

	public static String RandomIntegerGenerator(int n) {
		String number = "0123456789";
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			int index = (int) (number.length() * Math.random());

			sb.append(number.charAt(index));
		}
		return sb.toString();
	}

	/**
	 * Function to generate Random String of length 4
	 *
	 * @return
	 */
	public static String generateRandomString(int size) {
		String strNumbers = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder strRandomNumber = new StringBuilder(15);
		strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		String s1 = strRandomNumber.toString().toUpperCase();
		for (int i = 1; i < size; i++) {
			strRandomNumber.append(strNumbers.charAt(rnd.nextInt(strNumbers.length())));
		}
		return s1 + strRandomNumber.toString();
	}

	/**
	 * Function to generate Random characters of specified length
	 *
	 * @param candidateChars
	 * @param length
	 * @return
	 */
	public String generateRandomChars(String candidateChars, int length) {
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(candidateChars.charAt(random.nextInt(candidateChars.length())));
		}
		return sb.toString();
	}

	/**
	 * Function to generate Random Integer between range
	 *
	 * @param maxValue
	 * @param minValue
	 * @return
	 * @throws InterruptedException
	 */
	public String generateRandomIntwithrange(int maxValue, int minValue) throws InterruptedException {
		Thread.sleep(2000);
		Random rand = new Random();
		int x = rand.nextInt(maxValue - minValue) + 1;
		String randomInt = Integer.toString(x);
		System.out.println("Auto generate of number from generic method : " + randomInt);
		return randomInt;
	}

	/**
	 * Function Convert from String to Integer @param(String)
	 */
	public int convertToInt(String string) {
		int i = Integer.parseInt(string);
		return i;
	}

	/**
	 * Function Convert from Integer to String @param(integer)
	 */
	public String convertToString(int i) {
		String string = Integer.toString(i);
		return string;
	}

	public static int timeToSec(String s) {
		String[] t = s.split(":");
		int num = 0;
		System.out.println(t.length);

		if (t.length == 2) {
			num = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]); // minutes since 00:00
		}
		if (t.length == 3) {
			num = ((Integer.parseInt(t[0]) * 60) * 60) + Integer.parseInt(t[1]) * 60 + Integer.parseInt(t[2]);
		}

		return num;
	}

	public static String RandomStringGenerator(int n) {
		{
			String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
			StringBuilder sb = new StringBuilder(n);
			for (int i = 0; i < n; i++) {
				int index = (int) (AlphaNumericString.length() * Math.random());

				sb.append(AlphaNumericString.charAt(index));
			}
			return sb.toString();
		}
	}

	public static String getParameterFromXML(String param) {
		return ExtentReporter.testContext.getCurrentXmlTest().getParameter(param);
	}



	//	// execute query from DB
	//	public static String executeQuery1(String dbTable,int column_no) throws SQLException, ClassNotFoundException {
	//		// Setting the driver
	//		String ref_no = null;
	//		Class.forName("com.mysql.cj.jdbc.Driver");
	//		try {
	//			// Open a connection to the database
	//
	//			java.sql.Connection con = java.sql.DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
	//			java.sql.Statement st = con.createStatement();
	//
	//
	//			java.sql.ResultSet rs = st.executeQuery(dbTable);
	//
	//			System.out.println("=========================================");
	//			while (rs.next()) {
	//				
	//				ref_no = rs.getString(column_no);
	//				System.out.println("Database Value: " + ref_no);
	//			}
	//				System.out.println("=========================================");
	//
	//			con.close();
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		return ref_no;
	//	}


	// execute query from DB
	public static String executeQuery(String dbTable,int column_no) throws SQLException, ClassNotFoundException {
		// Setting the driver
		String ref_no = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		try {
			// Open a connection to the database

			java.sql.Connection con = java.sql.DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
			java.sql.Statement st = con.createStatement();


			java.sql.ResultSet rs = st.executeQuery(dbTable);


			while (rs.next()) {
				System.out.println("=========================================");
				ref_no = rs.getString(column_no);
				System.out.println("Database Value: " + ref_no);
				System.out.println("=========================================");
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ref_no;
	}




	//	public static String deleteQuery() throws SQLException, ClassNotFoundException {
	//
	//
	//		//	Connection conn = null;
	//		//	   Statement stmt = null;
	//		try{
	//			Class.forName("com.mysql.cj.jdbc.Driver");
	//			Connection con = DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
	//			Statement st = con.createStatement();
	//
	//			//		   DELETE FROM db_tradofina.users
	//			//		   WHERE mobile_number=9029698425
	//
	//			String sql = "DELETE FROM tablename WHERE id = idno;";
	//			st.executeUpdate(sql);
	//
	//			st.close();
	//
	//
	//		}
	//		catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		return null;
	//
	//	}



	public static void deleteRow(String query) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
		Statement st = con.createStatement();
		String sql = query ;         
		st.executeUpdate(sql);
		st.close();
	}


	public static Integer updateRow(String query) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection con = DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
		Statement st = con.createStatement();
		String sql = query ;         
		int value=st.executeUpdate(sql);
		st.close();
		return value;
	}




	public void executeUpdate(String updateExecutionQuery , String dbTable) throws ClassNotFoundException, SQLException {


		// Setting the driver
		Class.forName("com.mysql.cj.jdbc.Driver");


		// Open a connection to the database
		java.sql.Connection con = java.sql.DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
		java.sql.Statement st = con.createStatement();


		// Executing the update query
		st.executeUpdate(updateExecutionQuery);


		java.sql.ResultSet rs = st.executeQuery(dbTable);
		System.out.println("=================================================================================================");
		while (rs.next()) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4)
			+ " " + rs.getString(5) + " " + rs.getString(6) + " " + rs.getString(7) + " "
			+ rs.getString(8));
		}
		con.close();
	}


	public static String executeInsert(String dbTable/*String refNo, String eligibilityType, String approved_amount,String offer_id,String repeat_offer_id*/,String message) throws ClassNotFoundException, SQLException {



		// Setting the driver
		Class.forName("com.mysql.cj.jdbc.Driver");



		// Open a connection to the database
		java.sql.Connection con = java.sql.DriverManager.getConnection(prop.getproperty("dbHostUrl"),prop.getproperty("dbUserName"),prop.getproperty("dbPassword"));
		java.sql.Statement st = con.createStatement();



		java.sql.PreparedStatement ps = con.prepareStatement(dbTable /*"Insert into db_tradofina.instaloan_whitelisted_users(user_reference_number,eligible_type,approved_amount,offer_id,repeat_offer_id)VALUES('" +refNo+ "','" +eligibilityType+ "','" +approved_amount+ "','" +offer_id+ "','" +repeat_offer_id+ "')"*/);
		ps.executeUpdate();
		con.close();
		return message;
	}

	public static ValidatableResponse postMethodAPI(HashMap<String, Object> headers, HashMap<String, Object> Myrequestbody,String url) throws IOException {

		ValidatableResponse response = RestAssured.given()
				.baseUri(url)
				.contentType(ContentType.JSON)
				.headers(headers)
				.body(Myrequestbody)
				.when()
				.post()
				.then();
		//				.log().all()

		//		System.out.println("Request Url -->" + url);
		//		ExtentReporter.extentLogger("", "Request Url -->" + url);

		return response;

	}

	public static String getTodaysDate() {
		// Get today's date in the format "yyyy-MM-dd"
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return today.format(formatter);
	}

}