package com.airbnb.airrequest;

public enum RequestMethod {
    GET(0),
    POST(1),
    PUT(2),
    DELETE(3),
    HEAD(4),
    OPTIONS(5),
    TRACE(6),
    PATCH(7);
    
    private final int value;

    private RequestMethod(int value2) {
        this.value = value2;
    }

    public int value() {
        return this.value;
    }
}
