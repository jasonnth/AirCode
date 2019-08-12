package com.airbnb.android.lib.payments.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class PaymentOptionsFragment$$Lambda$2 implements Action1 {
    private final PaymentOptionsFragment arg$1;

    private PaymentOptionsFragment$$Lambda$2(PaymentOptionsFragment paymentOptionsFragment) {
        this.arg$1 = paymentOptionsFragment;
    }

    public static Action1 lambdaFactory$(PaymentOptionsFragment paymentOptionsFragment) {
        return new PaymentOptionsFragment$$Lambda$2(paymentOptionsFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowErrorWithSnackbar(this.arg$1.getView(), (AirRequestNetworkException) obj);
    }
}
