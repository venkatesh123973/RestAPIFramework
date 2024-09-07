package org.specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class responseSpecification {

    public static ResponseSpecification response_spec_200() {

        return new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).
                log(LogDetail.ALL).build();
    }

    public static ResponseSpecification response_spec_201() {

        return new ResponseSpecBuilder().expectStatusCode(201)
                .log(LogDetail.ALL).build();
    }

    public static ResponseSpecification response_spec_204() {

        return new ResponseSpecBuilder().expectStatusCode(204).expectContentType(ContentType.JSON).
                log(LogDetail.ALL).build();
    }
}
