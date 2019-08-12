package com.airbnb.android.lib.viewcomponents.viewmodels;

import com.airbnb.android.core.models.Price;
import com.google.common.base.Function;

final /* synthetic */ class PricingRowEpoxyModel$$Lambda$2 implements Function {
    private static final PricingRowEpoxyModel$$Lambda$2 instance = new PricingRowEpoxyModel$$Lambda$2();

    private PricingRowEpoxyModel$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return PricingRowEpoxyModel.lambda$getPriceItems$1((Price) obj);
    }
}
