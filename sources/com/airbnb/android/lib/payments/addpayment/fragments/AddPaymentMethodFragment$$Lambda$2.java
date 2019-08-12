package com.airbnb.android.lib.payments.addpayment.fragments;

import com.braintreepayments.api.interfaces.BraintreeCancelListener;

final /* synthetic */ class AddPaymentMethodFragment$$Lambda$2 implements BraintreeCancelListener {
    private final AddPaymentMethodFragment arg$1;

    private AddPaymentMethodFragment$$Lambda$2(AddPaymentMethodFragment addPaymentMethodFragment) {
        this.arg$1 = addPaymentMethodFragment;
    }

    public static BraintreeCancelListener lambdaFactory$(AddPaymentMethodFragment addPaymentMethodFragment) {
        return new AddPaymentMethodFragment$$Lambda$2(addPaymentMethodFragment);
    }

    public void onCancel(int i) {
        this.arg$1.showLoading(false);
    }
}
