package com.airbnb.android.payout.models;

import com.google.common.collect.FluentIterable;

public enum ExtraPayoutAttributeType {
    SHOW_ACCOUNT_TYPE("show_account_type"),
    UNKNOWN("");
    
    private final String serverKey;

    private ExtraPayoutAttributeType(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public static ExtraPayoutAttributeType fromServerKey(String serverKey2) {
        return (ExtraPayoutAttributeType) FluentIterable.from((E[]) values()).firstMatch(ExtraPayoutAttributeType$$Lambda$1.lambdaFactory$(serverKey2)).mo41059or(UNKNOWN);
    }
}
