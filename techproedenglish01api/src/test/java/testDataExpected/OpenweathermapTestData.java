package testDataExpected;

import java.util.HashMap;
import java.util.Map;

public class OpenweathermapTestData {
	
	public int statusCode = 200;
	
	public Map<String, Float> coordSetUp() {
		Map<String, Float> coord = new HashMap<>();
			coord.put("lon", -0.13f);
			coord.put("lat", 51.51f);		
		return coord;
	}
	
	Map weather[] = {weatherSetUp()};
	
	public Map<String, Object> weatherSetUp() {
		Map<String, Object> weather = new HashMap<>();
			weather.put("id", 500);
			weather.put("main", "Rain");	
			weather.put("description", "light rain");	
			weather.put("icon", "10n");
		return weather;
	}
	public Map<String, Float> mainSetUp() {
		Map<String, Float> main = new HashMap<>();
		main.put("temp", 280.43f);
		main.put("feels_like", 275.94f);	
		main.put("temp_min", 279.82f);	
		main.put("temp_max", 282.15f);
		main.put("pressure", 1011f);
		main.put("humidity", 81f);
		
		return main;
	}
	public Map<String, Float> windSetUp() {
		Map<String, Float> wind = new HashMap<>();
		wind.put("speed", 4.6f);
		wind.put("deg", 230f);	
		wind.put("gust", 9.8f);	
		return wind;
	}
	public Map<String, Float> rainSetUp() {
		Map<String, Float> rain = new HashMap<>();
		rain.put("1h", 0.83f);	
		return rain;
	}
	
	public Map<String, Float> cloudsSetUp() {
		Map<String, Float> clouds = new HashMap<>();
		clouds.put("all", 80f);	
		return clouds;
	}
	
	public Map<String, Object> sysSetUp() {
		Map<String, Object> sys = new HashMap<>();
		sys.put("type", 1);
		sys.put("id", 1414);
		sys.put("country", "GB");
		sys.put("sunrise", 1608451412);
		sys.put("sunset", 1608479581);
		
		return sys;
	}
	
	

	public Map<String, Object> expectedGetData() {
		Map<String, Object> expData = new HashMap<>();
		expData.put("coord", coordSetUp());
		expData.put("weather", weather);
		expData.put("base", "stations");
		expData.put("main", mainSetUp());
		expData.put("visibility", 10000);
		expData.put("wind", windSetUp());
		expData.put("rain", rainSetUp());
		expData.put("clouds", cloudsSetUp());
		expData.put( "dt", 1608443818);
		expData.put("sys", sysSetUp());
		expData.put("timezone", 0);
		expData.put("id", 2643743);
		expData.put("name", "London");
		expData.put("cod", 200);
		
		
		return expData;
	}
	
	
}
