package org.tests;

import io.restassured.response.Response;
import org.Base.baseTest;
import org.config.configmanager;
import org.specs.responseSpecification;
import org.testng.annotations.Test;
import org.utilities.JsonUtils;

import java.util.HashMap;
import java.util.Map;

public class BookingAPI extends baseTest {
  int bookingID;
    @Test (priority = 0)
    public void createBooking() {

        String payload = "{\n" +
                "    \"firstname\": \"Jim\",\n" +
                "    \"lastname\": \"Brown\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        Response resp = sendrequest("POST", "/booking", payload);
        resp.then().spec(responseSpecification.response_spec_200());
        System.out.println(resp.asPrettyString());
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
