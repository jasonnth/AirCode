package com.airbnb.android.managelisting.settings;

import com.airbnb.android.listing.utils.ListingTextUtils;
import p032rx.functions.Func1;

final /* synthetic */ class ManageListingFeesAdapter$$Lambda$6 implements Func1 {
    private final ManageListingFeesAdapter arg$1;

    private ManageListingFeesAdapter$$Lambda$6(ManageListingFeesAdapter manageListingFeesAdapter) {
        this.arg$1 = manageListingFeesAdapter;
    }

    public static Func1 lambdaFactory$(ManageListingFeesAdapter manageListingFeesAdapter) {
        return new ManageListingFeesAdapter$$Lambda$6(manageListingFeesAdapter);
    }

    public Object call(Object obj) {
        return ListingTextUtils.createGuestsCount(this.arg$1.context, ((Integer) obj).intValue());
    }
}
