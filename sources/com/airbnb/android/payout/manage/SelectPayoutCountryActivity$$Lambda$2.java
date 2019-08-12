package com.airbnb.android.payout.manage;

import com.airbnb.android.core.models.Country;
import com.google.common.base.Predicate;

final /* synthetic */ class SelectPayoutCountryActivity$$Lambda$2 implements Predicate {
    private final String arg$1;

    private SelectPayoutCountryActivity$$Lambda$2(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new SelectPayoutCountryActivity$$Lambda$2(str);
    }

    public boolean apply(Object obj) {
        return ((Country) obj).getAlpha_2().equals(this.arg$1);
    }
}
