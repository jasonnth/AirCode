package com.airbnb.android.lib.viewcomponents.viewmodels;

import com.airbnb.android.core.models.Price;
import com.google.common.base.Function;

final /* synthetic */ class PricingRowEpoxyModel$$Lambda$4 implements Function {
    private static final PricingRowEpoxyModel$$Lambda$4 instance = new PricingRowEpoxyModel$$Lambda$4();

    private PricingRowEpoxyModel$$Lambda$4() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return PricingRowEpoxyModel.lambda$getCouponCodePriceItem$3((Price) obj);
    }
}
