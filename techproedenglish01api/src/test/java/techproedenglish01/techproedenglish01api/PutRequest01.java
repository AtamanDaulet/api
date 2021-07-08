package techproedenglish01.techproedenglish01api;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseJsonPlaceHolder;
import testDataExpected.JsonPlaceHolderTestData;

public class PutRequest01 extends TestBaseJsonPlaceHolder{
	/*
	 When
            I send PUT Requst to the Url https://jsonplaceholder.typicode.com/todos/198
            with the PUT Request body like {
                                            "userId": 21,
                                            "title": "Wash the dishes",
                                            "completed": false
                                           }
       Then 
           Status code is 200
           And response body is like  {
                                        "userId": 21,
                                        "title": "Wash the dishes",
                                        "completed": false,
                                        "id": 198
                                      }
	 */
	@Test
	public void put01()  {
		//1) Set the URL ==> appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0
		spec.pathParams("todosPath", "todos", 
				"idPath",155);
		
		//2) Set the expected data
		
		JsonPlaceHolderTestData reqBodyObj = new JsonPlaceHolderTestData();
		Map<String , Object> putReqBodyMap = reqBodyObj.setUpPutDataByUsingMap();
		
		//3) Send the request
		Response response = given().
				contentType(ContentType.JSON).
				spec(spec).
//				auth().
//				basic("admin", "password123").
				body(putReqBodyMap).
			when().
				put("/{todosPath}/{idPath}");
		
		response.prettyPrint();
		
//		Homework
//		1 way body() + putReqDataMap   --Homework
//		2 way JsonPath() + putReqDataMap  --- Homework
		
//		3 way Gson + putReqDataMap + SoftAssertion
		
		Map<String, Object> actualDataMap = response.as(HashMap.class);
		System.out.println(actualDataMap);
		
		SoftAssert softAssert = new SoftAssert();
//		softAssert.assertEquals(actualDataMap.get("userId"), putReqBodyMap.get("userId"));
		softAssert.assertEquals(actualDataMap.get("completed"), putReqBodyMap.get("completed"));
		softAssert.assertEquals(actualDataMap.get("title"), putReqBodyMap.get("title"));
		
		softAssert.assertAll();
	}
}
