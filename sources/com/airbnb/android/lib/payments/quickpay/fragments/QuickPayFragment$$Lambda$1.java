package com.airbnb.android.lib.payments.quickpay.fragments;

import com.braintreepayments.api.interfaces.BraintreeResponseListener;

final /* synthetic */ class QuickPayFragment$$Lambda$1 implements BraintreeResponseListener {
    private final QuickPayFragment arg$1;

    private QuickPayFragment$$Lambda$1(QuickPayFragment quickPayFragment) {
        this.arg$1 = quickPayFragment;
    }

    public static BraintreeResponseListener lambdaFactory$(QuickPayFragment quickPayFragment) {
        return new QuickPayFragment$$Lambda$1(quickPayFragment);
    }

    public void onResponse(Object obj) {
        QuickPayFragment.lambda$onPaymentOptionsRequestSuccess$0(this.arg$1, (Boolean) obj);
    }
}
