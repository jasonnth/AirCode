package com.airbnb.android.core.requests;

import com.airbnb.android.core.models.Currency;
import com.airbnb.android.utils.AirbnbConstants;
import com.google.common.base.Predicate;

final /* synthetic */ class CurrenciesRequest$$Lambda$1 implements Predicate {
    private static final CurrenciesRequest$$Lambda$1 instance = new CurrenciesRequest$$Lambda$1();

    private CurrenciesRequest$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return AirbnbConstants.CURRENCY_INDIA.equals(((Currency) obj).getCode());
    }
}
