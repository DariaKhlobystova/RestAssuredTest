package apiSamples;

import api.AuthApi;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class SimplePutApi {
    public static void main(String[] args) {
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-02-23");
        bookingDates.put("checkout", "2024-10-23");
        requestBody.put("firstname", "Jim");
        requestBody.put("lastname", "Conrad");
        requestBody.put("totalprice", 12000);
        requestBody.put("depositpaid", true);
        requestBody.put("additionalneeds", "Mineral water");
        requestBody.put("bookingdates", bookingDates);
        AuthApi authApi = new AuthApi();
        String token = authApi.getToken();
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON)
                .and().header("Cookie","token="+ token)
                .and().baseUri("https://restful-booker.herokuapp.com")
                .and().basePath("/booking/{id}")
                .and().filters(new ResponseLoggingFilter(), new RequestLoggingFilter())
                .and().pathParam("id", 928)
                .and().body(requestBody)
                .when().put();

    }

}
