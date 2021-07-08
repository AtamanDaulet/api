package techproedenglish01.techproedenglish01api;

import static io.restassured.RestAssured.given;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
public class GetRequest02 {
/*
 Negative Scenario
When I send a GET Request to the URL https://restful-booker.herokuapp.com/booking/1001
Then 
HTTP Status code should be 404
And  Status Line should be HTTP/1.1 404 Not Found
 * 
 */
	
	@Test
	public void get02() {
		String url="https://restful-booker.herokuapp.com/booking/1001";
		
		Response response = given().
								accept(ContentType.JSON).  // like accept("application/json"). 
							when().get(url);
		response.prettyPrint();
		response.
			then().
			assertThat().
			statusCode(404).
			statusLine("HTTP/1.1 404 Not Found");
	}
}
