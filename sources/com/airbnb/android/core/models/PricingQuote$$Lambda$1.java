package com.airbnb.android.core.models;

import com.airbnb.android.core.models.Price.Type;
import com.google.common.base.Predicate;

final /* synthetic */ class PricingQuote$$Lambda$1 implements Predicate {
    private final Type arg$1;

    private PricingQuote$$Lambda$1(Type type) {
        this.arg$1 = type;
    }

    public static Predicate lambdaFactory$(Type type) {
        return new PricingQuote$$Lambda$1(type);
    }

    public boolean apply(Object obj) {
        return PricingQuote.lambda$hasLineItemType$0(this.arg$1, (Price) obj);
    }
}
