package com.airbnb.android.lib.businesstravel.models;

import com.google.common.base.Predicate;

final /* synthetic */ class IntentPredictionType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private IntentPredictionType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new IntentPredictionType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((IntentPredictionType) obj).serverKey.equals(this.arg$1);
    }
}
