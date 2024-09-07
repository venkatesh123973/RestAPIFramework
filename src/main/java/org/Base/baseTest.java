package org.Base;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.specs.requestSpecification;
import org.testng.annotations.BeforeClass;

import java.util.Map;

public class baseTest {
    RequestSpecification reqspec;

    @BeforeClass
    public void setup() {
        System.out.println("Setting up the baseclass");
        reqspec = requestSpecification.spec();
    }


    public Response sendrequest(String method,
                                String endpoint, Object body,
                                Map<String, Object> header,
                                Map<String, Object> queryParams, String authtoken) {

        RequestSpecification req = RestAssured.given().spec(reqspec);

        if (header != null) {
            req.headers(header);
        }

        if (body != null) {
            req.body(body);
        }

        if (queryParams != null) {
            req.queryParams(queryParams);
        }

        if (authtoken != null) {
            req.auth().preemptive().oauth2(authtoken);
        }

        switch (method.toUpperCase()) {
            case "GET":
                return req.when().get(endpoint);

            case "POST":
                return req.when().post(endpoint);

            case "PUT":
                return req.when().put(endpoint);

            case "DELETE":
                return req.when().delete(endpoint);

            default:
                throw new IllegalArgumentException("Currently we are not supporting other methods other than GET,POST,PUT,DELETE");

        }

    }

    public Response sendrequest(String method, String endpoint, Object body) {
        return sendrequest(method, endpoint, body, null, null, null);

    }

    public Response sendrequest(String method, String endpoint, Object body, Map<String, Object> header) {
        return sendrequest(method, endpoint, body, header, null, null);

    }

    public Response sendrequest(String method, String endpoint, Object body, Map<String, Object> header, Map<String, Object> queryParams) {
        return sendrequest(method, endpoint, body, header, queryParams, null);

    }
}
