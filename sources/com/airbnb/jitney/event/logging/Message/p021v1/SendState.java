package com.airbnb.jitney.event.logging.Message.p021v1;

/* renamed from: com.airbnb.jitney.event.logging.Message.v1.SendState */
public enum SendState {
    Success(1),
    Attempt(2),
    Failure(3);
    
    public final int value;

    private SendState(int value2) {
        this.value = value2;
    }
}
