package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.Country;
import com.airbnb.android.utils.ListUtils.Condition;

final /* synthetic */ class ListingCountryAdapter$$Lambda$1 implements Condition {
    private final ListingCountryAdapter arg$1;

    private ListingCountryAdapter$$Lambda$1(ListingCountryAdapter listingCountryAdapter) {
        this.arg$1 = listingCountryAdapter;
    }

    public static Condition lambdaFactory$(ListingCountryAdapter listingCountryAdapter) {
        return new ListingCountryAdapter$$Lambda$1(listingCountryAdapter);
    }

    public boolean check(Object obj) {
        return ((Country) obj).equals(this.arg$1.currentCountry);
    }
}
