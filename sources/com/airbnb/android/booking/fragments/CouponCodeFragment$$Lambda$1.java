package com.airbnb.android.booking.fragments;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CouponCodeFragment$$Lambda$1 implements Action1 {
    private final CouponCodeFragment arg$1;

    private CouponCodeFragment$$Lambda$1(CouponCodeFragment couponCodeFragment) {
        this.arg$1 = couponCodeFragment;
    }

    public static Action1 lambdaFactory$(CouponCodeFragment couponCodeFragment) {
        return new CouponCodeFragment$$Lambda$1(couponCodeFragment);
    }

    public void call(Object obj) {
        this.arg$1.getBookingActivity().doneWithCouponCode(((ReservationResponse) obj).reservation);
    }
}
