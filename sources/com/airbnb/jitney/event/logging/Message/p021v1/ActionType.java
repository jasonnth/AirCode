package com.airbnb.jitney.event.logging.Message.p021v1;

/* renamed from: com.airbnb.jitney.event.logging.Message.v1.ActionType */
public enum ActionType {
    DetailsButton(1),
    AcceptOrDecline(2),
    WriteReview(3),
    CompleteBooking(4),
    VerifyID(5),
    InstantBook(6),
    ViewPendingPaidAmenityOrders(7),
    ViewAvailablePaidAmenities(8),
    AlterationFlow(9),
    ViewCohostDetails(10);
    
    public final int value;

    private ActionType(int value2) {
        this.value = value2;
    }
}
