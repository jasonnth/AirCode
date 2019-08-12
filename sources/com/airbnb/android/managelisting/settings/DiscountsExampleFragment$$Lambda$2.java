package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class DiscountsExampleFragment$$Lambda$2 implements Action1 {
    private final DiscountsExampleFragment arg$1;

    private DiscountsExampleFragment$$Lambda$2(DiscountsExampleFragment discountsExampleFragment) {
        this.arg$1 = discountsExampleFragment;
    }

    public static Action1 lambdaFactory$(DiscountsExampleFragment discountsExampleFragment) {
        return new DiscountsExampleFragment$$Lambda$2(discountsExampleFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowRetryableErrorWithSnackbar(this.arg$1.getView(), (NetworkException) (AirRequestNetworkException) obj, DiscountsExampleFragment$$Lambda$3.lambdaFactory$(this.arg$1));
    }
}
