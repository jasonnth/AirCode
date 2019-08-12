package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.ListingExpectation;
import com.google.common.base.Predicate;

final /* synthetic */ class HouseRulesSettingsAdapter$$Lambda$5 implements Predicate {
    private static final HouseRulesSettingsAdapter$$Lambda$5 instance = new HouseRulesSettingsAdapter$$Lambda$5();

    private HouseRulesSettingsAdapter$$Lambda$5() {
    }

    public static Predicate lambdaFactory$() {
        return instance;
    }

    public boolean apply(Object obj) {
        return ((ListingExpectation) obj).isChecked();
    }
}
