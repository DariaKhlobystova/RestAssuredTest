package testClasses;

import api.AuthApi;
import api.DeleteBookingApi;
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

public class DeleteBookingTests {

    @Test
    public void deleteBooking(){
        DeleteBookingApi deleteBookingApi = new DeleteBookingApi(950);
        deleteBookingApi.setHeader("Cookie", "token=" + this.getToken());
        Response response = deleteBookingApi.sendRequest();
        response.then().statusCode(201);
    }

    private String getToken() {
        return AuthenticationUtil.getToken();
    }
}
