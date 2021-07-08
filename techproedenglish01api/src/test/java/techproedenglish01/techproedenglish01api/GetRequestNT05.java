package techproedenglish01.techproedenglish01api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.http.ContentType;
public class GetRequestNT05 {
/*
 * 	 When 
	 	I send a GET request to REST API URL http://dummy.restapiexample.com/api/v1/employees   
	    And Accept type is “application/JSON”
	 Then 
	    HTTP Status Code should be 200
	    And Response format should be "application/JSON"
	    And there should be 24 employees
	    And "Ashton Cox" should be one of the employees
	    And 21, 61, and 23 should be among the employee ages
 */
	@Test
	public void get05() {
		String url = "http://dummy.restapiexample.com/api/v1/employees";
		Response response = given().
								accept(ContentType.JSON).
							when().get(url);
		response.prettyPrint();
		response.
			then().
			assertThat().
			statusCode(200).
			contentType(ContentType.JSON).
			body("data.id", hasSize(24), 
				"data.employee_name",hasItem("Ashton Cox"),
				"data.employee_age",hasItems("21","61","23"));
	}
	
	
}
