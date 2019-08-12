package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingLicenseOrRegistrationNumberFragment$$Lambda$2 implements Action1 {
    private final ManageListingLicenseOrRegistrationNumberFragment arg$1;

    private ManageListingLicenseOrRegistrationNumberFragment$$Lambda$2(ManageListingLicenseOrRegistrationNumberFragment manageListingLicenseOrRegistrationNumberFragment) {
        this.arg$1 = manageListingLicenseOrRegistrationNumberFragment;
    }

    public static Action1 lambdaFactory$(ManageListingLicenseOrRegistrationNumberFragment manageListingLicenseOrRegistrationNumberFragment) {
        return new ManageListingLicenseOrRegistrationNumberFragment$$Lambda$2(manageListingLicenseOrRegistrationNumberFragment);
    }

    public void call(Object obj) {
        ManageListingLicenseOrRegistrationNumberFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
