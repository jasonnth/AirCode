package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingInstantBookUpsellFragment$$Lambda$1 implements Action1 {
    private final ManageListingInstantBookUpsellFragment arg$1;

    private ManageListingInstantBookUpsellFragment$$Lambda$1(ManageListingInstantBookUpsellFragment manageListingInstantBookUpsellFragment) {
        this.arg$1 = manageListingInstantBookUpsellFragment;
    }

    public static Action1 lambdaFactory$(ManageListingInstantBookUpsellFragment manageListingInstantBookUpsellFragment) {
        return new ManageListingInstantBookUpsellFragment$$Lambda$1(manageListingInstantBookUpsellFragment);
    }

    public void call(Object obj) {
        ManageListingInstantBookUpsellFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
