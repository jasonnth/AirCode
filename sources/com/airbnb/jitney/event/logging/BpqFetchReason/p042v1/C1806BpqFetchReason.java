package com.airbnb.jitney.event.logging.BpqFetchReason.p042v1;

/* renamed from: com.airbnb.jitney.event.logging.BpqFetchReason.v1.BpqFetchReason */
public enum C1806BpqFetchReason {
    PaymentInstrument(1),
    GiftCredit(2),
    Coupon(3),
    CurrencyUpdate(4),
    PostalCode(5);
    
    public final int value;

    private C1806BpqFetchReason(int value2) {
        this.value = value2;
    }
}
