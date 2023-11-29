package testClasses;


import api.AuthApi;
import api.UpdateBookingApi;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import utils.AuthenticationUtil;

import java.util.HashMap;
import java.util.Map;

public class UpdateBookingsApiTests {

    @Test
    public void updateBookingApiTest(){
        UpdateBookingApi updateBookingApi = new UpdateBookingApi(4923);
        updateBookingApi.setHeader("Cookie", "token="+this.getToken());
        updateBookingApi.setBody(this.getUpdateRequestMap("Test", "Automation"));
        Response response = updateBookingApi.sendRequest();
        response.then().statusCode(200);

    }

    private String getToken() {
        return AuthenticationUtil.getToken();
    }

    private Map<String, Object> getUpdateRequestMap(String firstname, String lastname){
        Map<String, Object> requestBody = new HashMap<>();
        Map<String, Object> bookingDates = new HashMap<>();
        bookingDates.put("checkin", "2023-02-23");
        bookingDates.put("checkout", "2024-10-23");
        requestBody.put("firstname", firstname);
        requestBody.put("lastname", lastname);
        requestBody.put("totalprice", 1000);
        requestBody.put("depositpaid", true);
        requestBody.put("additionalneeds", "Mineral water");
        requestBody.put("bookingdates", bookingDates);
        return requestBody;
    }
}
