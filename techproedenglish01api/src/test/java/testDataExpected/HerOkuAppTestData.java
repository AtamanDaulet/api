package testDataExpected;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class HerOkuAppTestData {
	
	
	public Map<String, Object> setUpData(){
		
	Map<String, String> bookingDatesMap = new HashMap<String, String>();
	Map<String, Object> bookingDetailsMap = new HashMap<String, Object>();
	
		bookingDatesMap.put("checkin", "2019-01-11");
		bookingDatesMap.put("checkout", "2020-10-29");
		
		bookingDetailsMap.put("firstname", "Jim");
		bookingDetailsMap.put("lastname", "Wilson");
		bookingDetailsMap.put("totalprice", 873.0);
		bookingDetailsMap.put("depositpaid", false);
		bookingDetailsMap.put("bookingdates", bookingDatesMap);
		
		return bookingDetailsMap;
	}	
	public JSONObject setUpDataJSONObjectPostRequest(){
		
		JSONObject bookingDataJSONObject = new JSONObject();
		
		bookingDataJSONObject.put("checkin", "2020-09-09");
		bookingDataJSONObject.put("checkout", "2020-09-21");
		
		JSONObject bookingDetailsJSONObject = new JSONObject();
		
		bookingDetailsJSONObject.put("firstname", "Selim");
		bookingDetailsJSONObject.put("lastname", "Ak");
		bookingDetailsJSONObject.put("totalprice", 11111);
		bookingDetailsJSONObject.put("depositpaid", true);
		bookingDetailsJSONObject.put("bookingdates", bookingDataJSONObject);
		
		return bookingDetailsJSONObject;
	}	
	public JSONObject setUpDataJSONObjectPractice02(){
		
		JSONObject bookingDataJSONObject = new JSONObject();
		
		bookingDataJSONObject.put("checkin", "2016-05-16");
		bookingDataJSONObject.put("checkout", "2017-08-31");
		
		JSONObject bookingDetailsJSONObject = new JSONObject();
		
		bookingDetailsJSONObject.put("firstname", "Eric");
		bookingDetailsJSONObject.put("lastname", "Ericsson");
		bookingDetailsJSONObject.put("totalprice", 572);
		bookingDetailsJSONObject.put("depositpaid", true);
		bookingDetailsJSONObject.put("bookingdates", bookingDataJSONObject);
		bookingDetailsJSONObject.put("additionalneeds", "Breakfast");
		
		return bookingDetailsJSONObject;
	}	
public JSONObject setUpDataJSONObjectPractice03Mary(){
		
		JSONObject bookingDataJSONObject = new JSONObject();
		
		bookingDataJSONObject.put("checkin", "2016-03-18");
		bookingDataJSONObject.put("checkout", "2018-09-01");
		
		JSONObject bookingDetailsJSONObject = new JSONObject();
		
		bookingDetailsJSONObject.put("firstname", "Mary");
		bookingDetailsJSONObject.put("lastname", "Jones");
		bookingDetailsJSONObject.put("totalprice", 438);
		bookingDetailsJSONObject.put("depositpaid", false);
		bookingDetailsJSONObject.put("bookingdates", bookingDataJSONObject);
		
		return bookingDetailsJSONObject;
	}	
}
