package com.airbnb.android.payout.models;

import com.google.common.base.Predicate;

final /* synthetic */ class ExtraPayoutAttributeType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private ExtraPayoutAttributeType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new ExtraPayoutAttributeType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((ExtraPayoutAttributeType) obj).serverKey.equals(this.arg$1);
    }
}
