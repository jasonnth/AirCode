package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingDiscountsSettingsFragment$$Lambda$5 implements Action1 {
    private final ManageListingDiscountsSettingsFragment arg$1;

    private ManageListingDiscountsSettingsFragment$$Lambda$5(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        this.arg$1 = manageListingDiscountsSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        return new ManageListingDiscountsSettingsFragment$$Lambda$5(manageListingDiscountsSettingsFragment);
    }

    public void call(Object obj) {
        ManageListingDiscountsSettingsFragment.lambda$new$4(this.arg$1, (AirRequestNetworkException) obj);
    }
}
