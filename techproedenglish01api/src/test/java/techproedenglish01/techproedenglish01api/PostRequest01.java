package techproedenglish01.techproedenglish01api;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import testBaseNtUrls.TestBaseDummy;
import testDataExpected.DummyTestData;

public class PostRequest01 extends TestBaseDummy{
/*
 * When 
 * 	I send a Post Request to the url http://dummy.restapiexample.com/api/v1/create
 *   by using the following Request Body {
                                                    "name":"Daulet",
                                                    "salary":"1000",
                                                    "age":"18",
                                                    "profile_image": ""
                                                }
         Then 
            Status code is 200
            And response body should be like {
                                                "status": "success",
                                                "data": {
                                                    "name": "Daulet",
                                                    "salary": "1000",
                                                    "age": "18",
                                                    "profile_image": null
                                                },
                                                "message": "Successfully! Record has been added."
                                             }
 */
	@Test
	public void post01()  {
		//1) Set the URL
		spec.pathParam("createPath", "create");
		
		//2) Set the expected data
		
		DummyTestData reqBodyObj = new DummyTestData();
		Map<String, String> reqBodyMap = reqBodyObj.setUpPostReqBody();
		
		//3) Send the request
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec).
//								auth().
//								basic("admin", "password123").
								body(reqBodyMap).
							when().
								post("/{createPath}");
//		response.prettyPrint();
		
		
		//4) Assert the things which are given in the test case
		DummyTestData expBodyObj = new DummyTestData();
		Map<String, String> expData = expBodyObj.setUpDataExp();
		
		response.then().assertThat().statusCode(200).body("data.name", equalTo(expData.get("name")),
				"data.salary", equalTo(expData.get("salary")),
				"data.age", equalTo(expData.get("age")),
				"data.profile_image", equalTo(expData.get("profile_image")));
			// 1 way
		JsonPath json = response.jsonPath();
		
		assertEquals(expData.get("name"), json.getString("data.name"));
		assertEquals(expData.get("salary"), json.getString("data.salary"));
		assertEquals(expData.get("age"), json.getString("data.age"));
		assertEquals(expData.get("profile_image"), json.getString("data.profile_image"));
		
//		System.out.println(reqBodyMap.get("data.profile_image"));
//		if(((String)reqBodyMap.get("data.profile_image")).equals(null)) {
//			System.out.println(reqBodyMap.get("data.profile_image"));
//			reqBodyMap.put("data.profile_image", null);
//		}
		
//		assertEquals(reqBodyMap.get("profile_image"), json.getString("data.profile_image"));
		
		Map<String, String> expMsgMap = reqBodyObj.setUpMessageDataByUsingMap();
		
		assertEquals(expMsgMap.get("status"), json.getString("status"));
		assertEquals(expMsgMap.get("message"), json.getString("message"));
		
			// 2 way  MAp + Gson
		Map<String, Object> actualDataMap = response.as(HashMap.class);
//		System.out.println(actualDataMap);
		
		assertEquals(expData.get("name"), ((Map)actualDataMap.get("data")).get("name"));
		assertEquals(expData.get("salary"), ((Map)actualDataMap.get("data")).get("salary"));
		assertEquals(expData.get("age"), ((Map)actualDataMap.get("data")).get("age"));
		
		assertEquals(expMsgMap.get("status"), actualDataMap.get("status"));
		assertEquals(expMsgMap.get("message"), actualDataMap.get("message"));
		
		  // 3 way  Soft assertion ==> JSONObject + JsonPath
		
		JSONObject expDataJSONObject = reqBodyObj.setUpPostReqBodyByUsingJSONObject();
		System.out.println(expDataJSONObject);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(json.getString("data.name"), expDataJSONObject.get("name"));
		softAssert.assertEquals(json.getString("data.salary"), expDataJSONObject.get("salary"));
		softAssert.assertEquals(json.getString("data.age"), expDataJSONObject.get("age"));
		
//		if(((String)reqBodyMap.get("data.profile_image")).equals(null)) {
//			reqBodyMap.put("data.profile_image", null);
//		}
		
		softAssert.assertEquals(String.valueOf(json.getString("data.profile_image")),
				expDataJSONObject.get("profile_image"));
		
		JSONObject expectedMsgJSONObject = reqBodyObj.setUpMessageDataByUsingJSONObject();
		
		softAssert.assertEquals(json.getString("status"), expectedMsgJSONObject.getString("status"));
		softAssert.assertEquals(json.getString("message"), expectedMsgJSONObject.getString("message"));
		
		softAssert.assertAll();
	}
}
