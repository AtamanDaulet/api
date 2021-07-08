package techproedenglish01.techproedenglish01api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import testBaseNtUrls.TestBaseOpenWetherMap;
import testDataExpected.OpenweathermapTestData;

public class GetRequest15 extends TestBaseOpenWetherMap{
/*
 * When 
	 		I send GET Request to the Url api.openweathermap.org/data/2.5/weather?q=London&appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0
	 	Then 
	 		Status code is 200
	 		And Response body is like {
										    "coord": {
										        "lon": -0.13,
										        "lat": 51.51
										    },
										    "weather": [
										        {
										            "id": 801,
										            "main": "Clouds",
										            "description": "few clouds",
										            "icon": "02n"
										        }
										    ],
										    "base": "stations",
										    "main": {
										        "temp": 284.57,
										        "feels_like": 280.6,
										        "temp_min": 283.71,
										        "temp_max": 285.37,
										        "pressure": 1007,
										        "humidity": 81
										    },
										    "visibility": 10000,
										    "wind": {
										        "speed": 5.1,
										        "deg": 160
										    },
										    "clouds": {
										        "all": 20
										    },
										    "dt": 1608329611,
										    "sys": {
										        "type": 1,
										        "id": 1414,
										        "country": "GB",
										        "sunrise": 1608278540,
										        "sunset": 1608306738
										    },
										    "timezone": 0,
										    "id": 2643743,
										    "name": "London",
										    "cod": 200
										}
 */
	
	@Test
	public void get15() {
		spec.pathParams("dataPath", "data",
				"numPath", 2.5,
				"weatherPath", "weather").
		queryParams("q", "London",
				"appid", "f4ffe3b2ef1fcb3600ab1d7fbc88c2f0");
		
		OpenweathermapTestData expectedData = new OpenweathermapTestData();
		
		Response response = given().
//								accept(ContentType.JSON).
								spec(spec).
							when().
								get("/{dataPath}/{numPath}/{weatherPath}");

		response.prettyPrint();
		
		JsonPath json = response.jsonPath();
		
		assertEquals(expectedData.statusCode, response.getStatusCode());
		
		assertEquals((Float)expectedData.coordSetUp().get("lon"), (Float)json.getFloat("coord.lon"));
		assertEquals((Float)expectedData.coordSetUp().get("lat"), (Float)json.getFloat("coord.lat"));
		
//		assertEquals(expectedData.weatherSetUp().get("id"), json.getInt("weather[0].id"));
//		assertEquals(expectedData.weatherSetUp().get("main"), json.getString("weather[0].main"));
//		assertEquals(expectedData.weatherSetUp().get("description"), json.getString("weather[0].description"));
//		assertEquals(expectedData.weatherSetUp().get("icon"), json.getString("weather[0].icon"));
		
		assertEquals(expectedData.expectedGetData().get("base"), json.getString("base"));
		
//		assertEquals((Float)expectedData.mainSetUp().get("temp"), (Float)json.getFloat("main.temp"));
//		assertEquals((Float)expectedData.mainSetUp().get("feels_like"), (Float)json.getFloat("main.feels_like"));
//		assertEquals((Float)expectedData.mainSetUp().get("temp_min"), (Float)json.getFloat("main.temp_min"));
//		assertEquals((Float)expectedData.mainSetUp().get("temp_max"), (Float)json.getFloat("main.temp_max"));
//		assertEquals((Float)expectedData.mainSetUp().get("pressure"), (Float)json.getFloat("main.pressure"));
//		assertEquals((Float)expectedData.mainSetUp().get("humidity"), (Float)json.getFloat("main.humidity"));
		
		assertEquals(expectedData.expectedGetData().get("visibility"), json.getInt("visibility"));
		
		assertEquals((Float)expectedData.windSetUp().get("speed"), (Float)json.getFloat("wind.speed"));
		assertEquals((Float)expectedData.windSetUp().get("deg"), (Float)json.getFloat("wind.deg"));
		assertEquals((Float)expectedData.windSetUp().get("gust"), (Float)json.getFloat("wind.gust"));
		
//		assertEquals((Float)expectedData.cloudsSetUp().get("all"), (Float)json.getFloat("clouds.all"));
//		
//		assertEquals(expectedData.expectedGetData().get("dt"), json.getInt("dt"));
		
		assertEquals(expectedData.sysSetUp().get("type"), json.getInt("sys.type"));
		assertEquals(expectedData.sysSetUp().get("id"), json.getInt("sys.id"));
		assertEquals(expectedData.sysSetUp().get("country"), json.getString("sys.country"));
		assertEquals(expectedData.sysSetUp().get("sunrise"), json.getInt("sys.sunrise"));
		assertEquals(expectedData.sysSetUp().get("sunset"), json.getInt("sys.sunset"));
		
		assertEquals(expectedData.expectedGetData().get("timezone"), json.getInt("timezone"));
		assertEquals(expectedData.expectedGetData().get("id"), json.getInt("id"));
		assertEquals(expectedData.expectedGetData().get("name"), json.getString("name"));
		assertEquals(expectedData.expectedGetData().get("cod"), json.getInt("cod"));
		
	}
	
}
