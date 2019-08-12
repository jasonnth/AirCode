package com.airbnb.android.core.payments.models;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AddPaymentMethodClientType {
    GiftCardRedemption("GiftCardRedemption"),
    Unknown("");
    
    private final String serverKey;

    private AddPaymentMethodClientType(String serverKey2) {
        this.serverKey = serverKey2;
    }

    @JsonValue
    public String getServerkey() {
        return this.serverKey;
    }
}
