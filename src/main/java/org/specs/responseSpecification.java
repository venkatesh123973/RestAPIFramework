package org.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.config.configmanager;

public class responseSpecification {
    static boolean responselogs=Boolean.parseBoolean(configmanager.getKey("responselogs"));

    public static ResponseSpecification response_spec_200() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON);
        if (responselogs) {
            builder.log(LogDetail.ALL);
        }
        return builder.build();
    }
    public static ResponseSpecification response_spec_201() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder()
                .expectStatusCode(201);
        if (responselogs) {
            builder.log(LogDetail.ALL);
        }
        return builder.build();
    }

    public static ResponseSpecification response_spec_204() {
        ResponseSpecBuilder builder = new ResponseSpecBuilder()
                .expectStatusCode(204);
        if (responselogs) {
            builder.log(LogDetail.ALL);
        }
        return builder.build();
}}
