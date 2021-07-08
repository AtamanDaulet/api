package techproedenglish01.techproedenglish01api;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import testBaseNtUrls.TestBaseDummy;
import testDataExpected.DummyTestData;

public class GetRequestNT12 extends TestBaseDummy{
	 /*
	 	When
	 		I send GET Request to the Url http://dummy.restapiexample.com/api/v1/employees
	 	Then
	 		Status code is 200
	 		And 5th employee name is Airi Satou
	 		And the number of employees is 24
	 		And the salary of 2nd last employee is 106450
	 		And 40, 21, and 19 are among the ages
	 		And 11th employee is like {
							            "id": "11",
							            "employee_name": "Jena Gaines",
							            "employee_salary": "90560",
							            "employee_age": "30",
							            "profile_image": ""
        							  }
	 */
	@Test
	public void get12() {
		spec.pathParam("employees", "employees");
		
		DummyTestData expectedObj = new DummyTestData();
		List <HashMap<String, Object>> expectedDataList = expectedObj.setUpData();
		
		Response response = given().
								accept(ContentType.JSON).
								spec(spec).
							when().get("/{employees}");
//		response.prettyPrint();
		
		response.
			then().
			assertThat().
			statusCode((Integer)expectedDataList.get(0).get("StatusCode")).
			body("data[4].employee_name", 
					equalTo(expectedDataList.get(1).get("SelectedEmployeeName")), 
				"data.id", 
					hasSize((Integer)expectedDataList.get(2).get("NumberOfEmployees")),
				"data[1].employee_salary", 
					equalTo(expectedDataList.get(3).get("SelectedEmpSalary")),
				"data.employee_age", 
					hasItems(((List) expectedDataList.get(4).get("MultipleAges")).get(0),
							((List) expectedDataList.get(4).get("MultipleAges")).get(1),
							((List) expectedDataList.get(4).get("MultipleAges")).get(2)),
				"data[10]", 
					equalTo((HashMap)expectedDataList.get(5).get("AllDetailAboutAnEmployee")),
				"data[10].employee_name", 
					equalTo(((HashMap)expectedDataList.get(5).
							get("AllDetailAboutAnEmployee")).get("employee_name")),
				"data[10].employee_salary", 
					equalTo(((HashMap)expectedDataList.get(5).
							get("AllDetailAboutAnEmployee")).get("employee_salary")),
				"data[10].employee_age", 
					equalTo(((HashMap)expectedDataList.get(5).
							get("AllDetailAboutAnEmployee")).get("employee_age")),
				"data[10].profile_image", 
					equalTo(((HashMap)expectedDataList.get(5).
							get("AllDetailAboutAnEmployee")).get("profile_image"))
					);
		
//		2 way
		
		assertEquals(expectedDataList.get(0).get("StatusCode"), response.getStatusCode());
		
		// Gson for De-Serialization
		Map<String, Object> actualDataMap = response.as(HashMap.class);
		System.out.println(actualDataMap);
		
		assertEquals(expectedDataList.get(0).get("StatusCode"), response.getStatusCode());
		assertEquals(expectedDataList.get(1).get("SelectedEmployeeName"), 
				((Map)((List)actualDataMap.get("data")).get(4)).get("employee_name") );
		assertEquals(expectedDataList.get(2).get("NumberOfEmployees"), 
				((List)actualDataMap.get("data")).size());
		assertEquals(expectedDataList.get(3).get("SelectedEmpSalary"), 
				((Map)((List)actualDataMap.get("data")).get(1)).get("employee_salary")  );
		
		int numOfEmp = ((List)actualDataMap.get("data")).size();
		
		List<String> ageList = new ArrayList<>();
		
		for (int i = 0; i < numOfEmp; i++) {
			ageList.add((String)((Map)((List)actualDataMap.get("data")).get(i)).get("employee_age"));
		}
		
		assertTrue(ageList.containsAll((List) expectedDataList.get(4).get("MultipleAges"))  );
		
		assertEquals(((Map)expectedDataList.get(5).get("AllDetailAboutAnEmployee")).get("employee_name"),
				((Map)((List)actualDataMap.get("data")).get(10)).get("employee_name")    );
		
		assertEquals(((Map)expectedDataList.get(5).get("AllDetailAboutAnEmployee")).get("employee_salary"),
				((Map)((List)actualDataMap.get("data")).get(10)).get("employee_salary")    );
		
		assertEquals(((Map)expectedDataList.get(5).get("AllDetailAboutAnEmployee")).get("employee_age"),
				((Map)((List)actualDataMap.get("data")).get(10)).get("employee_age")    );
		
		assertEquals(((Map)expectedDataList.get(5).get("AllDetailAboutAnEmployee")).get("profile_image"),
				((Map)((List)actualDataMap.get("data")).get(10)).get("profile_image")    );
		
	}

}
