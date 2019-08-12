package com.airbnb.android.lib.viewcomponents.viewmodels;

import com.airbnb.android.core.models.Price;
import com.google.common.base.Predicate;

final /* synthetic */ class PricingRowEpoxyModel$$Lambda$1 implements Predicate {
    private static final PricingRowEpoxyModel$$Lambda$1 instance = new PricingRowEpoxyModel$$Lambda$1();

    private PricingRowEpoxyModel$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return PricingRowEpoxyModel.lambda$getPriceItems$0((Price) obj);
    }
}
