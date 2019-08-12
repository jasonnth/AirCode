package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class PayPalPayoutFragment$$Lambda$2 implements Action1 {
    private final PayPalPayoutFragment arg$1;

    private PayPalPayoutFragment$$Lambda$2(PayPalPayoutFragment payPalPayoutFragment) {
        this.arg$1 = payPalPayoutFragment;
    }

    public static Action1 lambdaFactory$(PayPalPayoutFragment payPalPayoutFragment) {
        return new PayPalPayoutFragment$$Lambda$2(payPalPayoutFragment);
    }

    public void call(Object obj) {
        PayPalPayoutFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
