package com.airbnb.android.listing.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class CountryFragment$$Lambda$2 implements Action1 {
    private final CountryFragment arg$1;

    private CountryFragment$$Lambda$2(CountryFragment countryFragment) {
        this.arg$1 = countryFragment;
    }

    public static Action1 lambdaFactory$(CountryFragment countryFragment) {
        return new CountryFragment$$Lambda$2(countryFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowRetryableErrorWithSnackbar(this.arg$1.getView(), (NetworkException) (AirRequestNetworkException) obj, CountryFragment$$Lambda$3.lambdaFactory$(this.arg$1));
    }
}
