package com.airbnb.android.booking.fragments;

import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.models.Configuration;

final /* synthetic */ class PaymentManagerFragment$$Lambda$4 implements ConfigurationListener {
    private final PaymentManagerFragment arg$1;

    private PaymentManagerFragment$$Lambda$4(PaymentManagerFragment paymentManagerFragment) {
        this.arg$1 = paymentManagerFragment;
    }

    public static ConfigurationListener lambdaFactory$(PaymentManagerFragment paymentManagerFragment) {
        return new PaymentManagerFragment$$Lambda$4(paymentManagerFragment);
    }

    public void onConfigurationFetched(Configuration configuration) {
        this.arg$1.isPayPalEnabled = configuration.isPayPalEnabled();
    }
}
