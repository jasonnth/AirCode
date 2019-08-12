package com.airbnb.jitney.event.logging.core.context.p025v2;

/* renamed from: com.airbnb.jitney.event.logging.core.context.v2.HTTPMethodType */
public enum HTTPMethodType {
    GET(1),
    POST(2),
    PUT(3),
    PATCH(4),
    DELETE(5);
    
    public final int value;

    private HTTPMethodType(int value2) {
        this.value = value2;
    }
}
