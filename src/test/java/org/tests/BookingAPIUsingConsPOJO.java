package org.tests;

import io.restassured.response.Response;
import org.Base.baseTest;
import org.config.configmanager;
import org.model.BookerAPIToken;
import org.model.Booking;
import org.specs.responseSpecification;
import org.testng.annotations.Test;
import org.utilities.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class BookingAPIUsingConsPOJO extends baseTest {
    int bookingID;

    @Test(priority = 1)
    public void createBooking() {

        Booking booking = new Booking();
        booking.setFirstname("Himanshu");
        booking.setLastname("Kumar");
        booking.setTotalprice(200);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Evening Snacks");
        booking.setBookingdates(new Booking.BookingDates("2024-10-01", "2024-10-10"));

        Response resp = sendrequest("Post", "/booking", booking);

        resp.then().spec(responseSpecification.response_spec_200());

        System.out.println(resp.asPrettyString());
        bookingID = JsonUtils.getInt(resp, "bookingid");

    }

    @Test(priority = 2)
    public void updateBooking() {
        Booking booking = new Booking();
        booking.setFirstname("Venkat");
        booking.setLastname("Kannan");
        booking.setTotalprice(400);
        booking.setDepositpaid(true);
        booking.setAdditionalneeds("Break");
        booking.setBookingdates(new Booking.BookingDates("2025-10-01", "2025-10-10"));
        Map<String, Object> headers = new HashMap<>();
        headers.put("Cookie", "token=" + configmanager.getKey("token"));
        Response resp = sendrequest("PUT", "/booking/" + bookingID, booking, headers);
        resp.then().spec(responseSpecification.response_spec_200());
        System.out.println(resp.asPrettyString());
    }

    @Test(priority = 3)
    public void deleteBooking() {

        Map<String, Object> headers = new HashMap<>();
        headers.put("Cookie", "token=" + configmanager.getKey("token"));
        Response resp = sendrequest("DELETE", "/booking/" + bookingID, null, headers);
        System.out.println(resp.asPrettyString());
        resp.then().spec(responseSpecification.response_spec_201());
    }

}
