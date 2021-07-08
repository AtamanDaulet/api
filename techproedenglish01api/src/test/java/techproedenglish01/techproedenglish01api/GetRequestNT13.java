package techproedenglish01.techproedenglish01api;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import testBaseNtUrls.TestBaseHerOkuApp;
import testDataExpected.HerOkuAppTestData;

public class GetRequestNT13 extends TestBaseHerOkuApp{
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
	public void get13() {
		spec.pathParams("booking" , "booking",
						"id", 10);
		
		HerOkuAppTestData expectedObj = new HerOkuAppTestData();
		Map<String, Object> expectedObjMap = expectedObj.setUpData();
		
		Response response = given().
//								accept(ContentType.JSON).
								spec(spec).
							when().
								get("/{booking}/{id}");
		
		response.prettyPrint();
		
		Map<String, Object> actualDataMap = response.as(HashMap.class);
		
//		assertEquals(expectedObjMap.get("firstname"), actualDataMap.get("firstname"));
//		assertEquals(expectedObjMap.get("lastname"), actualDataMap.get("lastname"));
//		assertEquals(expectedObjMap.get("totalprice"), actualDataMap.get("totalprice"));
//		assertEquals(expectedObjMap.get("depositpaid"), actualDataMap.get("depositpaid"));
		assertEquals(((Map)expectedObjMap.get("bookingdates")).get("checkin"), 
				((Map)actualDataMap.get("bookingdates")).get("checkin"));
		assertEquals(((Map)expectedObjMap.get("bookingdates")).get("checkout"), 
				((Map)actualDataMap.get("bookingdates")).get("checkout"));
		
		
	}
	
	
	
}
