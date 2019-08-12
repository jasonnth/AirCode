package com.airbnb.android.managelisting.settings;

import p032rx.functions.Action1;

final /* synthetic */ class ManageListingDataController$$Lambda$5 implements Action1 {
    private static final ManageListingDataController$$Lambda$5 instance = new ManageListingDataController$$Lambda$5();

    private ManageListingDataController$$Lambda$5() {
    }

    public static Action1 lambdaFactory$() {
        return instance;
    }

    public void call(Object obj) {
        ((UpdateListener) obj).dataUpdated();
    }
}
