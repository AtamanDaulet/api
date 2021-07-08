package practice;

import java.util.Map;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import testBaseNtUrls.TestBaseDummy;
import testDataExpected.DummyTestData;

public class Practice06 extends TestBaseDummy{
	/*
    When
      I send a POST Request to the Url http://dummy.restapiexample.com/api/v1/create
      by using the following Request Body {
                                     "name":"Micheal JACKSON",
                                     "salary":"10000",
                                     "age":"55",
                                     "profile_image": ""
                                 }
    Then
      Status code is 200
      And response body should be like {
                                  "status": "success",
                                  "data": {
                                    "name":"Micheal JACKSON",
                                     "salary":"10000",
                                     "age":"55",
                                      "profile_image": null
                                  },
                                  "message": "Successfully! Record has been added."
                               }
*/
	
	@Test
	public void post01() {
		spec.pathParams("createPath", "create");
		
		DummyTestData requestBodyObjest = new DummyTestData();
		Map<String, String> requestBody = requestBodyObjest.requestDataPractice06();
		
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec).
								body(requestBody).
							when().
								post("/{createPath}");
		
		response.prettyPrint();
		
		JsonPath actualJson = response.jsonPath();
		Map<String,String> extraExpectedData = requestBodyObjest.expectedDataPractice05();
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(actualJson.getString("status"), extraExpectedData.get("status"));
		softAssert.assertEquals(actualJson.getString("message"), extraExpectedData.get("message"));
	
		softAssert.assertEquals(String.valueOf(actualJson.get("data.profile_image")+""),
				extraExpectedData.get("profile_image"));
		softAssert.assertEquals(actualJson.getString("data.name"),
				requestBody.get("name"));
		softAssert.assertEquals(actualJson.getString("data.salary"),
				requestBody.get("salary"));
		softAssert.assertEquals(actualJson.getString("data.age"),
				requestBody.get("age"));
		
		softAssert.assertAll();
	}
	
	
}
