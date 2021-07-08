package testDataExpected;

import java.util.HashMap;
import java.util.Map;

public class AgromonitoringTestData {
	/*
	 * {
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
	 */
	public final int status201 = 201;
	public float coordinates[][][] = {  {  {-121.1958f,37.6683f},
								            {-121.1779f,37.6687f},
								            {-121.1773f,37.6792f},
								            {-121.1958f,37.6792f},
								            {-121.1958f,37.6683f}  }  };
	
	public Map<String , Object> geometrySetUp(){
		Map <String , Object> geometry = new HashMap<>();
		
		geometry.put("type","Polygon");
		geometry.put("coordinates",coordinates);
		
		return geometry;
	}
	
	public Map <String , String> properties = new HashMap<>();
	
	public Map<String , Object> geojsonSetUp(){
		Map <String , Object> geojson = new HashMap<>();
		geojson.put("type","Feature");
		geojson.put("properties",properties);
		geojson.put("geometry", geometrySetUp());
		
		return geojson;
	}
	
	public Map<String , Object> postReqBodySetUp(){
		Map <String , Object> postReqBodyMap = new HashMap<>();
		postReqBodyMap.put("name","Polygon Sample");
		postReqBodyMap.put("geo_json", geojsonSetUp());
		
		return postReqBodyMap;
	}
	
	public Map<String , Object> extraDataSetUp(){
		Map <String , Object> extraDataMap = new HashMap<>();
		float center[] = { -121.1867f, 37.67385f};
		extraDataMap.put("area", 190.9484f);
		extraDataMap.put("user_id", "5fd8c02a3da20c000759e0f8");
		extraDataMap.put("center", center);
		
		return extraDataMap;
	}
	public Map<String , Object> geoJsonExpData(){
		Map <String , Object> geojson = new HashMap<>();
		geojson.put("type","Feature");
		geojson.put("properties",null);
		geojson.put("geometry", geometrySetUp());
		
		return geojson;
	}
	
}
