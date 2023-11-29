package pojo;

import lombok.*;


@Data
public class CreateBookingRequest {
    private String firstname;
    private String lastname;
    private boolean depositpaid;
    private int totalprice;
    private BookingDates bookingdates;
    private String additionalneeds;
}
