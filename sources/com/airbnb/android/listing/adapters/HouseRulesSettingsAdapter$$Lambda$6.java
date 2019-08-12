package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.ListingExpectation;
import com.google.common.base.Function;

final /* synthetic */ class HouseRulesSettingsAdapter$$Lambda$6 implements Function {
    private static final HouseRulesSettingsAdapter$$Lambda$6 instance = new HouseRulesSettingsAdapter$$Lambda$6();

    private HouseRulesSettingsAdapter$$Lambda$6() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((ListingExpectation) obj).getTitle();
    }
}
