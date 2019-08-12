package com.airbnb.android.lib.payments.addpayment.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.payments.models.PaymentMethodType;
import com.airbnb.android.lib.payments.addpayment.clicklisteners.AddPaymentMethodListener;

final /* synthetic */ class AddPaymentMethodEpoxyController$$Lambda$2 implements OnClickListener {
    private final AddPaymentMethodListener arg$1;
    private final PaymentMethodType arg$2;

    private AddPaymentMethodEpoxyController$$Lambda$2(AddPaymentMethodListener addPaymentMethodListener, PaymentMethodType paymentMethodType) {
        this.arg$1 = addPaymentMethodListener;
        this.arg$2 = paymentMethodType;
    }

    public static OnClickListener lambdaFactory$(AddPaymentMethodListener addPaymentMethodListener, PaymentMethodType paymentMethodType) {
        return new AddPaymentMethodEpoxyController$$Lambda$2(addPaymentMethodListener, paymentMethodType);
    }

    public void onClick(View view) {
        this.arg$1.onPaymentMethodSelected(this.arg$2);
    }
}
