package apiSamples;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import pojo.BookingDates;
import pojo.CreateBookingRequest;

import java.util.HashMap;
import java.util.Map;

public class SimplePostApi {
    public static void main(String[] args) {
        RequestSpecification requestSpecification = RestAssured.given();
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-02-23");
        bookingDates.put("checkout", "2024-10-23");
        requestBody.put("firstname", "Jim");
        requestBody.put("lastname", "Harper");
        requestBody.put("totalprice", 1000);
        requestBody.put("depositpaid", true);
        requestBody.put("additionalneeds", "Mineral water");
        requestBody.put("bookingdates", bookingDates);


//        requestSpecification.contentType(ContentType.JSON)
//                .and().baseUri("https://restful-booker.herokuapp.com")
//                .and().basePath("/booking")
//                .and().filters(new ResponseLoggingFilter(), new RequestLoggingFilter())
//                .and().body(requestBody)
//                .when().post();
        CreateBookingRequest createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setFirstname("Jerry");
        createBookingRequest.setLastname("Hubert");
        createBookingRequest.setAdditionalneeds("Mineral water");
        createBookingRequest.setDepositpaid(true);
        createBookingRequest.setTotalprice(10000);
        BookingDates bookingDatesPojo = new BookingDates();
        bookingDatesPojo.setCheckin("2024-01-01");
        bookingDatesPojo.setCheckout("2023-11-11");
        createBookingRequest.setBookingdates(bookingDatesPojo);

        requestSpecification.contentType(ContentType.JSON)
                .and().baseUri("https://restful-booker.herokuapp.com")
                .and().basePath("/booking")
                .and().filters(new ResponseLoggingFilter(), new RequestLoggingFilter())
                .and().body(createBookingRequest)
                .when().post();


    }
}
