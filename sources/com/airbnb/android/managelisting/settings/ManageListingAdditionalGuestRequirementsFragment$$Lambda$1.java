package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingAdditionalGuestRequirementsFragment$$Lambda$1 implements Action1 {
    private final ManageListingAdditionalGuestRequirementsFragment arg$1;

    private ManageListingAdditionalGuestRequirementsFragment$$Lambda$1(ManageListingAdditionalGuestRequirementsFragment manageListingAdditionalGuestRequirementsFragment) {
        this.arg$1 = manageListingAdditionalGuestRequirementsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingAdditionalGuestRequirementsFragment manageListingAdditionalGuestRequirementsFragment) {
        return new ManageListingAdditionalGuestRequirementsFragment$$Lambda$1(manageListingAdditionalGuestRequirementsFragment);
    }

    public void call(Object obj) {
        ManageListingAdditionalGuestRequirementsFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
