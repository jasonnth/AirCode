package com.airbnb.android.lib.payments.addpayment.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AddPaymentMethodEpoxyController$$Lambda$1 implements OnClickListener {
    private final AddPaymentMethodEpoxyController arg$1;

    private AddPaymentMethodEpoxyController$$Lambda$1(AddPaymentMethodEpoxyController addPaymentMethodEpoxyController) {
        this.arg$1 = addPaymentMethodEpoxyController;
    }

    public static OnClickListener lambdaFactory$(AddPaymentMethodEpoxyController addPaymentMethodEpoxyController) {
        return new AddPaymentMethodEpoxyController$$Lambda$1(addPaymentMethodEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onBillingCountryClicked();
    }
}
