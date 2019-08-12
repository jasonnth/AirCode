package com.airbnb.android.booking.fragments.redirectpay;

import p032rx.functions.Action1;

final /* synthetic */ class RedirectPayResultHandler$$Lambda$2 implements Action1 {
    private final RedirectPayResultHandler arg$1;

    private RedirectPayResultHandler$$Lambda$2(RedirectPayResultHandler redirectPayResultHandler) {
        this.arg$1 = redirectPayResultHandler;
    }

    public static Action1 lambdaFactory$(RedirectPayResultHandler redirectPayResultHandler) {
        return new RedirectPayResultHandler$$Lambda$2(redirectPayResultHandler);
    }

    public void call(Object obj) {
        this.arg$1.queryPaymentStatus();
    }
}
