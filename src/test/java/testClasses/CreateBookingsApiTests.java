package testClasses;

import api.CreateBookingApi;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.BookingDates;
import pojo.CreateBookingRequest;
import pojo.response.CreateBookingResponse;
import utils.RequestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.specification.Argument.withArg;

public class CreateBookingsApiTests {

    @Test(description = "Create a booking and check response status code")
    public void createBookingStatusCodeValidation(){
        HashMap<Object, Object> requestBody = this.getDefaultRequestMap();
        CreateBookingApi createBookingApi = new CreateBookingApi();
        createBookingApi.setBody(requestBody);
        createBookingApi.sendRequest().then().statusCode(200)
                .and().body("bookingid", Matchers.notNullValue())
                .and().body("booking", Matchers.notNullValue());
    }

    private HashMap<Object, Object> getDefaultRequestMap(){
        return RequestUtil.getStringObjectMap("Jim", "Harper", "Mineral water",
                true, 1000, "2024-10-23", "2023-02-23");
    }

    private CreateBookingRequest getRequestPojo(){
        CreateBookingRequest createBookingRequest = new CreateBookingRequest();
        createBookingRequest.setFirstname("Jerry");
        createBookingRequest.setLastname("Hubert");
        createBookingRequest.setAdditionalneeds("Mineral water");
        createBookingRequest.setDepositpaid(true);
        createBookingRequest.setTotalprice(10000);
        BookingDates bookingDatesPojo = new BookingDates();
        bookingDatesPojo.setCheckin("2024-01-01");
        bookingDatesPojo.setCheckout("2023-11-11");
        createBookingRequest.setBookingdates(bookingDatesPojo);
        return createBookingRequest;
    }

    @DataProvider(parallel = true)
    public Object[][] bookingDetailsDp(){
        return new Object[][] {
                {"Jerry", "Helpert", "mineral water", true, 1000, "2024-02-02", "2024-01-01"},
                {"Harry", "Hubert", "mineral water", true, 1000, "2024-02-02", "2024-01-01"},
                {"Marry", "Olsen", "mineral water", true, 1000, "2024-02-02", "2024-01-01"},
        };
    }

    @Test(description = "Create a booking with help of data provider", dataProvider = "bookingDetailsDp")
    public void createBookingStatusCodeValidationWithDp(String firstname, String lastname, String additionalNeeds,
                                                   boolean depositPaid, int amount, String checkout, String checkin)
    {

        HashMap<Object, Object> requestBody = RequestUtil.getStringObjectMap(firstname,
                lastname, additionalNeeds, depositPaid, amount, checkout, checkin);

        CreateBookingApi createBookingApi = new CreateBookingApi();
        createBookingApi.setBody(requestBody);

        Response createBookingResponse = createBookingApi.sendRequest();
//        createBookingApi.sendRequest().then().statusCode(200)
//                .and().body("bookingid", Matchers.notNullValue())
//                .and().body("booking", Matchers.notNullValue())
//                .and().rootPath("booking")
//                .and().body("additionalneeds", Matchers.equalTo(additionalNeeds))
//                .and().body("firstname", Matchers.equalTo(firstname))
//                .and().body("lastname", Matchers.equalTo(lastname))
//                .and().appendRootPath("bookingdates")
//                .and().body("checkin", Matchers.equalTo(checkin))
//                .and().body("checkout", Matchers.equalTo(checkout))
//                .and().detachRootPath("booking.bookingdates");
        var arguments = List.of(withArg("firstname"), withArg("lastname"), withArg("additionalneeds"));
        createBookingApi.sendRequest().then().statusCode(200)
                .and().rootPath("booking", arguments)
                .and().body(Matchers.notNullValue());
//        String firstName = createBookingResponse.then().extract().jsonPath().getString("booking.firstname");
//        System.out.println("Firstname: " + firstName);
//        String lastName = createBookingResponse.then().extract().jsonPath().getString("booking.lastname");
//        System.out.println("Lastname: " + lastName);
//        String bookingDatesFromResponse = createBookingResponse.then().extract().jsonPath().getString("booking.bookingdates");
//        System.out.println("BookingDatesFromResponse: " + bookingDatesFromResponse);

        CreateBookingResponse createBookingResponsePojo = createBookingResponse.as(CreateBookingResponse.class);
        System.out.println("Firstname: " + createBookingResponsePojo.getBooking().getFirstName());
        System.out.println("Lastname: " + createBookingResponsePojo.getBooking().getLastName());
        System.out.println("Additional needs: " + createBookingResponsePojo.getBooking().getAdditionalNeeds());
        System.out.println("Booking dates: " + createBookingResponsePojo.getBooking().getBookingDates());

    }

}

