package Pojos;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.gson.Gson;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseDummy;

public class GetRequestWithPojo01 extends TestBaseDummy{
/*
 * 	When 
 		I send GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
 	Then 
 		Status code is 200
 		And response body is like {
								    "status": "success",
								    "data": {
								        "id": 1,
								        "employee_name": "Tiger Nixon",
								        "employee_salary": 320800,
								        "employee_age": 61,
								        "profile_image": ""
								    },
								    "message": "Successfully! Record has been fetched."
								   }
 */
	
	@Test 
	public void get01() {
		
		spec.pathParams(
				"employeePath", "employee",
				"id", 1
				);
		
		Data data = new Data(1, "Tiger Nixon", 320800, 61, "");
		EmployeesExpectedPojo expEmployeesPojo = new 
				EmployeesExpectedPojo("success", data, "Successfully! Record has been fetched.");
		
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec).
							when().
								get("/{employeePath}/{id}");
		
		response.prettyPrint();
	
		EmployeesExpectedPojo actualDataPojo = response.as(EmployeesExpectedPojo.class);
//		System.out.println(actualDataPojo);
		
		assertEquals(expEmployeesPojo.getStatus(), actualDataPojo.getStatus());
		assertEquals(expEmployeesPojo.getData().getId(), actualDataPojo.getData().getId());
		assertEquals(expEmployeesPojo.getData().getEmployee_name(), actualDataPojo.getData().getEmployee_name());
		assertEquals(expEmployeesPojo.getData().getEmployee_salary(), actualDataPojo.getData().getEmployee_salary());
		assertEquals(expEmployeesPojo.getData().getEmployee_age(), actualDataPojo.getData().getEmployee_age());
		assertEquals(expEmployeesPojo.getData().getProfile_image(), actualDataPojo.getData().getProfile_image());
		assertEquals(expEmployeesPojo.getMessage(), actualDataPojo.getMessage());
		
		// Serialization by using Gson
		//Convert expEmployeesPojo to JSON Data
		
		//1 step  Create Gson object
		Gson gson = new Gson();
		
		// 2 step  Access to "toJson()" by using gson object
		String jsonFromJavaObject = gson.toJson(expEmployeesPojo);
//		System.out.println(expEmployeesPojo);
		System.out.println(jsonFromJavaObject);
		
		
		
		
		
		
	}
}
