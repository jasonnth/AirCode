package com.airbnb.jitney.event.logging.DuplicateType.p088v1;

/* renamed from: com.airbnb.jitney.event.logging.DuplicateType.v1.DuplicateType */
public enum C1989DuplicateType {
    SelfReferral(1),
    SameReferralLink(2),
    MultipleReferralLinks(3);
    
    public final int value;

    private C1989DuplicateType(int value2) {
        this.value = value2;
    }
}
