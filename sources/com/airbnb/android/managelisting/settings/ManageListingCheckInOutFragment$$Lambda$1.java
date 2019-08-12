package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCheckInOutFragment$$Lambda$1 implements Action1 {
    private final ManageListingCheckInOutFragment arg$1;

    private ManageListingCheckInOutFragment$$Lambda$1(ManageListingCheckInOutFragment manageListingCheckInOutFragment) {
        this.arg$1 = manageListingCheckInOutFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCheckInOutFragment manageListingCheckInOutFragment) {
        return new ManageListingCheckInOutFragment$$Lambda$1(manageListingCheckInOutFragment);
    }

    public void call(Object obj) {
        ManageListingCheckInOutFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
