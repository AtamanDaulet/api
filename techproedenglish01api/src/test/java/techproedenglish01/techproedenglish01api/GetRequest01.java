package techproedenglish01.techproedenglish01api;

import org.junit.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
public class GetRequest01 {
/*
 * Gherkin language is keyword  Given When Then And
 * 
 * Given: It declares prerequest.
 * When: It defines the action which user will perform
 * Then: It is output
 * And: In any part if you have multiple things use "And" between them
 * 
	Positive Scenario
	Given 
		I got UPL
	When 
		I send a GET Request to the URL https://restful-booker.herokuapp.com/booking/3
	Then 
		HTTP Status code should be 200
	And 
	 	Content type should be Json
	And 
		Status Line should be HTTP/1.1 200 OK
	*/
	
	@Test
	public void get01() {
		// 1. Set the URL
		String url = "https://restful-booker.herokuapp.com/booking/3";
		
		// 2. Set the expected data(In Get request, we will learn it later)
		
		// 3. Send the request(Like clicking on "Send" button on Postman)
		Response response = given().
								accept("application/json"). 
			//If you use accept("application/json"). it means you want the response in Json Format.
							when().
								get(url); 
		response.prettyPrint();
		// 4. Assert The things which are given in the test case(Like checking status code, 
		//    response body etc. on Postman)
		response.        // This is HARD assertion code
			then().             
			assertThat().
			statusCode(200).
			contentType("application/json").
			statusLine("HTTP/1.1 200 OK");
		//NOTE: If execution stops after first error, it is called "Hard Assertion"
		//NOTE: If execution does not stops for any error, it is called "Soft Assertion"
		// Soft Assertion - all assertion code will be executed then you will get report
		// for all errors
		
		//How to print content-type, status code, status line, etc on the console
		System.out.println(response.getContentType());
		System.out.println(response.getStatusLine());
		System.out.println(response.getStatusCode());
		System.out.println(response.getTime());
		System.out.println(response.getHeaders());
		
	}
	
}
