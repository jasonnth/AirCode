package com.airbnb.jitney.event.logging.IdentityFlowSource.p122v1;

/* renamed from: com.airbnb.jitney.event.logging.IdentityFlowSource.v1.IdentityFlowSource */
public enum C2233IdentityFlowSource {
    MobileHandoff(1),
    Booking(2),
    NonBooking(3),
    Profile(4),
    MagicalTrips(5),
    AppLaunch(6),
    Others(7),
    Unknown(8);
    
    public final int value;

    private C2233IdentityFlowSource(int value2) {
        this.value = value2;
    }
}
