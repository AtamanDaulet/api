package techproedenglish01.techproedenglish01api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import com.google.gson.JsonObject;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseJsonPlaceHolder;
import testDataExpected.DummyTestData;
import testDataExpected.JsonPlaceHolderTestData;

public class DeleteRequest02 extends TestBaseJsonPlaceHolder{
/*
 * When
	 		I send DELETE Request to the Url https://jsonplaceholder.typicode.com/todos/198
	 	Then Status code is 200
	 	And Response body is {}
 */
	@Test
	public void delete02() {
		spec.pathParams("todosPath", "todos",
						"id", 198);
		
		JsonPlaceHolderTestData expectedObj = new JsonPlaceHolderTestData();
		JSONObject expectedData = expectedObj.setUpDeleteDataByUsingMap();
		
		Response response = given().
								accept(ContentType.JSON).
								spec(spec).
							when().
								delete("/{todosPath}/{id}");
		response.prettyPrint();
	
		//1 way body
				response.
					then().
					assertThat().
					statusCode(expectedObj.statusCode);
				
		Map<String, Object> actualDataMap = response.as(HashMap.class);
//		System.out.println(actualDataMap.size());
		
		assertEquals(expectedData.length(), actualDataMap.size());
		
	}
}
