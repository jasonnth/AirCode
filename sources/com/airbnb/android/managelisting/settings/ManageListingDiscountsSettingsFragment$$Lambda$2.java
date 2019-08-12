package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingDiscountsSettingsFragment$$Lambda$2 implements Action1 {
    private final ManageListingDiscountsSettingsFragment arg$1;

    private ManageListingDiscountsSettingsFragment$$Lambda$2(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        this.arg$1 = manageListingDiscountsSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        return new ManageListingDiscountsSettingsFragment$$Lambda$2(manageListingDiscountsSettingsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
