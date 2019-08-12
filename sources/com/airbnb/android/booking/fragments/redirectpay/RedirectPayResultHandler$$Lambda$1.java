package com.airbnb.android.booking.fragments.redirectpay;

import p032rx.functions.Action1;

final /* synthetic */ class RedirectPayResultHandler$$Lambda$1 implements Action1 {
    private final RedirectPayResultHandler arg$1;

    private RedirectPayResultHandler$$Lambda$1(RedirectPayResultHandler redirectPayResultHandler) {
        this.arg$1 = redirectPayResultHandler;
    }

    public static Action1 lambdaFactory$(RedirectPayResultHandler redirectPayResultHandler) {
        return new RedirectPayResultHandler$$Lambda$1(redirectPayResultHandler);
    }

    public void call(Object obj) {
        RedirectPayResultHandler.lambda$startPolling$0(this.arg$1, (Long) obj);
    }
}
