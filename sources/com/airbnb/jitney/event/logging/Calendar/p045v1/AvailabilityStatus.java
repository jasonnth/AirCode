package com.airbnb.jitney.event.logging.Calendar.p045v1;

/* renamed from: com.airbnb.jitney.event.logging.Calendar.v1.AvailabilityStatus */
public enum AvailabilityStatus {
    AVAILABLE(0),
    UNAVAILABLE(1),
    UNAVAILABLE_OVERRIDE(2),
    UNAVAILABLE_BY_BOOKING_WINDOW(3);
    
    public final int value;

    private AvailabilityStatus(int value2) {
        this.value = value2;
    }
}
