package com.airbnb.android.lib.adapters;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PaymentInfoAdapter$$Lambda$1 implements OnClickListener {
    private final PaymentInfoAdapter arg$1;

    private PaymentInfoAdapter$$Lambda$1(PaymentInfoAdapter paymentInfoAdapter) {
        this.arg$1 = paymentInfoAdapter;
    }

    public static OnClickListener lambdaFactory$(PaymentInfoAdapter paymentInfoAdapter) {
        return new PaymentInfoAdapter$$Lambda$1(paymentInfoAdapter);
    }

    public void onClick(View view) {
        this.arg$1.adapterInterface.onPayoutsClicked();
    }
}
