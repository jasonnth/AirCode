package com.airbnb.android.lib.payments.paymentoptions.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.payments.paymentoptions.adapters.PaymentOptionsAdapter.PaymentOptionsAdapterListener;

final /* synthetic */ class PaymentOptionsAdapterFactory$$Lambda$2 implements OnClickListener {
    private final PaymentOptionsAdapterListener arg$1;

    private PaymentOptionsAdapterFactory$$Lambda$2(PaymentOptionsAdapterListener paymentOptionsAdapterListener) {
        this.arg$1 = paymentOptionsAdapterListener;
    }

    public static OnClickListener lambdaFactory$(PaymentOptionsAdapterListener paymentOptionsAdapterListener) {
        return new PaymentOptionsAdapterFactory$$Lambda$2(paymentOptionsAdapterListener);
    }

    public void onClick(View view) {
        this.arg$1.onAddPaymentMethodSelected();
    }
}
