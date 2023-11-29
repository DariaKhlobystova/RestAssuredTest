package stepdefs;

import api.*;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import sharedState.SharedContext;
import utils.AuthenticationUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudStepDefs {

    private SharedContext sharedContext;

    public CrudStepDefs(SharedContext sharedContext) {
        this.sharedContext = sharedContext;
    }

    @When("we {string} the previously created booking")
    public void weThePreviouslyCreatedBooking(String apiType) {

        if (apiType.equals("retrieve")) {
            GetBookingApi getBookingApi = new GetBookingApi(this.sharedContext.bookingId);
            this.sharedContext.response = getBookingApi.sendRequest();

        } else if (apiType.equals("update")) {
            Map<Object, Object> requestBody = this.sharedContext.requestMap;
            requestBody.replace("depositpaid", false);
            requestBody.replace("totalprice", 500);
            UpdateBookingApi updateBookingApi = new UpdateBookingApi(this.sharedContext.bookingId);
            updateBookingApi.setHeader("Cookie", "token=" + this.getToken());
            updateBookingApi.setBody(requestBody);
            this.sharedContext.response = updateBookingApi.sendRequest();
        }
//        else if (apiType.equals("partition update")) {
//            Map<Object, Object> requestBody = this.sharedContext.requestMap;
//            requestBody.replace("additionalneeds", "Breakfast");
//            PatchBookingApi patchBookingApi = new PatchBookingApi(this.sharedContext.bookingId);
//            patchBookingApi.setHeader("Cookie", "token=" + this.getToken());
//            patchBookingApi.setBody(requestBody);
//            this.sharedContext.response = patchBookingApi.sendRequest();
         else if (apiType.equals("delete")) {
            DeleteBookingApi deleteBookingApi = new DeleteBookingApi(this.sharedContext.bookingId);
            deleteBookingApi.setHeader("Cookie", "token=" + this.getToken());
            this.sharedContext.response = deleteBookingApi.sendRequest();
        }
    }
        private String getToken() {
           return AuthenticationUtil.getToken();
        }

    @When("we partial-update the previously created booking with below data")
    public void wePartialUpdateThePreviouslyCreatedBookingWithBelowData(List<Map<Object, Object>> patchRequestList) {
        Map<Object, Object> requestMap = patchRequestList.get(0);
    }
}
