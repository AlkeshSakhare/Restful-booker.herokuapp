package tests;

import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utility.ExcelUtils;

public class CreateTokenTest {
	PrintStream log = null;
	@Test(dataProvider = "getCredentials")
	public void createTokenTest(String username, String password) {

		if (username.equals("empty")) {
			username = "";
		}
		if (password.equals("empty")) {
			password = "";
		}
		if (username.equals("Null")) {
			username = null;
		}
		if (password.equals("Null")) {
			password = null;
		}
		HashMap<String, String> body = new LinkedHashMap<String, String>();
		body.put("username", username);
		body.put("password", password);
		Response res = RestAssured.
				given()
				.filter(RequestLoggingFilter.logRequestTo(log))
				.filter(ResponseLoggingFilter.logResponseTo(log)).log().all()
				.baseUri("https://restful-booker.herokuapp.com")
				.basePath("auth")
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(body)
			.when()
				.post()
			.then()
				.log()
				.all()
				.extract()
				.response();
		Assert.assertTrue(res.getStatusCode() == 200);

		res.then().assertThat().body(containsString("token"));
	}

	@DataProvider
	public Object[][] getCredentials() {

		Object[][] data = ExcelUtils.getTestData("Sheet1");
		return data;
	}

	@BeforeClass
	public void setUp() {
		try {
			log = new PrintStream(new FileOutputStream("logging.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
