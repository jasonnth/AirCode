package com.airbnb.android.lib.services;

import p032rx.functions.Action1;

final /* synthetic */ class ListingUpdateManager$$Lambda$3 implements Action1 {
    private final ListingUpdateManager arg$1;

    private ListingUpdateManager$$Lambda$3(ListingUpdateManager listingUpdateManager) {
        this.arg$1 = listingUpdateManager;
    }

    public static Action1 lambdaFactory$(ListingUpdateManager listingUpdateManager) {
        return new ListingUpdateManager$$Lambda$3(listingUpdateManager);
    }

    public void call(Object obj) {
        ListingUpdateManager.lambda$new$2(this.arg$1, (Boolean) obj);
    }
}
