package com.airbnb.android.booking.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CouponCodeFragment$$Lambda$2 implements Action1 {
    private final CouponCodeFragment arg$1;

    private CouponCodeFragment$$Lambda$2(CouponCodeFragment couponCodeFragment) {
        this.arg$1 = couponCodeFragment;
    }

    public static Action1 lambdaFactory$(CouponCodeFragment couponCodeFragment) {
        return new CouponCodeFragment$$Lambda$2(couponCodeFragment);
    }

    public void call(Object obj) {
        CouponCodeFragment.lambda$new$2(this.arg$1, (AirRequestNetworkException) obj);
    }
}
