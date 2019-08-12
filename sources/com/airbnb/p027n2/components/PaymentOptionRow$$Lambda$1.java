package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.PaymentOptionRow$$Lambda$1 */
final /* synthetic */ class PaymentOptionRow$$Lambda$1 implements OnClickListener {
    private final PaymentOptionRow arg$1;

    private PaymentOptionRow$$Lambda$1(PaymentOptionRow paymentOptionRow) {
        this.arg$1 = paymentOptionRow;
    }

    public static OnClickListener lambdaFactory$(PaymentOptionRow paymentOptionRow) {
        return new PaymentOptionRow$$Lambda$1(paymentOptionRow);
    }

    public void onClick(View view) {
        PaymentOptionRow.lambda$mock$0(this.arg$1, view);
    }
}
