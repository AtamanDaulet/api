package techproedenglish01.techproedenglish01api;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import testBaseNtUrls.TestBaseDummy;
public class GetRequestNT10 extends TestBaseDummy{
/*
 *      When 
         I send GET Request to URL http://dummy.restapiexample.com/api/v1/employees
     Then
         Status code is 200
         1)Print all ids greater than 10 on the console
           Assert that there are 14 ids greater than 10
         2)Print all ages less than 30 on the console
           Assert that maximum age is 23
         3)Print all employee names whose salaries are greater than 350,000 
           Assert that "Charde Marshall" is one of the employees whose salary is greater than 350,000

 */
	@Test
	public void get10() {
		spec.pathParam("employeesName", "employees");
		
		Response response = given().
								accept(ContentType.JSON).
								spec(spec).
							when().
								get("/{employeesName}");
		response.prettyPrint();
		
		response.then().assertThat().statusCode(200);
		
//		 1)Print all ids greater than 10 on the console 
		JsonPath json = response.jsonPath();
		
		//To filter the outputs we need to use "Groovy Language";
		List<String> idList = json.getList("data.findAll{Integer.valueOf(it.id) > 10}.id") ;
		
		System.out.println(idList);
//		Assert that there are 14 ids greater than 10
		
		assertEquals(14, idList.size());
		
//		Print all ages less than 30 on the console
//		Assert that maximum age is 23
		List<String> employeeAgeList = json.getList("data.findAll{Integer.valueOf(it.employee_age) < 30}.employee_age") ;
		
		
		List<Integer> ageListInt = new ArrayList<>();
		for (String w : employeeAgeList) {
			ageListInt.add(Integer.valueOf(w));
		}
		System.out.println(ageListInt);
		Collections.sort(ageListInt);
		System.out.println(ageListInt);
		assertEquals(Integer.valueOf(23),  ageListInt.get(ageListInt.size()-1));
		
//		3)Print all employee names whose salaries are greater than 350,000 
//        Assert that "Charde Marshall" is one of the employees whose salary is greater than 350,000
	
		List<String> employeeNameList = json.
				getList("data.findAll{Integer.valueOf(it.employee_salary) > 350000}.employee_name") ;
		System.out.println(employeeNameList);
		
		assertTrue("Charde Marshall didn't exist in employee names whose salaries are greater than 350,000 ",
				employeeNameList.contains("Charde Marshall"));
		
		
	}
}
