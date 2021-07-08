package techproedenglish01.techproedenglish01api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import com.google.gson.Gson;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseJsonPlaceHolder;
import testDataExpected.HerOkuAppTestData;
import testDataExpected.JsonPlaceHolderTestData;

public class PostRequest03 extends TestBaseJsonPlaceHolder {
/*
 * When 
            I send POST Request to the Url https://jsonplaceholder.typicode.com/todos
            with the request body {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false
                                   }
        Then 
            Status code is 200
            And response body is like {
                                        "userId": 55,
                                        "title": "Tidy your room",
                                        "completed": false,
                                        "id": 201
                                      } 
 */
	@Test
	public void post02()  {
		//1) Set the URL
		spec.pathParam("todosPath", "todos");
		
		//2) Set the expected data
		
		JsonPlaceHolderTestData reqBodyObj = new JsonPlaceHolderTestData();
		JSONObject expBodyMap = reqBodyObj.setUpDataPostRequest03();
		
		//3) Send the request
		Response response = given().
				contentType(ContentType.JSON).
				spec(spec).
//				auth().
//				basic("admin", "password123").
				body(expBodyMap.toString()).
			when().
				post("/{todosPath}");
		
		response.prettyPrint();
		
		Map<String, Object> actualDataMap  = response.as(HashMap.class);
		JSONObject expDataBodyMap = reqBodyObj.expectedDataPostRequest03();
		
		System.out.println(actualDataMap);
		response.
			then().
			assertThat().statusCode(201);
		
		assertEquals(expDataBodyMap.getDouble("id"), actualDataMap.get("id"));
		assertEquals(expDataBodyMap.getDouble("userId"), actualDataMap.get("userId"));
		assertEquals(expDataBodyMap.getString("title"), actualDataMap.get("title"));
		assertEquals(expDataBodyMap.getBoolean("completed"), actualDataMap.get("completed"));
		
		
		
	}
}
