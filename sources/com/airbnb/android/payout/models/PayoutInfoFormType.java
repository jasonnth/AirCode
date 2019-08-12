package com.airbnb.android.payout.models;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.EnumSet;
import java.util.Set;

public enum PayoutInfoFormType {
    BankAccount("Bank Account"),
    InternationalWire("International Wire"),
    PayPal("PayPal"),
    PayoneerPrepaid("Payoneer API"),
    VaCuba("VaCuba"),
    WesternUnion("WesternUnion");
    
    private final String serverKey;

    private PayoutInfoFormType(String serverKey2) {
        this.serverKey = serverKey2;
    }

    @JsonValue
    public String getServerKey() {
        return this.serverKey;
    }

    public static Set<PayoutInfoFormType> getBankDepositPayoutMethodTypes() {
        return EnumSet.of(BankAccount);
    }

    public static Set<PayoutInfoFormType> getRedirectPayoutMethodTypes() {
        return EnumSet.of(PayoneerPrepaid);
    }
}
