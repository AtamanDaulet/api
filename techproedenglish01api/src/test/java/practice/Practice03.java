package practice;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseDummy;
import testDataExpected.HerOkuAppTestData;

public class Practice03 extends TestBaseDummy {
	/*
	Print all employees on the console
	Assert that 4 rd employee's name is Cedric Kelly
	Print first 4 employees' names
	Assert that 11. employee's salary is 90560
	Assert that 16. employee's age is 66
	http://dummy.restapiexample.com/api/v1/employees
	          1)Print all ids smaller than 8 on the console
	  Assert that there are 9 ids greater than 14
	2)Print all ages less than 40 on the console
	  Assert that maximum age less than 30 is 23
	3)Print all employee names whose salaries are smaller than 500000
	  Assert that "Brielle Williamson" is one of the employees whose salary is smaller than 500000
	 */
	@Test 
	public void get01() {
		spec.pathParams("employeesPath", "employees");
		
//		HerOkuAppTestData expObject = new HerOkuAppTestData();
//		JSONObject expData = expObject.setUpDataJSONObjectPractice02();
		
		Response response = given().
					accept(ContentType.JSON).
					spec(spec).
				when().
					get("/{employeesPath}");
		
//		response.prettyPrint();
		
		JsonPath actualJson = response.jsonPath(); 
	//Assert that 4 rd employee's name is Cedric Kelly	
	assertEquals("Cedric Kelly", actualJson.getString("data[3].employee_name"));

	//Print first 4 employees' names
	List<String> firstFourEmployeesName = new ArrayList<>();
	
	for (int i = 0; i < 4; i++) {
		firstFourEmployeesName.add(actualJson.getString("data["+i+"].employee_name"));
	}
	System.out.println(firstFourEmployeesName);
	
	//Assert that 11. employee's salary is 90560
	assertEquals("90560", actualJson.getString("data[10].employee_salary"));
	
	//Assert that 16. employee's age is 66
	assertEquals("66", actualJson.getString("data[15].employee_age"));
	
	//  1)Print all ids smaller than 8 on the console
	
	List<Map<String, String>> eightFirstEmployees = actualJson.getList("data");

	for (int i = 0; i < 8; i++) {
		System.out.println(eightFirstEmployees.get(i));
	}
	
//	Assert that there are 10 ids greater than 14

	List< String> idsEmployees = actualJson.getList("data.findAll{Integer.valueOf(it.id) > 14}.id");

	assertEquals(10, idsEmployees.size());
	
//	2)Print all ages less than 40 on the console
	
	List< String> ageList = actualJson.getList("data.findAll{Integer.valueOf(it.employee_age) < 40}.employee_age");
	System.out.println(ageList);
	
//	  Assert that maximum age less than 30 is 23
	
	List< String> ageLess30List = actualJson.getList("data.findAll{Integer.valueOf(it.employee_age) < 30}.employee_age");
	Collections.sort(ageLess30List);
	assertEquals("23", ageLess30List.get(ageLess30List.size()-1));
	
//	3)Print all employee names whose salaries are smaller than 500000
	
	List< String> namesSalarySmaller500kList = actualJson.
			getList("data.findAll{Integer.valueOf(it.employee_salary) < 500000}.employee_name");
	
//	  Assert that "Brielle Williamson" is one of the employees whose salary is smaller than 500000
	
	assertTrue(namesSalarySmaller500kList.contains("Brielle Williamson"));
	
	}
}
