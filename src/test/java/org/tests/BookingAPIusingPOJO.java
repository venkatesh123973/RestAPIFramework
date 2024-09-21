package org.tests;

import io.restassured.response.Response;
import org.Base.baseTest;
import org.config.configmanager;
import org.model.Booking;
import org.specs.responseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.utilities.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class BookingAPIusingPOJO extends baseTest {
   int bookingID;
    @Test
    public void getBooking() {
        Booking booking = Booking.builder()
                .firstname("Bhagyashree")
                .lastname("API")
                .totalprice(300)
                .depositpaid(true)
                .additionalneeds("lunch")
                .bookingdates(Booking.BookingDates.builder().checkin("2024-12-01").checkout("2024-12-15").build())
                .build();

        Response resp = sendrequest("Post", "/booking", booking);

        resp.then().spec(responseSpecification.response_spec_200());

        System.out.println(resp.asPrettyString());

        String name = JsonUtils.getString(resp, "booking.firstname");

        Assert.assertTrue(name.contains("Bhagyashree"), "Name does not match");

        int price = JsonUtils.getInt(resp, "booking.totalprice");

        Assert.assertEquals(price, 300, "Price does not match");
        bookingID = JsonUtils.getInt(resp, "bookingid");
    }

    @Test(priority = 1)
    public void deleteBooking() {

        Map<String, Object> headers = new HashMap<>();
        headers.put("Cookie", "token=" + configmanager.getKey("token"));
        Response resp = sendrequest("DELETE", "/booking/" + bookingID, null, headers);
        System.out.println(resp.asPrettyString());
        resp.then().spec(responseSpecification.response_spec_201());
    }
}
