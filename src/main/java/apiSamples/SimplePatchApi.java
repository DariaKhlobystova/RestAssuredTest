//package apiSamples;
//
//import io.restassured.RestAssured;
//import io.restassured.filter.log.RequestLoggingFilter;
//import io.restassured.filter.log.ResponseLoggingFilter;
//import io.restassured.http.ContentType;
//import io.restassured.specification.RequestSpecification;
//import pojo.BookingDates;
//import pojo.CreateBookingRequest;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class SimplePatchApi {
//    public static void main(String[] args) {
//        RequestSpecification requestSpecification = RestAssured.given();
////        Map<String, Object> requestBody = new HashMap<>();
////        Map<String, Object> bookingDates = new HashMap<>();
////        bookingDates.put("checkin", "2023-02-23");
////        bookingDates.put("checkout", "2024-10-23");
////        requestBody.put("firstname", "Jim");
////        requestBody.put("lastname", "Harper");
////        requestBody.put("totalprice", 1000);
////        requestBody.put("depositpaid", true);
////        requestBody.put("additionalneeds", "Mineral water");
////        requestBody.put("bookingdates", bookingDates);
//
//        CreateBookingRequest createBookingRequest = new CreateBookingRequest();
//        createBookingRequest.setAdditionalneeds("Breakfast");
//
//        requestSpecification.contentType(ContentType.JSON)
//                .and().baseUri("https://restful-booker.herokuapp.com")
//                .and().basePath("/booking/{id}")
//                .and().pathParam("id", 1421)
//                .and().filters(new ResponseLoggingFilter(), new RequestLoggingFilter())
//                .and().body(createBookingRequest)
//                .when().patch();
//    }
//}
//
