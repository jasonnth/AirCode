package com.airbnb.android.lib.activities;

import com.airbnb.android.core.responses.ReservationResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PayWithAlipayActivity$$Lambda$1 implements Action1 {
    private final PayWithAlipayActivity arg$1;

    private PayWithAlipayActivity$$Lambda$1(PayWithAlipayActivity payWithAlipayActivity) {
        this.arg$1 = payWithAlipayActivity;
    }

    public static Action1 lambdaFactory$(PayWithAlipayActivity payWithAlipayActivity) {
        return new PayWithAlipayActivity$$Lambda$1(payWithAlipayActivity);
    }

    public void call(Object obj) {
        PayWithAlipayActivity.lambda$new$0(this.arg$1, (ReservationResponse) obj);
    }
}
