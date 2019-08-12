package com.airbnb.android.core.host;

import com.airbnb.android.core.models.ListingPickerInfo;
import com.google.common.base.Predicate;

final /* synthetic */ class ListingPromoController$$Lambda$2 implements Predicate {
    private static final ListingPromoController$$Lambda$2 instance = new ListingPromoController$$Lambda$2();

    private ListingPromoController$$Lambda$2() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ListingPromoController.lambda$null$0((ListingPickerInfo) obj);
    }
}
