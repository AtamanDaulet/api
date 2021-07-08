package techproedenglish01.techproedenglish01api;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseHerOkuApp;
import static io.restassured.RestAssured.*;
public class GetRequestNT08 extends TestBaseHerOkuApp{
/*
 When 
            I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/5 
      Then 
          HTTP Status Code should be 200
          And response content type is “application/JSON” 
          And response body should be like; 
          { "firstname": "Sally", 
            "lastname": "Ericsson", 
            "totalprice": 111,
            "depositpaid": false, 
            "bookingdates": { "checkin": "2017-05-23", 
                              "checkout":"2019-07-02" }
          }
 */
	@Test
	public void get08() {
		spec.pathParams("bookingPath", "booking", 
							"id", 5);
		Response response = given().
//								accept(ContentType.JSON).
								spec(spec).
							when().
								get("/{bookingPath}/{id}");
		response.prettyPrint();
//		response.
//			then().
//			assertThat().
//			statusCode(200).
	//		contentType(ContentType.JSON).
//			body("firstname", equalTo("Jim"), 
//				"lastname", equalTo("Wilson"),
//				"totalprice", equalTo(442),
//				"depositpaid", equalTo(true),
//				"bookingdates.checkin", equalTo("2016-04-07"),
//				"bookingdates.checkout", equalTo("2019-01-12"),
//				"additionalneeds", equalTo("Breakfast")
//				);
		
		// 2 way: Use JsonPath Class: We use JsonPath Class to navigate in Json Data 
		JsonPath json = response.jsonPath();
		// Hard assertion by using JsonPath Class
		assertEquals("First name is not matching!!!","Eric", json.getString("firstname"));
//		assertEquals("Ericsson", json.getString("lastname"));
//		assertEquals(633, json.getInt("totalprice"));
//		assertEquals(false, json.getBoolean("depositpaid"));
//		assertEquals("2016-06-07", json.getString("bookingdates.checkin"));
//		assertEquals("2016-07-27", json.getString("bookingdates.checkout"));
		
		//SoftAssert
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(json.getString("firstname"), "Eric");
		softAssert.assertEquals(json.getString("lastname"), "Ericsson");
		softAssert.assertEquals( json.getInt("totalprice"), 635, "Total price did not match!");
		softAssert.assertEquals( json.getBoolean("depositpaid"), false);
		softAssert.assertEquals( json.getString("bookingdates.checkin"),"2016-06-07");
		softAssert.assertEquals(json.getString("bookingdates.checkout"), "2016-07-27" );
		
		softAssert.assertAll();
		
	}
}
