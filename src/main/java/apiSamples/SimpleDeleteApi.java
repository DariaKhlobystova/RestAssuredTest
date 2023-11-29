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

public class SimpleDeleteApi {
    public static void main(String[] args) {
        AuthApi authApi = new AuthApi();
        String token = authApi.getToken();

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.contentType(ContentType.JSON)
                .header("Cookie","token="+ token)
                .filters(new ResponseLoggingFilter(), new RequestLoggingFilter())
                .and().baseUri("https://restful-booker.herokuapp.com")
                .and().basePath("/booking/{id}")
                .and().pathParam("id", 586)
                .when().delete();

    }
}
