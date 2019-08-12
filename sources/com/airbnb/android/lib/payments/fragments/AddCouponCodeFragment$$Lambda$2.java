package com.airbnb.android.lib.payments.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AddCouponCodeFragment$$Lambda$2 implements Action1 {
    private final AddCouponCodeFragment arg$1;

    private AddCouponCodeFragment$$Lambda$2(AddCouponCodeFragment addCouponCodeFragment) {
        this.arg$1 = addCouponCodeFragment;
    }

    public static Action1 lambdaFactory$(AddCouponCodeFragment addCouponCodeFragment) {
        return new AddCouponCodeFragment$$Lambda$2(addCouponCodeFragment);
    }

    public void call(Object obj) {
        this.arg$1.onCouponCodeError((AirRequestNetworkException) obj);
    }
}
