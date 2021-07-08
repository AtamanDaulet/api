package practice;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured. *;
import testBaseNtUrls.TestBaseJsonPlaceHolder;

public class Practice01 extends TestBaseJsonPlaceHolder{
	  /*
	   When
	          I send a GET request to REST API URL https://jsonplaceholder.typicode.com/todos/100
	    Then
	          HTTP Status Code should be 200
	       And "Server" in Headers should be "cloudflare"
	       And response content type is "application/JSON"
	       And "userId" should be 5,
	       And "title" should be "excepturi a et neque qui expedita vel voluptate"
	      And "completed" should be false
	*/
	@Test 
	public void get01() {
		spec.pathParams("todosPath", "todos", "id" , 100);
		
//		set exp data
		
		Response response = given().
					accept(ContentType.JSON).
					spec(spec).
				when().
					get("/{todosPath}/{id}");
		
		response.prettyPrint();
		
		response.then().assertThat().statusCode(200).body("userId", Matchers.equalTo(5),
				"userId", Matchers.equalTo(5),
				"id", Matchers.equalTo(100),
				"title", Matchers.equalTo("excepturi a et neque qui expedita vel voluptate"),
				"completed", Matchers.equalTo(false)
				);
		
		
		
		
	}


}
