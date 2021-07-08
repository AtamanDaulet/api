package techproedenglish01.techproedenglish01api;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
public class GetRequestNT04 {
/*
 *      When 
        I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking/1   
        And Accept type is “application/JSON”
     Then 
        HTTP Status Code should be 200
        And Response format should be "application/JSON"
        And first name should be "Susan"
        And last name should be "Brown"
        And checkin date should be "2015-02-16"
        And checkout date should be "2017-06-20"
 */
	@Test
	public void get04() {
		//1) Set the URL
//		String url = "https://restful-booker.herokuapp.com/booking/1";
		String url =  "https://jsonplaceholder.typicode.com/todos/55";
		//2) Set the expected data (We will learn it later)
		Response response = //given().
								//accept(ContentType.JSON).
							when().
								get(url);
		//3) Send the request
		response.prettyPrint();
		//4) Assert the things which are given in the test case
//		1.Way
//			1. 1 way of 1 way
//			response.
//				then().
//				assertThat().
//				statusCode(200).
//				contentType(ContentType.JSON).
//						body("title", Matchers.
//						equalTo("voluptatum omnis minima qui occaecati provident nulla voluptatem ratione")).		
//						body("completed", Matchers.equalTo(true)).
//						body("userId", Matchers.equalTo(3));
//			2. 2 way of 1 way  recommended
			response.
			then().
			assertThat().
			statusCode(200).
			contentType(ContentType.JSON).
				body("title", 
				equalTo("voluptatum omnis minima qui occaecati provident nulla voluptatem ratione"),		
				"completed", equalTo(true),
				"userId", equalTo(3));  //3
			
//			2.Way
			//In that test case, using this way is not good because your assertions are not specific
			//You are just checking if a large String containing a word, but in the 2.Way of 1.Way 
			//we are asserting everything with details like completed is false, user id is 2 etc
			assertEquals(200,  response.getStatusCode());
			assertEquals("application/json; charset=utf-8", response.getContentType());
			assertTrue(response.asString().contains("voluptatum omnis minima qui occaecati provident nulla voluptatem ratione"));
			assertTrue(response.asString().contains("true"));
			assertTrue(response.asString().contains("3"));
	
	// 3 Way
			SoftAssert softAssert = new SoftAssert();
			
			softAssert.assertEquals(response.getStatusCode(), 200);
			softAssert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
			softAssert.assertTrue(response.asString().contains("voluptatum omnis minima qui occaecati provident nulla voluptatem ratione"));
			softAssert.assertTrue(response.asString().contains("true"));
			softAssert.assertTrue(response.asString().contains("3"));
			softAssert.assertAll();
	
	
	}
}
