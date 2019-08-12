package com.airbnb.android.payout.create.fragments;

import com.airbnb.android.payout.models.PayoutFormFieldInputWrapper;
import com.google.common.base.Predicate;

final /* synthetic */ class AddPayoutAccountInfoFragment$$Lambda$2 implements Predicate {
    private static final AddPayoutAccountInfoFragment$$Lambda$2 instance = new AddPayoutAccountInfoFragment$$Lambda$2();

    private AddPayoutAccountInfoFragment$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return AddPayoutAccountInfoFragment.lambda$hasHelpContent$1((PayoutFormFieldInputWrapper) obj);
    }
}
