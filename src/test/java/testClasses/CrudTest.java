package testClasses;

import api.*;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import utils.AuthenticationUtil;
import utils.RequestUtil;

import java.util.HashMap;
import java.util.Map;

public class CrudTest {

private int bookingId;

    @Test(description = "Create a booking")
    public void createBooking(){
        HashMap<Object, Object> requestBody = this.getDefaultRequestMap();
        CreateBookingApi createBookingApi = new CreateBookingApi();
        createBookingApi.setBody(requestBody);
        Response createBookingResponse = createBookingApi.sendRequest();
        bookingId = createBookingResponse.then().statusCode(200)
                .and().body("bookingid", Matchers.notNullValue())
                .and().body("booking", Matchers.notNullValue())
                .extract().jsonPath().getInt("bookingid");
    }

    @Test(description = "Retrieve the booking", dependsOnMethods = "createBooking")
    public void retrieveBooking(){
        GetBookingApi getBookingApi = new GetBookingApi(this.bookingId);
        Response retrieveBookingResponse = getBookingApi.sendRequest();
        retrieveBookingResponse.then().statusCode(200)
                .and().body("bookingdates", Matchers.notNullValue());
    }

    @Test(description = "Update a booking", dependsOnMethods = "retrieveBooking")
    public void updateBooking(){
        HashMap<Object, Object> requestBody = this.getDefaultRequestMap();
        requestBody.replace("depositpaid", false);
        requestBody.replace("totalprice", 500);
        UpdateBookingApi updateBookingApi = new UpdateBookingApi(this.bookingId);
        updateBookingApi.setHeader("Cookie", "token="+this.getToken());
        updateBookingApi.setBody(requestBody);
        Response response = updateBookingApi.sendRequest();
        response.then().statusCode(200)
                .and().body("bookingdates", Matchers.notNullValue());

    }

    @Test(description = "Update a part of the booking", dependsOnMethods = "updateBooking")
    public void partitionUpdateBooking(){
        PatchBookingApi patchBookingApi = new PatchBookingApi(this.bookingId);
        patchBookingApi.setHeader("Cookie", "token="+this.getToken());
        Response response = patchBookingApi.sendRequest();
        response.then().statusCode(200);
    }

    @Test(description = "Delete a booking", dependsOnMethods = "partitionUpdateBooking")
    public void deleteBooking(){
        DeleteBookingApi deleteBookingApi = new DeleteBookingApi(this.bookingId);
        deleteBookingApi.setHeader("Cookie", "token=" + this.getToken());
        Response response = deleteBookingApi.sendRequest();
        response.then().statusCode(201);

    }

    private HashMap<Object, Object> getDefaultRequestMap(){
        return RequestUtil.getStringObjectMap("Jim", "Harper", "Mineral water",
                true, 1000, "2024-10-23", "2023-02-23");
    }

    private String getToken() {
        return AuthenticationUtil.getToken();
    }
}
