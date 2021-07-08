package techproedenglish01.techproedenglish01api;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseDummy;
import testDataExpected.DummyTestData;

public class GetRequestNT14 extends TestBaseDummy{
/*
 		When 
	 		I send a request to http://dummy.restapiexample.com/api/v1/employees
	 	Then 
	 		Status code is 200
	 		And the highest salary is 725000
	 		And the minimum age is 19
	 		And the second lowest salary is 90560
 */
	
	@Test
	public void get14() {
		spec.pathParam("employeesPath", "employees");
		
		DummyTestData expectedObj = new DummyTestData();
		Map<String, Integer> expectedMap = expectedObj.setUpData2();
		
		Response response = given().
								accept(ContentType.JSON).
								spec(spec).
							when().get("/{employeesPath}");
//		response.prettyPrint();
		
		assertEquals(expectedMap.get("StatusCode"), (Integer)response.getStatusCode());
		
	// 1 way JsonPath
		
		JsonPath json = response.jsonPath();
		List<String> salaryList = json.getList("data.employee_salary");
		List<Integer> salaryListInt = new ArrayList<>();
		
		salaryList.stream().forEach(t -> salaryListInt.add(Integer.valueOf(t)));
		
		Collections.sort(salaryListInt);
		
		assertEquals(expectedMap.get("HighestSalary"), 
				salaryListInt.get(salaryListInt.size()-1) );
		
		List<String> ageList = json.getList("data.employee_age");
		List<Integer> ageListInt = new ArrayList<>();
		ageList.stream().forEach(t -> ageListInt.add(Integer.valueOf(t)));
		Collections.sort(ageListInt);	
		
		assertEquals(expectedMap.get("MinAge"), 
				ageListInt.get(0) );
		
		assertEquals(expectedMap.get("SecondHighestSalary"), 
				salaryListInt.get(salaryListInt.size()-2) );
		
		// 2 way gson
		
		Map<String, Object> actualDataMap = response.as(HashMap.class);
		List<Integer> empSalaryList = new ArrayList<>();
		List<Integer> agesGsonList = new ArrayList<>();
//		System.out.println(actualDataMap);
		for (int i = 0; i < ((List)actualDataMap.get("data")).size(); i++) {
		empSalaryList.add( Integer.valueOf( (String) ((Map)((List)actualDataMap.get("data")).get(i)).get("employee_salary")));
		agesGsonList.add( Integer.valueOf( (String) ((Map)((List)actualDataMap.get("data")).get(i)).get("employee_age"))   );
		}
		
		Collections.sort(empSalaryList);
		Collections.sort(agesGsonList); 
		
		assertEquals(expectedMap.get("HighestSalary"), empSalaryList.get(empSalaryList.size()-1));
		
		assertEquals(expectedMap.get("MinAge"), agesGsonList.get(0));
		
		assertEquals(expectedMap.get("SecondHighestSalary"), empSalaryList.get(empSalaryList.size()-2));
	}
	
}
