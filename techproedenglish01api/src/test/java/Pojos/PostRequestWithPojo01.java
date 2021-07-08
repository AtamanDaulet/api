package Pojos;

import static org.junit.Assert.assertEquals;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import testBaseNtUrls.TestBaseJsonPlaceHolder;

public class PostRequestWithPojo01 extends TestBaseJsonPlaceHolder{
	/*
	 * When 
	 * 		I send post Request to the URL https://jsonplaceholder.typicode.com/todos
            with Post Request body  {
                                        "userId": 21,
                                        "id": 183,
                                        "title": "Tidy your room",
                                        "completed": false
                                      }
        Then 
            Status code is 201
            And response body is like {
                                        "userId": 21,
                                        "id": 183,
                                        "title": "Tidy your room",
                                        "completed": false
                                      }
	 */
	@Test 
	public void postPojo01() {
		//Set the URL
		spec.pathParam("todosPath", "todos");
		//Set the Expected Data
		TodosPojo expPojoData = new TodosPojo(21, 201, "Tidy your room!!!", false);
		//Send Request
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec).
								body(expPojoData).
							when().
								post("/{todosPath}");
		
		response.prettyPrint();
		//Assert
				//1.Way: body() + Pojo Class
		response.
			then().
			assertThat().
			statusCode(201).
			body("userId", Matchers.equalTo(expPojoData.getUserId()),
					"id", Matchers.equalTo(expPojoData.getId()),
					"title", Matchers.equalTo(expPojoData.getTitle()),
					"completed", Matchers.equalTo(expPojoData.isCompleted())) ;
		
		// 2 way  JsonPath + Pojo Class + Hard Assertion
		
		JsonPath json = response.jsonPath();
		
		assertEquals(expPojoData.getUserId(), json.getInt("userId"));
		assertEquals(expPojoData.getId(), json.getInt("id"));
		assertEquals(expPojoData.getTitle(), json.getString("title"));
		assertEquals(expPojoData.isCompleted(), json.getBoolean("completed"));
		
		// 3 way GSON + Pojo Class + SoftAssert
		
		TodosPojo actualPojoData = response.as(TodosPojo.class);
		System.out.println(actualPojoData);
		
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(actualPojoData.getUserId(), expPojoData.getUserId());
		softAssert.assertEquals(actualPojoData.getId(), expPojoData.getId());
		softAssert.assertEquals(actualPojoData.getTitle(), expPojoData.getTitle());
		softAssert.assertEquals(actualPojoData.isCompleted(), expPojoData.isCompleted());
		
		softAssert.assertAll();
		
	}

}
