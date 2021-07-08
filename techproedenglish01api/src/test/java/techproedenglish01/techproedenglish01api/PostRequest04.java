package techproedenglish01.techproedenglish01api;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Map;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseAgromonitoring;
import testDataExpected.AgromonitoringTestData;

public class PostRequest04 extends TestBaseAgromonitoring{
/*
 * When 
         I send POST Request to the Url "http://api.agromonitoring.com/agro/1.0/polygons?appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0"
         with the Request Body like  {
                                           "name":"Polygon Sample",
                                           "geo_json":{
                                              "type":"Feature",
                                              "properties":{},
                                              "geometry":{
                                                 "type":"Polygon",
                                                 "coordinates":[
                                                    [
                                                       [-121.1958,37.6683],
                                                       [-121.1779,37.6687],
                                                       [-121.1773,37.6792],
                                                       [-121.1958,37.6792],
                                                       [-121.1958,37.6683]
                                                    ]
                                                 ]
                                              }
                                           }
                                        }
    Then
        Assert Status Code (201) 
        And Response Body should be like {
                                            "id": "5fd8c383714b523b2ce1f154",
                                            "geo_json": {
                                                "geometry": {
                                                    "coordinates": [
                                                        [
                                                            [
                                                                -121.1958,
                                                                37.6683
                                                            ],
                                                            [
                                                                -121.1779,
                                                                37.6687
                                                            ],
                                                            [
                                                                -121.1773,
                                                                37.6792
                                                            ],
                                                            [
                                                                -121.1958,
                                                                37.6792
                                                            ],
                                                            [
                                                                -121.1958,
                                                                37.6683
                                                            ]
                                                        ]
                                                    ],
                                                    "type": "Polygon"
                                                },
                                                "type": "Feature",
                                                "properties": {
                                                    
                                                }
                                            },
                                            "name": "Polygon Sample",
                                            "center": [
                                                -121.1867,
                                                37.67385
                                            ],
                                            "area": 190.9484,
                                            "user_id": "5fd8c02a3da20c000759e0f8",
                                            "created_at": 1608041347
                                        }
 */
	@Test
	public void post04()  {
		//1) Set the URL ==> appid=f4ffe3b2ef1fcb3600ab1d7fbc88c2f0
		spec.pathParams("agroPath", "agro", 
				"idPath",1.0, 
				"polygonsPath","polygons").
				queryParam("appid", "f4ffe3b2ef1fcb3600ab1d7fbc88c2f0");
		
		//2) Set the expected data
		
		AgromonitoringTestData reqBodyObj = new AgromonitoringTestData();
		Map<String , Object> postReqBodyMap = reqBodyObj.postReqBodySetUp();
		
		//3) Send the request
		Response response = given().
				contentType(ContentType.JSON).
				spec(spec).
//				auth().
//				basic("admin", "password123").
				body(postReqBodyMap).
			when().
				post("/{agroPath}/{idPath}/{polygonsPath}");
		
		response.prettyPrint();
		
		response.then().assertThat().statusCode(201).
			body("geo_json.geometry.coordinates[0][1][0]", Matchers.equalTo(reqBodyObj.coordinates[0][1][0]) ,
				"geo_json.geometry.coordinates[0][1][1]", Matchers.equalTo(reqBodyObj.coordinates[0][1][1]),
				"geo_json.geometry.type", Matchers.equalTo(reqBodyObj.geometrySetUp().get("type")),
				"geo_json.geometry.properties", Matchers.equalTo(reqBodyObj.geoJsonExpData().get("properties")),
				"geo_json.type", Matchers.equalTo(reqBodyObj.geojsonSetUp().get("type")),
				"name", Matchers.equalTo(postReqBodyMap.get("name")),
				"center[0]", Matchers.equalTo(((float[])reqBodyObj.extraDataSetUp().get("center"))[0])	
					);
	
//		jsonPath and postReqBodyMap ==> Homework
		
		JsonPath json = response.jsonPath();

		assertEquals(reqBodyObj.status201, response.getStatusCode());
		assertEquals(((float[][][])((Map)reqBodyObj.geometrySetUp()).get("coordinates"))[0][0][0], 
				json.getFloat("geo_json.geometry.coordinates[0][0][0]"),0);
		assertEquals(((float[][][])((Map)reqBodyObj.geometrySetUp()).get("coordinates"))[0][0][1], 
				json.getFloat("geo_json.geometry.coordinates[0][0][1]"),0);
		
		assertEquals(((float[][][])((Map)reqBodyObj.geometrySetUp()).get("coordinates"))[0][1][0], 
				json.getFloat("geo_json.geometry.coordinates[0][1][0]"),0);
		assertEquals(((float[][][])((Map)reqBodyObj.geometrySetUp()).get("coordinates"))[0][1][1], 
				json.getFloat("geo_json.geometry.coordinates[0][1][1]"),0);
		
		assertEquals(((float[][][])((Map)reqBodyObj.geometrySetUp()).get("coordinates"))[0][2][0], 
				json.getFloat("geo_json.geometry.coordinates[0][2][0]"),0);
		assertEquals(((float[][][])((Map)reqBodyObj.geometrySetUp()).get("coordinates"))[0][2][1], 
				json.getFloat("geo_json.geometry.coordinates[0][2][1]"),0);
		
		assertEquals(((float[][][])((Map)reqBodyObj.geometrySetUp()).get("coordinates"))[0][3][0], 
				json.getFloat("geo_json.geometry.coordinates[0][3][0]"),0);
		assertEquals(((float[][][])((Map)reqBodyObj.geometrySetUp()).get("coordinates"))[0][3][1], 
				json.getFloat("geo_json.geometry.coordinates[0][3][1]"),0);
		
		assertEquals(((float[][][])((Map)reqBodyObj.geometrySetUp()).get("coordinates"))[0][4][0], 
				json.getFloat("geo_json.geometry.coordinates[0][4][0]"),0);
		assertEquals(((float[][][])((Map)reqBodyObj.geometrySetUp()).get("coordinates"))[0][4][1], 
				json.getFloat("geo_json.geometry.coordinates[0][4][1]"),0);
		
		assertEquals(((Map)reqBodyObj.geometrySetUp()).get("type"),	json.getString("geo_json.geometry.type"));
		
		assertEquals(((Map)postReqBodyMap.get("geo_json")).get("type"),	json.getString("geo_json.type"));
		
		assertEquals(((Map)postReqBodyMap.get("geo_json")).get("properties"),
				json.getMap("geo_json.properties"));
		
		assertEquals(postReqBodyMap.get("name"), json.getString("name"));
		
		assertEquals(((float[])reqBodyObj.extraDataSetUp().get("center"))[0], json.getFloat("center[0]"),0);
		assertEquals(((float[])reqBodyObj.extraDataSetUp().get("center"))[1], json.getFloat("center[1]"),0);

		assertEquals((float)reqBodyObj.extraDataSetUp().get("area"), json.getFloat("area"),0);
		
		assertEquals(reqBodyObj.extraDataSetUp().get("user_id"), json.getString("user_id"));
		
	}
}
