package testDataExpected;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class DummyTestData {

	public int statusCode = 200;

	public List<HashMap<String, Object>> expectedDataList;

	public List<HashMap<String, Object>> setUpData() {
		HashMap<String, Object> expectedMap = new HashMap<>();
		expectedDataList = new ArrayList<>();

		expectedMap.put("StatusCode", 200);
		expectedDataList.add(expectedMap);

		HashMap<String, Object> expectedMap1 = new HashMap<>();
		expectedMap1.put("SelectedEmployeeName", "Airi Satou");
		expectedDataList.add(expectedMap1);

		HashMap<String, Object> expectedMap2 = new HashMap<>();
		expectedMap2.put("NumberOfEmployees", 24);
		expectedDataList.add(expectedMap2);

		HashMap<String, Object> expectedMap3 = new HashMap<>();
		expectedMap3.put("SelectedEmpSalary", "170750");
		expectedDataList.add(expectedMap3);

		HashMap<String, Object> expectedMap4 = new HashMap<>();
		List<String> ageList = new ArrayList<>();
		ageList.add("40");
		ageList.add("21");
		ageList.add("19");

		expectedMap4.put("MultipleAges", ageList);
		expectedDataList.add(expectedMap4);

		HashMap<String, Object> expectedMap5 = new HashMap<>();
		HashMap<String, String> empDetailMap = new HashMap<>();
		empDetailMap.put("id", "11");
		empDetailMap.put("employee_name", "Jena Gaines");
		empDetailMap.put("employee_salary", "90560");
		empDetailMap.put("employee_age", "30");
		empDetailMap.put("profile_image", "");

		expectedMap5.put("AllDetailAboutAnEmployee", empDetailMap);
		expectedDataList.add(expectedMap5);

		return expectedDataList;
	}

	public Map<String, Integer> setUpData2() {
		Map<String, Integer> expectedDataMap = new HashMap<String, Integer>();
		expectedDataMap.put("StatusCode", 200);
		expectedDataMap.put("HighestSalary", 725000);
		expectedDataMap.put("MinAge", 19);
		expectedDataMap.put("SecondHighestSalary", 675000);

		return expectedDataMap;
	}

	public Map<String, String> setUpPostReqBody() {
		Map<String, String> reqBodyMap = new HashMap<>();
		reqBodyMap.put("name", "Daulet");
		reqBodyMap.put("salary", "1000");
		reqBodyMap.put("age", "18");
		reqBodyMap.put("profile_image", "");

		return reqBodyMap;
	}

	public Map<String, String> setUpDataExp() {
		Map<String, String> expectedDataMap1 = new HashMap<>();
		expectedDataMap1.put("name", "Daulet");
		expectedDataMap1.put("salary", "1000");
		expectedDataMap1.put("age", "18");
		expectedDataMap1.put("profile_image", null);

		return expectedDataMap1;
	}

	public Map<String, String> setUpMessageDataByUsingMap() {

		Map<String, String> massageMap = new HashMap<String, String>();
		massageMap.put("status", "success");
		massageMap.put("message", "Successfully! Record has been added.");
		return massageMap;

	}

	public JSONObject setUpPostReqBodyByUsingJSONObject() {

		JSONObject reqBodyJSONObject = new JSONObject();
		reqBodyJSONObject.put("name", "Daulet");
		reqBodyJSONObject.put("salary", "1000");
		reqBodyJSONObject.put("age", "18");
		reqBodyJSONObject.put("profile_image", "null");

		return reqBodyJSONObject;
	}

	public JSONObject setUpMessageDataByUsingJSONObject() {

		JSONObject messageJSONObject = new JSONObject();
		messageJSONObject.put("status", "success");
		messageJSONObject.put("message", "Successfully! Record has been added.");

		return messageJSONObject;
	}

	public Map<String, Object> setUpExpDeleteDataByUsingMap() {

		Map<String, Object> expectedData = new HashMap<>();
		;
		expectedData.put("status", "success");
		expectedData.put("message", "Successfully! Record has been deleted");
		expectedData.put("data", "2");
		expectedData.put("statusCode", 200);

		return expectedData;
	}

	public Map<String, String> postRequestBodySetUpPractice05() {
		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("name", "Test Data");
		requestBody.put("salary", "8888");
		requestBody.put("age", "33");

		return requestBody;
	}
	public Map<String, String> expectedDataPractice05() {
		Map<String, String> expectedData = new HashMap<>();
		expectedData.put("status", "success");
		expectedData.put("message", "Successfully! Record has been added.");
		expectedData.put("profile_image", "null");
		return expectedData;
	}
	public Map<String, String> requestDataPractice06() {
		Map<String, String> requestBody = new HashMap<>();
		requestBody.put("name", "Micheal JACKSON");
		requestBody.put("salary", "10000");
		requestBody.put("age", "55");
		requestBody.put("profile_image", "");

		return requestBody;
	}

}
