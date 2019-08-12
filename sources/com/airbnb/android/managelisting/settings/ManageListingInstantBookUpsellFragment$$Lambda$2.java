package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingInstantBookUpsellFragment$$Lambda$2 implements Action1 {
    private final ManageListingInstantBookUpsellFragment arg$1;

    private ManageListingInstantBookUpsellFragment$$Lambda$2(ManageListingInstantBookUpsellFragment manageListingInstantBookUpsellFragment) {
        this.arg$1 = manageListingInstantBookUpsellFragment;
    }

    public static Action1 lambdaFactory$(ManageListingInstantBookUpsellFragment manageListingInstantBookUpsellFragment) {
        return new ManageListingInstantBookUpsellFragment$$Lambda$2(manageListingInstantBookUpsellFragment);
    }

    public void call(Object obj) {
        ManageListingInstantBookUpsellFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
