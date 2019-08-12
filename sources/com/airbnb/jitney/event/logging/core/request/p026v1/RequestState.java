package com.airbnb.jitney.event.logging.core.request.p026v1;

/* renamed from: com.airbnb.jitney.event.logging.core.request.v1.RequestState */
public enum RequestState {
    Success(1),
    Attempt(2),
    Failure(3),
    Cached(4);
    
    public final int value;

    private RequestState(int value2) {
        this.value = value2;
    }
}
