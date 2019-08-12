package com.airbnb.jitney.event.logging.CancellationFlowHostReasonL2.p053v1;

/* renamed from: com.airbnb.jitney.event.logging.CancellationFlowHostReasonL2.v1.CancellationFlowHostReasonL2 */
public enum C1899CancellationFlowHostReasonL2 {
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

    private C1899CancellationFlowHostReasonL2(int value2) {
        this.value = value2;
    }
}
