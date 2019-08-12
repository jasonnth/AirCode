package com.airbnb.jitney.event.p304v1;

/* renamed from: com.airbnb.jitney.event.v1.MessageType */
public enum MessageType {
    JITNEY_THRIFT(0),
    JITNEY_JSON(1),
    SIMPLE_JSON(2),
    UNKNOWN(3);
    
    public final int value;

    private MessageType(int value2) {
        this.value = value2;
    }
}
