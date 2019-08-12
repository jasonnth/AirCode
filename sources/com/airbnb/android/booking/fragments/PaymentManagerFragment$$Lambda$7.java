package com.airbnb.android.booking.fragments;

import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.google.android.gms.common.api.GoogleApiClient;

final /* synthetic */ class PaymentManagerFragment$$Lambda$7 implements BraintreeResponseListener {
    private final PaymentManagerFragment arg$1;

    private PaymentManagerFragment$$Lambda$7(PaymentManagerFragment paymentManagerFragment) {
        this.arg$1 = paymentManagerFragment;
    }

    public static BraintreeResponseListener lambdaFactory$(PaymentManagerFragment paymentManagerFragment) {
        return new PaymentManagerFragment$$Lambda$7(paymentManagerFragment);
    }

    public void onResponse(Object obj) {
        PaymentManagerFragment.lambda$createGoogleApiClient$4(this.arg$1, (GoogleApiClient) obj);
    }
}
