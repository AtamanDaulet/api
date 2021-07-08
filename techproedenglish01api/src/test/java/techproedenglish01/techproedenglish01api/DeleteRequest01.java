package techproedenglish01.techproedenglish01api;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import testBaseNtUrls.TestBaseDummy;
import testDataExpected.DummyTestData;

public class DeleteRequest01 extends  TestBaseDummy{
/*
 *         When
            I send DELETE Request to the Url http://dummy.restapiexample.com/api/v1/delete/2            
        Then 
            Status code is 200
            And Response body is {
                                    "status": "success",
                                    "data": "2",
                                    "message": "Successfully! Record has been deleted"
                                 }  
 */
	
	@Test
	public void delete01() {
		spec.pathParams("deletePath", "delete",
						"id", 2);
		
		DummyTestData expectedObj = new DummyTestData();
		Map<String, Object> expectedData = expectedObj.setUpExpDeleteDataByUsingMap();
		
		Response response = given().
								accept(ContentType.JSON).
								spec(spec).
							when().
								delete("/{deletePath}/{id}");
		response.prettyPrint();
		
		//1 way body
		response.
			then().
			assertThat().
			statusCode(expectedObj.statusCode).
			body("status", equalTo(expectedData.get("status")),
					"data", equalTo(expectedData.get("data")),
					"message", equalTo(expectedData.get("message"))				
				);
		
		//2 way Json + expData
		JsonPath json = response.jsonPath();
		
		assertEquals(expectedData.get("status"), json.getString("status"));
		assertEquals(expectedData.get("data"), json.getString("data"));
		assertEquals(expectedData.get("message"), json.getString("message"));
		assertEquals(expectedData.get("statusCode"), response.getStatusCode());
		
		//3 way GSON + expData +SoftAssert
		
		Map<String, String> actualData = response.as(HashMap.class);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(actualData.get("status"), expectedData.get("status"));
		softAssert.assertEquals(actualData.get("data"), expectedData.get("data"));
		softAssert.assertEquals(actualData.get("message"), expectedData.get("message"));
		
		softAssert.assertAll();
		
		
		
		
	}
}
