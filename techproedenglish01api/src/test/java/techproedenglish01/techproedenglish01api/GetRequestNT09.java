package techproedenglish01.techproedenglish01api;

import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import testBaseNtUrls.TestBaseDummy;

public class GetRequestNT09 extends TestBaseDummy{
/*
 * Use JsonPath Class and Soft Assertion to do;
	    
	 	When 
	 		I send Get Request to http://dummy.restapiexample.com/api/v1/employees
	 	Then
	 		The Status code is 200
	 		And The name of 3rd employee is "Ashton Cox"
	 		And The Salary of 6th employee is 372000
	 		And The age of the last employee is 23
	 		And 21, 23, 61 are among the ages
 */
	@Test
	public void get09() {
		spec.pathParam("employeesName", "employees");
		
		Response response = given().
								accept(ContentType.JSON).
								spec(spec).
							when().get("/{employeesName}");
		response.prettyPrint();
		
		JsonPath json = response.jsonPath();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(json.getString("data[2].employee_name"), "Ashton Cox", "Name didn't match");
		softAssert.assertEquals(json.getString("data[5].employee_salary"), "372000","Salary didn't match");
		softAssert.assertEquals(json.getString("data[-1].employee_age"), "23","Age didn't match");
		
		List<String> ageList = new ArrayList<>();
		ageList.add("21");
		ageList.add("23");
		ageList.add("61");
		
		softAssert.assertTrue(json.getList("data.employee_age").containsAll(ageList)  , 
				"At least one of Ages (21,23,61) didn't contain"   );
		
		softAssert.assertAll();
	} 
}
