package com.airbnb.android.payout.create.controllers;

import com.airbnb.android.payout.models.PayoutInfoForm;
import com.google.common.base.Predicate;

final /* synthetic */ class ChoosePayoutMethodEpoxyController$$Lambda$1 implements Predicate {
    private final String arg$1;

    private ChoosePayoutMethodEpoxyController$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new ChoosePayoutMethodEpoxyController$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((PayoutInfoForm) obj).currencies().contains(this.arg$1);
    }
}
