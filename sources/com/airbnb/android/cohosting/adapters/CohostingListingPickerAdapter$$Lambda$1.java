package com.airbnb.android.cohosting.adapters;

import com.airbnb.android.core.enums.ListingStatus;
import com.airbnb.android.core.models.Listing;
import com.google.common.base.Predicate;

final /* synthetic */ class CohostingListingPickerAdapter$$Lambda$1 implements Predicate {
    private final ListingStatus arg$1;

    private CohostingListingPickerAdapter$$Lambda$1(ListingStatus listingStatus) {
        this.arg$1 = listingStatus;
    }

    public static Predicate lambdaFactory$(ListingStatus listingStatus) {
        return new CohostingListingPickerAdapter$$Lambda$1(listingStatus);
    }

    public boolean apply(Object obj) {
        return CohostingListingPickerAdapter.lambda$addListingsSection$0(this.arg$1, (Listing) obj);
    }
}
