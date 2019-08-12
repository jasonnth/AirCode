package com.airbnb.android.core.responses;

import com.airbnb.android.core.models.ListingPickerInfo;
import com.google.common.base.Predicate;

final /* synthetic */ class ListingPickerInfoResponse$$Lambda$1 implements Predicate {
    private static final ListingPickerInfoResponse$$Lambda$1 instance = new ListingPickerInfoResponse$$Lambda$1();

    private ListingPickerInfoResponse$$Lambda$1() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((ListingPickerInfo) obj).hasEverBeenAvailable();
    }
}
