package techproedenglish01.techproedenglish01api;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseHerOkuApp;
import static io.restassured.RestAssured.*;
public class GetRequestNT07 extends TestBaseHerOkuApp{
/*
 * When
  		I send a GET request to REST API URL https://restful-booker.herokuapp.com/booking
  	Then
  		Among the data there should be someone whose first name is "Jim" and last  name is "Ericsson"
 */
	@Test
	public void get07() {
		spec.pathParam("bookingPath", "booking").
			queryParams("firstname", "Susan", 
						"lastname", "Brown");
	    
		Response response = given().
								accept(ContentType.JSON).
								spec(spec).
							when().
								get("/{bookingPath}");
		response.prettyPrint();
		
		response.
			then().
				assertThat().
				statusCode(200);
		assertTrue(response.asString().contains("bookingid"));
		
	}
	
	
}
