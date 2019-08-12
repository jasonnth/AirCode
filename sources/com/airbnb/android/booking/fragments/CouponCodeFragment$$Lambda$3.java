package com.airbnb.android.booking.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class CouponCodeFragment$$Lambda$3 implements OnClickListener {
    private final CouponCodeFragment arg$1;

    private CouponCodeFragment$$Lambda$3(CouponCodeFragment couponCodeFragment) {
        this.arg$1 = couponCodeFragment;
    }

    public static OnClickListener lambdaFactory$(CouponCodeFragment couponCodeFragment) {
        return new CouponCodeFragment$$Lambda$3(couponCodeFragment);
    }

    public void onClick(View view) {
        this.arg$1.deleteCoupon();
    }
}
