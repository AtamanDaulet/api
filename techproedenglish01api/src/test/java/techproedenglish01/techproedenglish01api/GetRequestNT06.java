package techproedenglish01.techproedenglish01api;

import static org.hamcrest.Matchers.*;
import org.junit.Test;
import testBaseNtUrls.TestBaseJsonPlaceHolder;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
public class GetRequestNT06 extends TestBaseJsonPlaceHolder{
/*
 * When 
I send a GET request to REST API URL https://jsonplaceholder.typicode.com/todos/123
Then 
HTTP Status Code should be 200
And "Server" in Headers should be "cloudflare"
And response content type is “application/JSON”
And "userId" should be 7,
And "title" should be "esse et quis iste est earum aut impedit"
And "completed" should be false
 */
	
	@Test
	public void get06() {
		spec.pathParams("firstParam", "todos", 
						"id" , 123);
		Response response = given().
								accept(ContentType.JSON).
								spec(spec).
							when().
								get("/{firstParam}/{id}");
		response.prettyPrint();
		
		response.
		then().
		assertThat().
		statusCode(200).
		header("Server", "cloudflare").
		contentType(ContentType.JSON).
		body("userId", equalTo(7),
			 "title", equalTo("esse et quis iste est earum aut impedit"),
			 "completed", equalTo(false));
	}
}
