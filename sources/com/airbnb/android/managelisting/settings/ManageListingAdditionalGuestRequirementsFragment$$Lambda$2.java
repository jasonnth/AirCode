package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingAdditionalGuestRequirementsFragment$$Lambda$2 implements Action1 {
    private final ManageListingAdditionalGuestRequirementsFragment arg$1;

    private ManageListingAdditionalGuestRequirementsFragment$$Lambda$2(ManageListingAdditionalGuestRequirementsFragment manageListingAdditionalGuestRequirementsFragment) {
        this.arg$1 = manageListingAdditionalGuestRequirementsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingAdditionalGuestRequirementsFragment manageListingAdditionalGuestRequirementsFragment) {
        return new ManageListingAdditionalGuestRequirementsFragment$$Lambda$2(manageListingAdditionalGuestRequirementsFragment);
    }

    public void call(Object obj) {
        ManageListingAdditionalGuestRequirementsFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
