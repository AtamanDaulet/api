package practice;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseHerOkuApp;
import testDataExpected.HerOkuAppTestData;

public class Practice02 extends TestBaseHerOkuApp{
	 /*
	    * When I send a GET request to REST API URL
	    * 	https://restful-booker.herokuapp.com/booking/7
	    * Then HTTP Status Code should be 200
	    * And response content type is "application/JSON"
	    * And response body should be like;
	    * {
	{
	    "firstname": "Mary",
	    "lastname": "Brown",
	    "totalprice": 138,
	    "depositpaid": true,
	    "bookingdates": {
	        "checkin": "2019-07-18",
	        "checkout": "2020-11-19"
	    },
	    "additionalneeds": "Breakfast"
	}
	    */
	@Test 
	public void get01() {
		spec.pathParams("bookingPath", "booking", "id" , 7);
		
		HerOkuAppTestData expObject = new HerOkuAppTestData();
		JSONObject expData = expObject.setUpDataJSONObjectPractice02();
		
		Response response = given().
//					accept(ContentType.JSON).
					spec(spec).
				when().
					get("/{bookingPath}/{id}");
		
		response.prettyPrint();
		
		response.
			then().
			assertThat().
			statusCode(200).
			body("firstname", Matchers.equalTo(expData.getString( "firstname")),
				"lastname", Matchers.equalTo(expData.getString( "lastname")),
				"totalprice", Matchers.equalTo(expData.getInt( "totalprice")),
				"depositpaid", Matchers.equalTo(expData.getBoolean( "depositpaid")),
				"bookingdates.checkin", 
					Matchers.equalTo(expData.getJSONObject("bookingdates").getString("checkin")),
				"bookingdates.checkout", 
					Matchers.equalTo(expData.getJSONObject("bookingdates").getString("checkout"))
//				"additionalneeds", Matchers.equalTo(expData.getString("additionalneeds"))

				);
		
		JsonPath json = response.jsonPath();
		
		assertEquals("They are not equal", expData.getString( "firstname"), json.getString("firstname"));
		assertEquals("They are not equal", expData.getString( "lastname"), json.getString("lastname"));
		assertEquals("They are not equal", expData.getInt( "totalprice"), json.getInt( "totalprice"));
		assertEquals("They are not equal", expData.getBoolean( "depositpaid"), json.getBoolean( "depositpaid"));
		assertEquals("They are not equal", expData.getJSONObject("bookingdates").getString("checkin"),
				json.getString("bookingdates.checkin"));
		assertEquals("They are not equal", expData.getJSONObject("bookingdates").getString("checkout"),
				json.getString("bookingdates.checkout"));
		assertEquals("They are not equal", expData.getString( "additionalneeds"), json.getString("additionalneeds"));
		
		
	}		
}
