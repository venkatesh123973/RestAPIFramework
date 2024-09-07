package org.tests;

import io.restassured.response.Response;
import org.Base.baseTest;
import org.model.BookerAPIToken;
import org.model.Booking;
import org.specs.responseSpecification;
import org.testng.annotations.Test;
import org.utilities.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class BookingAPIUSingPOJOwithoutCons extends baseTest {
  int bookingID;
    String token;

    @Test(priority = 0)
    public void getToken() {
        BookerAPIToken bookerAPIToken = new BookerAPIToken("admin","password123");
        Response resp = sendrequest("POST", "/auth", bookerAPIToken);
        token = JsonUtils.getString(resp, "token");
        System.out.println("Token Value " + token);
    }
    @Test(priority = 1)
    public void createBooking() {

        Booking booking = new Booking("Jayanta", "Mandal", 200, false, "Breakout", new Booking.BookingDates("2024-10-01", "2024-10-10"));

        Response resp = sendrequest("Post", "/booking", booking);

        resp.then().spec(responseSpecification.response_spec_200());

        System.out.println(resp.asPrettyString());
        bookingID = JsonUtils.getInt(resp, "bookingid");
    }

    @Test(priority = 2)
    public void updateBooking() {

        Booking booking = new Booking("JayantaKannan", "Mandal", 200, false, "Breakout", new Booking.BookingDates("2024-10-01", "2024-10-10"));
        Map<String, Object> headers = new HashMap<>();
        headers.put("Cookie", "token=" + token);
        Response resp = sendrequest("PUT", "/booking" + bookingID, booking,headers);

        resp.then().spec(responseSpecification.response_spec_201());

        System.out.println(resp.asPrettyString());

    }


    @Test(priority = 3)
    public void deleteBooking() {

        Map<String, Object> headers = new HashMap<>();
        headers.put("Cookie", "token=" + token);

        Response resp = sendrequest("DELETE", "/booking/" + bookingID, null, headers);
        System.out.println(resp.asPrettyString());
        resp.then().spec(responseSpecification.response_spec_201());
    }


}
