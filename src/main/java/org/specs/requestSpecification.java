package org.specs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.config.configmanager;

public class requestSpecification {

    public static RequestSpecification spec() {

        return new RequestSpecBuilder()
                .setBaseUri(configmanager.getKey("baseURL")).setContentType(ContentType.JSON).log(LogDetail.ALL).build();

    }
}
