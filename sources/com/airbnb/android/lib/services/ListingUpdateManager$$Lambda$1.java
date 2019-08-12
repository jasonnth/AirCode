package com.airbnb.android.lib.services;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ListingUpdateManager$$Lambda$1 implements Action1 {
    private final ListingUpdateManager arg$1;

    private ListingUpdateManager$$Lambda$1(ListingUpdateManager listingUpdateManager) {
        this.arg$1 = listingUpdateManager;
    }

    public static Action1 lambdaFactory$(ListingUpdateManager listingUpdateManager) {
        return new ListingUpdateManager$$Lambda$1(listingUpdateManager);
    }

    public void call(Object obj) {
        this.arg$1.onUpdateSuccess(((SimpleListingResponse) obj).listing);
    }
}
