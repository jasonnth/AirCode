package com.airbnb.android.listing.fragments;

import com.airbnb.android.core.models.Country;
import com.google.common.base.Predicate;

final /* synthetic */ class CountryFragment$$Lambda$4 implements Predicate {
    private final CountryFragment arg$1;

    private CountryFragment$$Lambda$4(CountryFragment countryFragment) {
        this.arg$1 = countryFragment;
    }

    public static Predicate lambdaFactory$(CountryFragment countryFragment) {
        return new CountryFragment$$Lambda$4(countryFragment);
    }

    public boolean apply(Object obj) {
        return ((Country) obj).getAlpha_2().equals(this.arg$1.currentCountryCode);
    }
}
