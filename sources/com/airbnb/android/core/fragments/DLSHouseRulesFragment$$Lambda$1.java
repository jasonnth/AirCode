package com.airbnb.android.core.fragments;

import com.airbnb.android.core.responses.ListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class DLSHouseRulesFragment$$Lambda$1 implements Action1 {
    private final DLSHouseRulesFragment arg$1;

    private DLSHouseRulesFragment$$Lambda$1(DLSHouseRulesFragment dLSHouseRulesFragment) {
        this.arg$1 = dLSHouseRulesFragment;
    }

    public static Action1 lambdaFactory$(DLSHouseRulesFragment dLSHouseRulesFragment) {
        return new DLSHouseRulesFragment$$Lambda$1(dLSHouseRulesFragment);
    }

    public void call(Object obj) {
        this.arg$1.houseRulesEpoxyController.setTranslatedHouseRules(((ListingResponse) obj).listing.getLocalizedAdditionalHouseRulesWithGoogleTranslate());
    }
}
