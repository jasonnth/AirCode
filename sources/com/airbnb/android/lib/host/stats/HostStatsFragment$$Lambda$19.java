package com.airbnb.android.lib.host.stats;

import com.airbnb.android.core.models.CurrencyAmount;
import com.google.common.base.Predicate;

final /* synthetic */ class HostStatsFragment$$Lambda$19 implements Predicate {
    private final CurrencyAmount arg$1;

    private HostStatsFragment$$Lambda$19(CurrencyAmount currencyAmount) {
        this.arg$1 = currencyAmount;
    }

    public static Predicate lambdaFactory$(CurrencyAmount currencyAmount) {
        return new HostStatsFragment$$Lambda$19(currencyAmount);
    }

    public boolean apply(Object obj) {
        return ((CurrencyAmount) obj).getCurrency().equals(this.arg$1.getCurrency());
    }
}
