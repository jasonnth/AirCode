package com.airbnb.airrequest;

public class TagFactory {
    public static String requestTag(AirRequest airRequest) {
        return requestTag(airRequest.getClass());
    }

    public static String requestTag(Class<?> klass) {
        return klass.getSimpleName();
    }
}
