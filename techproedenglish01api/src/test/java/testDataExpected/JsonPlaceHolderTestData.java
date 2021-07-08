package testDataExpected;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class JsonPlaceHolderTestData {
	
	public int statusCode = 200;
	
	public HashMap<String, Object> setUpData() {
		HashMap<String, Object> expectedDataMap = new HashMap<>();
			expectedDataMap.put("StatusCode", 200);
			expectedDataMap.put("completed", true);
			expectedDataMap.put("userId", 10);
			expectedDataMap.put("title", "quis eius est sint explicabo");
			expectedDataMap.put("headerVia", "1.1 vegur");
			expectedDataMap.put("Server", "cloudflare");
			
		return expectedDataMap;
	}
	public JSONObject setUpDataPostRequest03() {
		JSONObject expectedDataMap = new JSONObject();
			expectedDataMap.put("completed", false);
			expectedDataMap.put("userId", 55);
			expectedDataMap.put("title", "Tidy your room");
			
		return expectedDataMap;
	}
	public JSONObject expectedDataPostRequest03() {
		JSONObject expectedDataMap = new JSONObject();
			expectedDataMap.put("StatusCode", 200);
			expectedDataMap.put("completed", false);
			expectedDataMap.put("userId", 55);
			expectedDataMap.put("title", "Tidy your room");
			expectedDataMap.put("id", "201");
			
		return expectedDataMap;
	}
	
	public Map<String , Object> setUpPutDataByUsingMap(){
		Map <String , Object> putReqBodyMap = new HashMap<>();

		putReqBodyMap.put("userId", 21);
		putReqBodyMap.put("title", "Wash the dishes");
		putReqBodyMap.put("completed", false);
		
		return putReqBodyMap;
	}

	
	public Map<String , Object> setUpPatchDataByUsingMap(){
		Map <String , Object> putReqBodyMap = new HashMap<>();

		putReqBodyMap.put("title", "Tidy your room");
		
		return putReqBodyMap;
	}
	
	public Map<String , Object> expPatchDataByUsingMap(){
		Map <String , Object> expBodyMap = new HashMap<>();

		expBodyMap.put("userId", 8);
		expBodyMap.put("id", 155);
		expBodyMap.put("title", "Tidy your room");
		expBodyMap.put("completed", true);
		
		return expBodyMap;
	}
	
	public JSONObject setUpDeleteDataByUsingMap(){
		JSONObject expData = new JSONObject();
				
		return expData;
	}
}
