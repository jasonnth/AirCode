package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class PayPalPayoutFragment$$Lambda$1 implements Action1 {
    private final PayPalPayoutFragment arg$1;

    private PayPalPayoutFragment$$Lambda$1(PayPalPayoutFragment payPalPayoutFragment) {
        this.arg$1 = payPalPayoutFragment;
    }

    public static Action1 lambdaFactory$(PayPalPayoutFragment payPalPayoutFragment) {
        return new PayPalPayoutFragment$$Lambda$1(payPalPayoutFragment);
    }

    public void call(Object obj) {
        PayPalPayoutFragment.lambda$new$0(this.arg$1, (PaymentInstrumentResponse) obj);
    }
}
