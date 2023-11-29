package api;

import http.BaseApi;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.PropertyConfig;

import java.util.HashMap;
import java.util.Map;

public class AuthApi extends BaseApi {
    public AuthApi() {
        super("/auth", Method.POST);
        super.setContentType(ContentType.JSON);
        super.logResponse();
    }
    public String getToken(){
        var request = new HashMap<>();
        request.put("username", PropertyConfig.getConfig().username());
        request.put("password", PropertyConfig.getConfig().password());
        super.setBody(request);
        return super.sendRequest().then().statusCode(200)
                .and().extract().jsonPath().getString("token");
    }
}
