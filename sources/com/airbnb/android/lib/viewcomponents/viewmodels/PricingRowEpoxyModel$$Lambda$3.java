package com.airbnb.android.lib.viewcomponents.viewmodels;

import com.airbnb.android.core.models.Price;
import com.google.common.base.Predicate;

final /* synthetic */ class PricingRowEpoxyModel$$Lambda$3 implements Predicate {
    private static final PricingRowEpoxyModel$$Lambda$3 instance = new PricingRowEpoxyModel$$Lambda$3();

    private PricingRowEpoxyModel$$Lambda$3() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return PricingRowEpoxyModel.lambda$getCouponCodePriceItem$2((Price) obj);
    }
}
