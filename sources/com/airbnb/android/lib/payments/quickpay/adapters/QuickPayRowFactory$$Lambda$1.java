package com.airbnb.android.lib.payments.quickpay.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.payments.quickpay.clicklisteners.QuickPayClickListener;

final /* synthetic */ class QuickPayRowFactory$$Lambda$1 implements OnClickListener {
    private final QuickPayClickListener arg$1;

    private QuickPayRowFactory$$Lambda$1(QuickPayClickListener quickPayClickListener) {
        this.arg$1 = quickPayClickListener;
    }

    public static OnClickListener lambdaFactory$(QuickPayClickListener quickPayClickListener) {
        return new QuickPayRowFactory$$Lambda$1(quickPayClickListener);
    }

    public void onClick(View view) {
        this.arg$1.onPaymentRowClicked();
    }
}
