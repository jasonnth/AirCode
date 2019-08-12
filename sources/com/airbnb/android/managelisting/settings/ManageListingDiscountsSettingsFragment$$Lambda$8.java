package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingDiscountsSettingsFragment$$Lambda$8 implements Action1 {
    private final ManageListingDiscountsSettingsFragment arg$1;

    private ManageListingDiscountsSettingsFragment$$Lambda$8(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        this.arg$1 = manageListingDiscountsSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        return new ManageListingDiscountsSettingsFragment$$Lambda$8(manageListingDiscountsSettingsFragment);
    }

    public void call(Object obj) {
        ManageListingDiscountsSettingsFragment.lambda$new$7(this.arg$1, (AirRequestNetworkException) obj);
    }
}
