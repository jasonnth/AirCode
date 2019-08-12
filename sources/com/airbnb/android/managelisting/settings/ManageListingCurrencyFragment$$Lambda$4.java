package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCurrencyFragment$$Lambda$4 implements Action1 {
    private final ManageListingCurrencyFragment arg$1;

    private ManageListingCurrencyFragment$$Lambda$4(ManageListingCurrencyFragment manageListingCurrencyFragment) {
        this.arg$1 = manageListingCurrencyFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCurrencyFragment manageListingCurrencyFragment) {
        return new ManageListingCurrencyFragment$$Lambda$4(manageListingCurrencyFragment);
    }

    public void call(Object obj) {
        ManageListingCurrencyFragment.lambda$new$4(this.arg$1, (AirRequestNetworkException) obj);
    }
}
