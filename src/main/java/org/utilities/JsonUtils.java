package org.utilities;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static String getString(Response resp, String jsonpath) {
        String value = resp.jsonPath().getString(jsonpath);
        return value;
    }

    public static int getInt(Response resp, String jsonpath) {
        int value = resp.jsonPath().getInt(jsonpath);
        return value;
    }

    public static boolean getBoolean(Response resp, String jsonpath) {
        boolean value = resp.jsonPath().getBoolean(jsonpath);
        return value;
    }

    public static <T> List<T> getList(Response resp, String jsonpath) {
        return resp.jsonPath().getList(jsonpath);
    }

    public static <K, V> Map<K, V> getMap(Response resp, String jsonpath) {
        return resp.jsonPath().getMap(jsonpath);
    }
}
