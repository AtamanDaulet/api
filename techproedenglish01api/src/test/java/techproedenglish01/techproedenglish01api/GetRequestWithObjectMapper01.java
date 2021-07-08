package techproedenglish01.techproedenglish01api;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import testBaseNtUrls.TestBaseJsonPlaceHolder;
import utilities.JsonUtil;

public class GetRequestWithObjectMapper01 extends TestBaseJsonPlaceHolder  {

	/*
	        When 
            I send GET Request to the URL https://jsonplaceholder.typicode.com/todos/198
            
        Then 
            Status code is 200
            And response body is like {
                                            "userId": 10,
                                            "id": 198,
                                            "title": "quis eius est sint explicabo",
                                            "completed": true
                                      }
	 */
	
	@Test
	public void get01() {
		spec.pathParams("todosPath", "todos",
							"idPath", 198);
		
//		2
		String expectedJson = "{\n"
				+"\"userId\": 10,\n"
               + "\"id\": 198,\n"
               + "\"title\": \"quis eius est sint explicabo\",\n"
               + "\"completed\": true\n}";
		
		Map<String, Object> expectedMap = JsonUtil.convertJsonToJava(expectedJson, Map.class);
//		System.out.println(expectedMap);
		
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec).
							when().
								get("/{todosPath}/{idPath}");
		
		response.prettyPrint();
		
		// Instead of using "toString" use "asString()"
		Map<String, Object> actualMap = JsonUtil.convertJsonToJava(response.asString(), Map.class);		
//		System.out.println(actualMap);
		
		assertEquals(expectedMap.get("userId"), actualMap.get("userId"));
		assertEquals(expectedMap.get("id"), actualMap.get("id"));
		assertEquals(expectedMap.get("title"), actualMap.get("title"));
		assertEquals(expectedMap.get("completed"), actualMap.get("completed"));
		
		
	}
	
	
}
