package utils;

import java.util.HashMap;
import java.util.Map;

public class RequestUtil {
    public static HashMap<Object, Object> getStringObjectMap(Object firstName, Object lastName, Object additionalNeeds,
                                                             Object depositPaid, Object amount, Object checkOut, Object checkIn) {

        var requestBody = new HashMap<>();
        var bookingDates = new HashMap<>();
        bookingDates.put("checkin", checkIn);
        bookingDates.put("checkout", checkOut);
        requestBody.put("firstname", firstName);
        requestBody.put("lastname", lastName);
        requestBody.put("totalprice", amount);
        requestBody.put("depositpaid", depositPaid);
        requestBody.put("additionalneeds", additionalNeeds);
        requestBody.put("bookingdates", bookingDates);
        return requestBody;
    }
}
