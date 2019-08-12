package com.airbnb.android.core.enums;

import com.google.common.base.Predicate;

final /* synthetic */ class UrgencyMessageType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private UrgencyMessageType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new UrgencyMessageType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((UrgencyMessageType) obj).serverKey.equalsIgnoreCase(this.arg$1);
    }
}
