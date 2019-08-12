package com.airbnb.jitney.event.logging.IdentityReason.p124v1;

/* renamed from: com.airbnb.jitney.event.logging.IdentityReason.v1.IdentityReasonType */
public enum IdentityReasonType {
    OTHER(0),
    HOMES_BOOKING_HOST_REQUIRED(1),
    HOMES_BOOKING_RISK_REQUIRED(2),
    EXPERIENCES_BOOKING_PRIMARY_GUEST(3),
    EXPERIENCES_BOOKING_OTHER_GUEST(4),
    EXPERIENCES_HOST(5),
    HOMES_MANAGE_LISTING(6),
    SELF_INITIATED(7),
    AGENT_INITIATED(8),
    ALL_ACTIONS_RISK_REQUIRED(9);
    
    public final int value;

    private IdentityReasonType(int value2) {
        this.value = value2;
    }
}
