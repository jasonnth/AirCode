package com.airbnb.android.listing.adapters;

import com.airbnb.android.core.models.Currency;
import com.airbnb.android.utils.ListUtils.Condition;

final /* synthetic */ class ListingCurrencyAdapter$$Lambda$1 implements Condition {
    private final ListingCurrencyAdapter arg$1;

    private ListingCurrencyAdapter$$Lambda$1(ListingCurrencyAdapter listingCurrencyAdapter) {
        this.arg$1 = listingCurrencyAdapter;
    }

    public static Condition lambdaFactory$(ListingCurrencyAdapter listingCurrencyAdapter) {
        return new ListingCurrencyAdapter$$Lambda$1(listingCurrencyAdapter);
    }

    public boolean check(Object obj) {
        return ((Currency) obj).getCode().equals(this.arg$1.currentCurrencyCode);
    }
}
