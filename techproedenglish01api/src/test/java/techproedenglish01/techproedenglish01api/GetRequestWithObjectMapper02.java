package techproedenglish01.techproedenglish01api;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseHerOkuApp;
import utilities.JsonUtil;

public class GetRequestWithObjectMapper02 extends TestBaseHerOkuApp {
	/*
	 * When I send GET Request to the URL
	 * https://restful-booker.herokuapp.com/booking/2
	 * 
	 * Then Status code is 200 And response body is like { "firstname": "Mark",
	 * "lastname": "Ericsson", "totalprice": 726, "depositpaid": true,
	 * "bookingdates": { "checkin": "2015-08-07", "checkout": "2020-10-25" }
	 * 
	 * }
	 */

	@Test
	public void get02() {
		spec.pathParams("bookingPath", "booking", "idPath", 2);

//2
		String expectedJson = "{\n" + "\"firstname\": \"Sally\",\n" + "\"lastname\": \"Jones\",\n"
				+ "\"totalprice\": 725,\n" + "\"depositpaid\": false,\n" + "\"bookingdates\": {\n"
				+ "\"checkin\": \"2019-08-04\",\n" + "\"checkout\": \"2020-09-25\"" + "}\n" + "}";

		Map<String, Object> expectedMap = JsonUtil.convertJsonToJava(expectedJson, Map.class);
		System.out.println(expectedMap);

		Response response = given().contentType(ContentType.JSON).spec(spec).when().get("/{bookingPath}/{idPath}");

//		response.prettyPrint();

		Map<String, Object> actualMap = JsonUtil.convertJsonToJava(response.asString(), Map.class);

		SoftAssert softAssert = new SoftAssert();
		
//		softAssert.assertEquals(actualMap.get("firstname"), expectedMap.get("firstname"));
//		softAssert.assertEquals(actualMap.get("lastname"), expectedMap.get("lastname"));
//		softAssert.assertEquals(actualMap.get("totalprice"), expectedMap.get("totalprice"));
//		softAssert.assertEquals(actualMap.get("depositpaid"), expectedMap.get("depositpaid"));
//		softAssert.assertEquals(((Map)actualMap.get("bookingdates")).get("checkin"), ((Map)expectedMap.get("bookingdates")).get("checkin"));
//		softAssert.assertEquals(((Map)actualMap.get("bookingdates")).get("checkout"), ((Map)expectedMap.get("bookingdates")).get("checkout"));

		softAssert.assertAll();
		
		// To practice Serialization Method convert expectedMap and actualMap to Json Data
		String JsonFromExpectedMap = JsonUtil.convertJavaToJson(expectedMap);
		System.out.println(JsonFromExpectedMap);
		
		String JsonFromActualMap = JsonUtil.convertJavaToJson(actualMap);
		System.out.println(JsonFromActualMap);
	}
}
