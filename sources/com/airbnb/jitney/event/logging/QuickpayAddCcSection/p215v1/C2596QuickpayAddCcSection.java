package com.airbnb.jitney.event.logging.QuickpayAddCcSection.p215v1;

/* renamed from: com.airbnb.jitney.event.logging.QuickpayAddCcSection.v1.QuickpayAddCcSection */
public enum C2596QuickpayAddCcSection {
    Country(1),
    CcNumber(2),
    ExpirationDate(3),
    CvvCode(4),
    ZipCode(5),
    Error(6),
    Successful(7),
    BrazilDetailsImpression(8),
    BrazilDetailsNext(9);
    
    public final int value;

    private C2596QuickpayAddCcSection(int value2) {
        this.value = value2;
    }
}
