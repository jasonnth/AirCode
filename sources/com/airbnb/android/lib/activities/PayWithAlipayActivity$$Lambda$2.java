package com.airbnb.android.lib.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PayWithAlipayActivity$$Lambda$2 implements Action1 {
    private final PayWithAlipayActivity arg$1;

    private PayWithAlipayActivity$$Lambda$2(PayWithAlipayActivity payWithAlipayActivity) {
        this.arg$1 = payWithAlipayActivity;
    }

    public static Action1 lambdaFactory$(PayWithAlipayActivity payWithAlipayActivity) {
        return new PayWithAlipayActivity$$Lambda$2(payWithAlipayActivity);
    }

    public void call(Object obj) {
        PayWithAlipayActivity.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
