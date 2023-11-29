package api;

import http.BaseApi;
import io.restassured.http.ContentType;
import io.restassured.http.Method;

public class PatchBookingApi extends BaseApi {
    public PatchBookingApi(Object bookingId) {
        super("/booking/{bookingId}", Method.PATCH);
        super.setPathParams("bookingId", bookingId);
//        super("/booking/{id}", Method.PATCH);
//        super.setPathParams("id", bookingId);
        super.setContentType(ContentType.JSON);
//        super.logResponse();
    }
}
