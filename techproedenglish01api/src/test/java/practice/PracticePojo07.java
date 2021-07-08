package practice;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import practicePojo.BookingPojo07;
import practicePojo.BookingdatesPojo;
import testBaseNtUrls.TestBaseHerOkuApp;

public class PracticePojo07 extends TestBaseHerOkuApp{
	/*
    When
    	I send Get Request to url  https://restful-booker.herokuapp.com/booking/5
   Then
	   Status Code is 200
	   And response body is like
			{
			    "firstname": "Susan",
			    "lastname": "Jackson",
			    "totalprice": 762,
			    "depositpaid": false,
			    "bookingdates": {
			        "checkin": "2017-05-20",
			        "checkout": "2018-04-10"
			    },
			    "additionalneeds": "Breakfast"
			}			
 */
	
	@Test
	public void getPojo() {
		spec.pathParams("bookingPath", "booking", "id", 5);
		
		BookingdatesPojo bookingDates = new BookingdatesPojo("2020-02-22", "2020-09-09");
		BookingPojo07 expectedDataPojo = 
				new BookingPojo07("Sally", "Jones",  665, true, bookingDates, "Breakfast");
		
		Response response = given().
//								accept(ContentType.JSON).
								spec(spec).
							when().
								get("/{bookingPath}/{id}");
		response.prettyPrint();
		
		BookingPojo07 actualData = response.as(BookingPojo07.class);
		
		assertEquals(expectedDataPojo.getFirstname(), actualData.getFirstname());
		assertEquals(expectedDataPojo.getLastname(), actualData.getLastname());
		assertEquals(expectedDataPojo.getTotalprice(), actualData.getTotalprice());
		assertEquals(expectedDataPojo.isDepositpaid(), actualData.isDepositpaid());
		assertEquals(expectedDataPojo.getAdditionalneeds(), actualData.getAdditionalneeds());
		assertEquals(expectedDataPojo.getBookingdates().getCheckin(), 
				actualData.getBookingdates().getCheckin());
		assertEquals(expectedDataPojo.getBookingdates().getCheckout(), 
				actualData.getBookingdates().getCheckout());
		
	}

}
