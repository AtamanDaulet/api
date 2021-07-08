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

public class Practice04 extends TestBaseHerOkuApp{
	/*
    When
        I send GET Request to https://restful-booker.herokuapp.com/booking/1
    Then
        Response body should be like that;
        {
           "firstname": "Eric",
           "lastname": "Smith",
           "totalprice": 555,
           "depositpaid": false,
           "bookingdates": {
               "checkin": "2016-09-09",
               "checkout": "2017-09-21"
            }
       }
*/
	@Test 
	public void get01() {
		spec.pathParams("bookingPath", "booking", "id" , 1);
		
		HerOkuAppTestData expObject = new HerOkuAppTestData();
		JSONObject expData = expObject.setUpDataJSONObjectPractice03Mary();
		
		Response response = given().
//					accept(ContentType.JSON).
					spec(spec).
				when().
					get("/{bookingPath}/{id}");
		
		response.prettyPrint();
		
		response.then().assertThat().statusCode(200).contentType(ContentType.JSON).
			body("firstname", Matchers.equalTo("Mary"),
					"lastname", Matchers.equalTo("Jones"),
					"totalprice", Matchers.equalTo(438),
					"depositpaid", Matchers.equalTo(false),
					"bookingdates.checkin", Matchers.equalTo("2016-03-18"),
					"bookingdates.checkout", Matchers.equalTo("2018-09-01")
					);
	
		JsonPath actuaiJson = response.jsonPath();
		
		assertEquals("They are not equal", expData.getString("firstname"), actuaiJson.getString("firstname"));
		assertEquals("They are not equal", expData.getString( "lastname"), actuaiJson.getString("lastname"));
		assertEquals("They are not equal", expData.getInt( "totalprice"), actuaiJson.getInt( "totalprice"));
		assertEquals("They are not equal", expData.getBoolean( "depositpaid"), actuaiJson.getBoolean( "depositpaid"));
		assertEquals("They are not equal", expData.getJSONObject("bookingdates").getString("checkin"),
				actuaiJson.getString("bookingdates.checkin"));
		assertEquals("They are not equal", expData.getJSONObject("bookingdates").getString("checkout"),
				actuaiJson.getString("bookingdates.checkout"));
	}
}
