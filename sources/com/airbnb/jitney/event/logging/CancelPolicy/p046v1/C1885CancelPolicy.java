package com.airbnb.jitney.event.logging.CancelPolicy.p046v1;

/* renamed from: com.airbnb.jitney.event.logging.CancelPolicy.v1.CancelPolicy */
public enum C1885CancelPolicy {
    CANCEL_FLEXIBLE(3),
    CANCEL_MODERATE(4),
    CANCEL_STRICT(5),
    CANCEL_SUPER_STRICT(6),
    CANCEL_NO_REFUNDS(7),
    CANCEL_LONG_TERM(8),
    CANCEL_SUPER_STRICT_60(9),
    CANCEL_FLEXIBLE_NEW(10),
    CANCEL_MODERATE_NEW(11),
    CANCEL_STRICT_NEW(12),
    CANCEL_SUPER_STRICT_30_NEW(13),
    CANCEL_SUPER_STRICT_60_NEW(14),
    CANCEL_LONG_TERM_NEW(15);
    
    public final int value;

    private C1885CancelPolicy(int value2) {
        this.value = value2;
    }
}
