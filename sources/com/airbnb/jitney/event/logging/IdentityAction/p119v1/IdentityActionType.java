package com.airbnb.jitney.event.logging.IdentityAction.p119v1;

/* renamed from: com.airbnb.jitney.event.logging.IdentityAction.v1.IdentityActionType */
public enum IdentityActionType {
    REQUESTED(1),
    RENDERED(2),
    STARTED(3),
    ATTEMPTED(4),
    SUBMITTED(5),
    APPROVED_PROVISIONAL(6),
    APPROVED_FINAL(7),
    REJECTED_PROVISIONAL(8),
    REJECTED_FINAL(9),
    ABORTED(10),
    REJECTED(11),
    CONSUMED(12),
    CAPABILITIES_CHECKED(13);
    
    public final int value;

    private IdentityActionType(int value2) {
        this.value = value2;
    }
}
