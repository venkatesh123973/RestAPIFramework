package org.tests;

import io.restassured.response.Response;
import org.Base.baseTest;
import org.model.Booking;
import org.model.BookingId;
import org.specs.responseSpecification;
import org.testng.annotations.Test;
import org.utilities.JsonUtils;

public class BookingAPIdeseralize extends baseTest {

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
        BookingId bookingId=JsonUtils.deserailize(resp, BookingId.class);
        System.out.println(bookingId.getBooking().getFirstname());
        System.out.println(bookingId.getBooking().getLastname());
        System.out.println(bookingId.getBookingid());

    }

}
