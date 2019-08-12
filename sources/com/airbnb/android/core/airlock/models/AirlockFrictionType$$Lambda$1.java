package com.airbnb.android.core.airlock.models;

import com.google.common.base.Predicate;

final /* synthetic */ class AirlockFrictionType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private AirlockFrictionType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new AirlockFrictionType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((AirlockFrictionType) obj).getServerKey().equals(this.arg$1);
    }
}
