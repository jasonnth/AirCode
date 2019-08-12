package com.airbnb.jitney.event.logging.HostReasonL2.p116v1;

/* renamed from: com.airbnb.jitney.event.logging.HostReasonL2.v1.HostReasonL2 */
public enum C2209HostReasonL2 {
    DoubleBooked(1),
    PartialDoubleBooked(2),
    Emergency(3),
    TripLength(4),
    Price(5),
    ChangeReservation(6),
    BadReviews(7),
    HouseRules(8),
    OtherConcerns(9);
    
    public final int value;

    private C2209HostReasonL2(int value2) {
        this.value = value2;
    }
}
