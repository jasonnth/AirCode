package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingCurrencyFragment$$Lambda$2 implements Action1 {
    private final ManageListingCurrencyFragment arg$1;

    private ManageListingCurrencyFragment$$Lambda$2(ManageListingCurrencyFragment manageListingCurrencyFragment) {
        this.arg$1 = manageListingCurrencyFragment;
    }

    public static Action1 lambdaFactory$(ManageListingCurrencyFragment manageListingCurrencyFragment) {
        return new ManageListingCurrencyFragment$$Lambda$2(manageListingCurrencyFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowRetryableErrorWithSnackbar(this.arg$1.getView(), (NetworkException) (AirRequestNetworkException) obj, ManageListingCurrencyFragment$$Lambda$5.lambdaFactory$(this.arg$1));
    }
}
