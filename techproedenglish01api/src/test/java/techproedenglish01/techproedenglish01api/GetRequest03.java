package techproedenglish01.techproedenglish01api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.testng.asserts.SoftAssert; //---
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
public class GetRequest03 {
/*
 *When I send a GET request to REST API URL 
		https://restful-booker.herokuapp.com/booking/1001   
	Then HTTP Status Code should be 404
		HTTP Status code should be 404
	And  Status Line should be HTTP/1.1 404 Not Found
	And response body contains "Not Found"
	And response body does not contain "TechProEd" 
 */
	
	@Test
	public void get03() {
		String url="https://restful-booker.herokuapp.com/booking/1001";
		
		Response response = given().
								accept(ContentType.JSON).
							when().get(url);
		response.prettyPrint();
		
		//  1 way. Hard assertion
		response.
			then().
			assertThat().
			statusCode(404).
			statusLine("HTTP/1.1 404 Not Found");
		
	/*  2 way. Hard assertion ==> 3 methods:
										1. assertEquals(Expected, Actual)
										2. assertTrue(boolean)
										3. assertFalse(boolean)										
	*/	
		// 2 way
		assertEquals(404,response.getStatusCode());
		assertEquals("HTTP/1.1 404 Not Found",response.getStatusLine());
		assertTrue(response.asString().contains("Not Found"));
		assertFalse(response.asString().contains("TechProEd"));
		
		/* 3 way: Soft Assertion ==> There are 3 steps in Soft Assertion
				1. Create SoftAssert class object ==> SoftAssert 
				   softAssert = new SoftAssert();
				2. Type assertions by using assertEqual(), assertTrue(),and assertFalse(boolean)		
			***3. Use assertAll(), if you forget to use assertAll(), you will get passed 
					but it is not meaningful. ==> 	softAssert.assertAll();					
									*/
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(response.getStatusCode(), 404);
		softAssert.assertEquals(response.getStatusLine(), "HTTP/1.1 404 Not Found");
		
		softAssert.assertTrue(response.asString().contains("Not Found"));
		softAssert.assertFalse(response.asString().contains("TechProEd"));

		softAssert.assertAll();
		
		
	}
	
}
