package com.airbnb.jitney.event.logging.UnavailableReason.p277v1;

/* renamed from: com.airbnb.jitney.event.logging.UnavailableReason.v1.UnavailableReason */
public enum C2775UnavailableReason {
    MinNightRequirementNotMet(1),
    MaxNightRequirementNotMet(2),
    MinNightRequirementCannotSatisty(3),
    ClosedToArrival(4),
    ClosedToDeparture(5),
    SpecificCheckInDayRequirement(6),
    UnavailableForCheckIn(7),
    UnavailableForCheckOut(8),
    ContainUnavailableDays(9);
    
    public final int value;

    private C2775UnavailableReason(int value2) {
        this.value = value2;
    }
}
