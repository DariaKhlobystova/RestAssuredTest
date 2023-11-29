package api;

import http.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import utils.RequestUtil;

import java.util.HashMap;
import java.util.Map;

public class CreateBookingApi extends BaseApi {
    public CreateBookingApi() {
        super("/booking", Method.POST);
        super.setContentType(ContentType.JSON);
        super.logResponse();
    }

    public HashMap<Object, Object> getRequest(){
        return RequestUtil.getStringObjectMap("Jim", "Harper", "Mineral water",
                true, 1000, "2024-10-23", "2023-02-23");
    }
}
