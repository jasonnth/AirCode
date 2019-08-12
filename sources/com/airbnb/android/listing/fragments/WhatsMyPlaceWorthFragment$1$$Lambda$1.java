package com.airbnb.android.listing.fragments;

import com.airbnb.android.listing.utils.ListingTextUtils;
import p032rx.functions.Func1;

final /* synthetic */ class WhatsMyPlaceWorthFragment$1$$Lambda$1 implements Func1 {
    private final C72411 arg$1;

    private WhatsMyPlaceWorthFragment$1$$Lambda$1(C72411 r1) {
        this.arg$1 = r1;
    }

    public static Func1 lambdaFactory$(C72411 r1) {
        return new WhatsMyPlaceWorthFragment$1$$Lambda$1(r1);
    }

    public Object call(Object obj) {
        return ListingTextUtils.createCapacityCount(WhatsMyPlaceWorthFragment.this.getContext(), ((Integer) obj).intValue());
    }
}
