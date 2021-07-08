package techproedenglish01.techproedenglish01api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseHerOkuApp;
import testDataExpected.HerOkuAppTestData;



public class PostRequest02 extends TestBaseHerOkuApp {
/*
 When
I send POST Request to the Url https://restful-booker.herokuapp.com/booking
with the request body 
		{
		"firstname": "Selim",
		"lastname": "Ak",
		"totalprice": 11111,
		"depositpaid": true,
		"bookingdates": {
			"checkin": "2020-09-09",
			"checkout": "2020-09-21"
			}
		}
		Then
		Status code is 200
		And response body should be like 
		{
		"bookingid": 11,
		"booking": {
			"firstname": "Selim",
			"lastname": "Ak",
			"totalprice": 11111,
			"depositpaid": true,
			"bookingdates": {
				"checkin": "2020-09-09",
				"checkout": "2020-09-21"
						}
					}
		}   

 */
	
	@Test
	public void post02()  {
		//1) Set the URL
		spec.pathParam("bookingPath", "booking");
		
		//2) Set the expected data
		
		HerOkuAppTestData reqBodyObj = new HerOkuAppTestData();
		JSONObject expBodyMap = reqBodyObj.setUpDataJSONObjectPostRequest();
		
		//3) Send the request
		Response response = given().
				contentType(ContentType.JSON).
				spec(spec).
//				auth().
//				basic("admin", "password123").
				body(expBodyMap.toString()).
			when().
				post("/{bookingPath}");
		
//		response.prettyPrint();
		
		response.then().assertThat().statusCode(200).
		body("booking.firstname", Matchers.equalTo(expBodyMap.getString("firstname")),
				"booking.lastname", Matchers.equalTo(expBodyMap.getString("lastname")),
				"booking.totalprice", Matchers.equalTo(expBodyMap.getInt("totalprice")),
				"booking.depositpaid", Matchers.equalTo(expBodyMap.getBoolean("depositpaid")),
				"booking.bookingdates.checkin", 
					Matchers.equalTo(expBodyMap.getJSONObject("bookingdates").getString("checkin")),
				"booking.bookingdates.checkout", 
					Matchers.equalTo(expBodyMap.getJSONObject("bookingdates").getString("checkout"))	
				);
		// 2 way
		JsonPath json = response.jsonPath();
		
		assertEquals(expBodyMap.getString("firstname"), json.getString("booking.firstname"));
		assertEquals(expBodyMap.getString("lastname"), json.getString("booking.lastname"));
		assertEquals(expBodyMap.getInt("totalprice"), json.getInt("booking.totalprice"));
		assertEquals(expBodyMap.getBoolean("depositpaid"), json.getBoolean("booking.depositpaid"));
		assertEquals(expBodyMap.getJSONObject("bookingdates").getString("checkin"),
				json.getString("booking.bookingdates.checkin"));
		assertEquals(expBodyMap.getJSONObject("bookingdates").getString("checkout"),
				json.getString("booking.bookingdates.checkout"));
		//3 way
		
		Map<String, Object> actualDataJSONObject = response.as(HashMap.class);
		
		System.out.println(actualDataJSONObject);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(((Map)actualDataJSONObject.get("booking")).get("firstname"), 
				expBodyMap.getString("firstname"));
		softAssert.assertEquals(((Map)actualDataJSONObject.get("booking")).get("lastname"), 
				expBodyMap.getString("lastname"));
		softAssert.assertEquals(((Map)actualDataJSONObject.get("booking")).get("totalprice"), 
				expBodyMap.getDouble("totalprice"));
		softAssert.assertEquals(((Map)actualDataJSONObject.get("booking")).get("depositpaid"), 
				expBodyMap.getBoolean("depositpaid"));
		
		softAssert.assertEquals(((Map)((Map)actualDataJSONObject.get("booking")).get("bookingdates")).get("checkin"),
				expBodyMap.getJSONObject("bookingdates").getString("checkin"));

		softAssert.assertEquals(((Map)((Map)actualDataJSONObject.get("booking")).get("bookingdates")).get("checkout"),
				expBodyMap.getJSONObject("bookingdates").getString("checkout"));

		softAssert.assertAll();
		
		
	}
}
