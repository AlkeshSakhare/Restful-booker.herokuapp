package tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestfulbookerTest {
	RequestSpecification reqspec;
	Response response;
	String token,bookingid;
	 

	@BeforeTest
	public void setUp() {
		reqspec = RestAssured.given();
		reqspec.baseUri("https://restful-booker.herokuapp.com");
		reqspec.basePath("auth");
		reqspec.contentType(ContentType.JSON);
		reqspec.header("Accept", "application/json");
	}

	@Test(priority = 1)
	public void CreateTokenTest() {
		
	  response = given()
				 .spec(reqspec)
				 .body("{\r\n" + 
				 		"    \"username\" : \"admin\",\r\n" + 
				 		"    \"password\" : \"password123\"\r\n" + 
				 		"}")
				 .log()
				 .all()
			.when()
				.post();
		token = response.jsonPath().getString("token");
		response.then().log().all();
}
	@Test(priority = 2)
	public void GetBookingTest() {
		
		given()
			 .spec(reqspec)
			 .basePath("booking")
			 .log()
			 .all()
		.when()
			.get()
		.then()
			.log()
			.all();
	}
	
	
	@Test(priority = 3)
	public void CreateBookingTest() {
		
		response=given()
			 .spec(reqspec)
			 .basePath("booking")
			 .header("Authorization",token)
			 .body("{\r\n" + 
			 		"    \"firstname\" : \"Jim\",\r\n" + 
			 		"    \"lastname\" : \"Brown\",\r\n" + 
			 		"    \"totalprice\" : 111,\r\n" + 
			 		"    \"depositpaid\" : true,\r\n" + 
			 		"    \"bookingdates\" : {\r\n" + 
			 		"        \"checkin\" : \"2018-01-01\",\r\n" + 
			 		"        \"checkout\" : \"2019-01-01\"\r\n" + 
			 		"    },\r\n" + 
			 		"    \"additionalneeds\" : \"Breakfast\"\r\n" + 
			 		"}")
			 .log()
			 .all()
		.when()
			.post();
		bookingid = response.jsonPath().get("bookingid") + "";
		response.then().log().all().statusCode(200);

	}
	
	@Test(priority = 4)
	public void GetBookingIdTest() {
		
		given()
			 .spec(reqspec)
			 .basePath("booking")
			 .basePath(bookingid)
			 .log()
			 .all()
		.when()
			.get()
		.then()
			.log()
				.all().statusCode(200);

	}

	@Test(priority = 5)
	public void UpdateBookingTest() {
		
			given().spec(reqspec)
			.header("Authorization", token)
			.basePath("booking")
			.basePath(bookingid)
			.body("{\r\n" + "    \"firstname\" : \"Jim\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
							+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
							+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
							+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
							+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
			.log()
			.all()
			.when()
			.put()
			.then()
			.log()
			.all()
			.statusCode(201);
	}

	@Test(priority = 6)
	public void PartialUpdateBookingTest() {
		
		given()
			 .spec(reqspec)
			 .header("Authorization",token)
			 .basePath("booking")
			 .basePath(bookingid)
			 .body("{\r\n" + 
			 		"    \"firstname\" : \"James\",\r\n" + 
			 		"    \"lastname\" : \"Brown\"\r\n" + 
			 		"}")
			 .log()
			 .all()
		.when()
			.patch()
		.then()
			.log()
				.all().statusCode(200);
	}
	
	@Test(priority = 7)
	public void DeleteBookingTest() {
		
			given()
				 .spec(reqspec)
				 .basePath("booking")
				 .basePath(bookingid)
				 .header("Authorization",token)
				 .log()
				 .all()
			.when()
				.delete()
			.then()
				.log()
				.all()
				.statusCode(200);
	}
	
}
