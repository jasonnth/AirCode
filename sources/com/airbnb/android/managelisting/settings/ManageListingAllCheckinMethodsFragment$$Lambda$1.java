package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.AirBatchResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingAllCheckinMethodsFragment$$Lambda$1 implements Action1 {
    private final ManageListingAllCheckinMethodsFragment arg$1;

    private ManageListingAllCheckinMethodsFragment$$Lambda$1(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment) {
        this.arg$1 = manageListingAllCheckinMethodsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment) {
        return new ManageListingAllCheckinMethodsFragment$$Lambda$1(manageListingAllCheckinMethodsFragment);
    }

    public void call(Object obj) {
        ManageListingAllCheckinMethodsFragment.lambda$new$0(this.arg$1, (AirBatchResponse) obj);
    }
}
