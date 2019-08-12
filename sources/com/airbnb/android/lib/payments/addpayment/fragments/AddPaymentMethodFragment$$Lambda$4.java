package com.airbnb.android.lib.payments.addpayment.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AddPaymentMethodFragment$$Lambda$4 implements OnClickListener {
    private final AddPaymentMethodFragment arg$1;

    private AddPaymentMethodFragment$$Lambda$4(AddPaymentMethodFragment addPaymentMethodFragment) {
        this.arg$1 = addPaymentMethodFragment;
    }

    public static OnClickListener lambdaFactory$(AddPaymentMethodFragment addPaymentMethodFragment) {
        return new AddPaymentMethodFragment$$Lambda$4(addPaymentMethodFragment);
    }

    public void onClick(View view) {
        this.arg$1.loadPaymentOptions();
    }
}
