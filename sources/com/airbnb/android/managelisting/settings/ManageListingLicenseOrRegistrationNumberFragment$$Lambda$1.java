package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingLicenseOrRegistrationNumberFragment$$Lambda$1 implements Action1 {
    private final ManageListingLicenseOrRegistrationNumberFragment arg$1;

    private ManageListingLicenseOrRegistrationNumberFragment$$Lambda$1(ManageListingLicenseOrRegistrationNumberFragment manageListingLicenseOrRegistrationNumberFragment) {
        this.arg$1 = manageListingLicenseOrRegistrationNumberFragment;
    }

    public static Action1 lambdaFactory$(ManageListingLicenseOrRegistrationNumberFragment manageListingLicenseOrRegistrationNumberFragment) {
        return new ManageListingLicenseOrRegistrationNumberFragment$$Lambda$1(manageListingLicenseOrRegistrationNumberFragment);
    }

    public void call(Object obj) {
        ManageListingLicenseOrRegistrationNumberFragment.lambda$new$0(this.arg$1, (SimpleListingResponse) obj);
    }
}
