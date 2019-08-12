package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.PaymentInstrument;

final /* synthetic */ class PayoutListFragment$PayoutsAdapter$$Lambda$1 implements OnClickListener {
    private final PayoutsAdapter arg$1;
    private final PaymentInstrument arg$2;

    private PayoutListFragment$PayoutsAdapter$$Lambda$1(PayoutsAdapter payoutsAdapter, PaymentInstrument paymentInstrument) {
        this.arg$1 = payoutsAdapter;
        this.arg$2 = paymentInstrument;
    }

    public static OnClickListener lambdaFactory$(PayoutsAdapter payoutsAdapter, PaymentInstrument paymentInstrument) {
        return new PayoutListFragment$PayoutsAdapter$$Lambda$1(payoutsAdapter, paymentInstrument);
    }

    public void onClick(View view) {
        PayoutListFragment.this.createActionListDialog(this.arg$1.mPayoutCount, this.arg$2);
    }
}
