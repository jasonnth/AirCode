package com.airbnb.android.lib.payments.paymentoptions.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.lib.payments.paymentoptions.adapters.PaymentOptionsAdapter.PaymentOptionsAdapterListener;

final /* synthetic */ class PaymentOptionsAdapterFactory$$Lambda$3 implements OnClickListener {
    private final PaymentOptionsAdapterListener arg$1;
    private final PaymentOption arg$2;

    private PaymentOptionsAdapterFactory$$Lambda$3(PaymentOptionsAdapterListener paymentOptionsAdapterListener, PaymentOption paymentOption) {
        this.arg$1 = paymentOptionsAdapterListener;
        this.arg$2 = paymentOption;
    }

    public static OnClickListener lambdaFactory$(PaymentOptionsAdapterListener paymentOptionsAdapterListener, PaymentOption paymentOption) {
        return new PaymentOptionsAdapterFactory$$Lambda$3(paymentOptionsAdapterListener, paymentOption);
    }

    public void onClick(View view) {
        this.arg$1.onPaymentOptionSelected(this.arg$2);
    }
}
