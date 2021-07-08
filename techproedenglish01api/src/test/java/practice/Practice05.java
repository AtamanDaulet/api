package practice;

import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import testBaseNtUrls.TestBaseDummy;
import testDataExpected.DummyTestData;

public class Practice05 extends TestBaseDummy{
	/*
    1)Create a spec object in TestBase class for the base url "http://dummy.restapiexample.com/api/v1"
      2)Add path parameter which is "/create" to the base url
    3)By using Map, create a Request Body which has the following data
{
  "name": "Test Data",
      "salary": "8888",
      "age": "33"
}
    When
I send POST Request to http://dummy.restapiexample.com/api/v1/create
Then
Status code is 200
Content Type is "application/json"
      "status" key has value "success"
      "message" key has value "Successfully! Record has been added."
      5)Make assertions by using hard assertion
 */
	@Test
	public void post01() {
		spec.pathParam("createPath", "create");
		
		DummyTestData requestBodyObjest = new DummyTestData();
		Map<String,String> requestBodyMap = requestBodyObjest.postRequestBodySetUpPractice05();
		
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec).						
								body(requestBodyMap).
							when().
								post("/{createPath}");

		response.prettyPrint();
		
		Map<String,String> extraExpectedData = requestBodyObjest.expectedDataPractice05();
		response.
			then().
			assertThat().
			statusCode(requestBodyObjest.statusCode).
			body("status", Matchers.equalTo(extraExpectedData.get("status")),
					"data.name", Matchers.equalTo(requestBodyMap.get("name")),
					"data.salary", Matchers.equalTo(requestBodyMap.get("salary")),
					"data.age", Matchers.equalTo(requestBodyMap.get("age")),
					"message", Matchers.equalTo(extraExpectedData.get("message"))	
					);
		
		
		
	}
	
}
