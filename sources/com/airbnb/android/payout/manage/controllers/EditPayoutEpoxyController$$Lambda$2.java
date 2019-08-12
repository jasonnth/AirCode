package com.airbnb.android.payout.manage.controllers;

import com.airbnb.android.core.models.PaymentInstrument;
import com.google.common.base.Predicate;

final /* synthetic */ class EditPayoutEpoxyController$$Lambda$2 implements Predicate {
    private static final EditPayoutEpoxyController$$Lambda$2 instance = new EditPayoutEpoxyController$$Lambda$2();

    private EditPayoutEpoxyController$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((PaymentInstrument) obj).isDefaultPayout();
    }
}
