package techproedenglish01.techproedenglish01api;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseJsonPlaceHolder;
import testDataExpected.JsonPlaceHolderTestData;

public class PatchRequest01 extends TestBaseJsonPlaceHolder{
	/*
	  When
            I send PATCH Request to the Url https://jsonplaceholder.typicode.com/todos/198
            with the PUT Request body like {
                                            "title": "Tidy your room",
                                           }
       Then 
           Status code is 200
           And response body is like  {
                                        "userId": 10,
                                        "title": "Tidy your room",
                                        "completed": true,
                                        "id": 198
                                      }
	 */


	@Test
	public void patch01()  {
		//1) Set the URL 
		spec.pathParams("todosPath", "todos", 
				"idPath",155);
		
		//2) Set the expected data
		
		JsonPlaceHolderTestData reqBodyObj = new JsonPlaceHolderTestData();
		Map<String , Object> patchReqBodyMap = reqBodyObj.setUpPatchDataByUsingMap();
		
		//3) Send the request
		Response response = given().
				contentType(ContentType.JSON).
				spec(spec).
				body(patchReqBodyMap).
			when().
				patch("/{todosPath}/{idPath}");
		
		response.prettyPrint();
		
		Map<String, Object> expDataMap =  reqBodyObj.expPatchDataByUsingMap(); 
//		1 way
		response.
			then().
			assertThat().
			statusCode(200).
			body("title", Matchers.equalTo(patchReqBodyMap.get("title")));
		
//		2 way Gson + putReqDataMap + SoftAssertion
		
		Map <String, Object> actualDataGson = response.as(HashMap.class);
		
		SoftAssert softAssert = new SoftAssert();
		
		System.out.println(actualDataGson);
		softAssert.assertEquals(((Double)actualDataGson.get("userId")).intValue() , expDataMap.get("userId"));
		softAssert.assertEquals(((Double)actualDataGson.get("id")).intValue(), expDataMap.get("id"));
		softAssert.assertEquals(actualDataGson.get("title"), expDataMap.get("title"));
		softAssert.assertEquals(actualDataGson.get("completed"), expDataMap.get("completed"));
		
		softAssert.assertAll();
		
//		3 way
		
		JsonPath json = response.jsonPath();
		
		assertEquals(reqBodyObj.statusCode, response.getStatusCode());
		assertEquals(expDataMap.get("title"), json.getString("title"));
		assertEquals(expDataMap.get("userId"), json.getInt("userId"));
		assertEquals(expDataMap.get("id"), json.getInt("id"));
		assertEquals(expDataMap.get("completed"), json.getBoolean("completed"));
		
		
}
}