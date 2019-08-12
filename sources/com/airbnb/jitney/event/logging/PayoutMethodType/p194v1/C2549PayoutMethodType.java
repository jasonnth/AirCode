package com.airbnb.jitney.event.logging.PayoutMethodType.p194v1;

/* renamed from: com.airbnb.jitney.event.logging.PayoutMethodType.v1.PayoutMethodType */
public enum C2549PayoutMethodType {
    BankDeposit(1),
    PayPal(2),
    WesternUnion(3),
    InternationalWire(4),
    Vacuba(5),
    PayoneerDebit(6);
    
    public final int value;

    private C2549PayoutMethodType(int value2) {
        this.value = value2;
    }
}
