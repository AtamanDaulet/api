package Pojos;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import static org.hamcrest.Matchers.*;
import org.junit.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import testBaseNtUrls.TestBaseHerOkuApp;

public class PostRequestWithPojo02 extends TestBaseHerOkuApp{
	/*
 	When 
 		I send POST Request to the URL https://restful-booker.herokuapp.com/booking
 		with Post Request body  {
								    "firstname": "Alp",
								    "lastname": "Sun",
								    "totalprice": 888,
								    "depositpaid": false,
								    "bookingdates": {
								        "checkin": "2021-01-07",
								        "checkout": "2021-01-25"
								    }
								}
 	Then 
 		Status code is 200
 		And response body is like {
								    "bookingid": 13,
								    "booking": {
								        "firstname": "Alp",
								        "lastname": "Sun",
								        "totalprice": 888,
								        "depositpaid": false,
								        "bookingdates": {
								            "checkin": "2021-01-07",
								            "checkout": "2021-01-25"
								        }
								     }
								  }
 	
	 */


	@Test 
	public void postPojo02() {
		
		spec.pathParam("bookingPath", "booking");
		
		BookingDatesPojo bookingdates = new BookingDatesPojo("2021-01-07", "2021-01-25");
		BookingPojo expBooking = new BookingPojo("D", "A", 888, false, bookingdates);
		
		Response response = given().
								contentType(ContentType.JSON).
								spec(spec).
								body(expBooking).
							when().
								post("/{bookingPath}");
		
		response.prettyPrint();
		
//		Assert
		// 1 way body + booking  --> homework
		
		response.
			then().
				assertThat().
				statusCode(200).
			body("booking.firstname", equalTo(expBooking.getFirstname()), 
					"booking.lastname", equalTo(expBooking.getLastname()),
					"booking.totalprice", equalTo(expBooking.getTotalprice()),
					"booking.depositpaid", equalTo(expBooking.isDepositpaid()),
					"booking.bookingdates.checkin", equalTo(expBooking.getBookingdates().getCheckin()),
					"booking.bookingdates.checkout", equalTo(expBooking.getBookingdates().getCheckout())					
					);
		// 2 way JsonPath + booking  --> homework
		JsonPath json = response.jsonPath();
		
		assertEquals(expBooking.getFirstname(), json.getString("booking.firstname"));
		assertEquals(expBooking.getLastname(), json.getString("booking.lastname"));
		assertEquals(expBooking.getTotalprice(), json.getInt("booking.totalprice"));
		assertEquals(expBooking.isDepositpaid(), json.getBoolean("booking.depositpaid"));
		assertEquals(expBooking.getBookingdates().getCheckin(), json.getString("booking.bookingdates.checkin"));
		assertEquals(expBooking.getBookingdates().getCheckout(), json.getString("booking.bookingdates.checkout"));
		
		// 3 way GSON + booking  --> homework
		// Following code gives error, because BookingPojo format is diffirent from response body format.
		// If you see that kind of issue, you have 2 option:
				//	1. Stop using Pojo + Gson, proceed with JsonPath
				// 2 . Create new Pojo Classes for the response body
//		BookingPojo actualBooking = response.as(BookingPojo.class);
//		System.out.println(actualBooking);
		
		BookingResponsePojo actualBooking = response.as(BookingResponsePojo.class);
//		System.out.println(actualBooking);
		
		assertEquals(expBooking.getFirstname(), actualBooking.getBooking().getFirstname());
		assertEquals(expBooking.getLastname(), actualBooking.getBooking().getLastname());
		assertEquals(expBooking.getTotalprice(), actualBooking.getBooking().getTotalprice());
		assertEquals(expBooking.isDepositpaid(), actualBooking.getBooking().isDepositpaid());
		assertEquals(expBooking.getBookingdates().getCheckin(), 
				actualBooking.getBooking().getBookingdates().getCheckin());
		assertEquals(expBooking.getBookingdates().getCheckout(), 
				actualBooking.getBooking().getBookingdates().getCheckout());
		
	}
}