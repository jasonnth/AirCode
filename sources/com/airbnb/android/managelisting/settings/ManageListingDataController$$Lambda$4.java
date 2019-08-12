package com.airbnb.android.managelisting.settings;

import p032rx.functions.Action1;

final /* synthetic */ class ManageListingDataController$$Lambda$4 implements Action1 {
    private final boolean arg$1;

    private ManageListingDataController$$Lambda$4(boolean z) {
        this.arg$1 = z;
    }

    public static Action1 lambdaFactory$(boolean z) {
        return new ManageListingDataController$$Lambda$4(z);
    }

    public void call(Object obj) {
        ((UpdateListener) obj).dataLoading(this.arg$1);
    }
}
