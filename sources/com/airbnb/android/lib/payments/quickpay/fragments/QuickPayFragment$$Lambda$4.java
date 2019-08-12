package com.airbnb.android.lib.payments.quickpay.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class QuickPayFragment$$Lambda$4 implements OnClickListener {
    private final QuickPayFragment arg$1;

    private QuickPayFragment$$Lambda$4(QuickPayFragment quickPayFragment) {
        this.arg$1 = quickPayFragment;
    }

    public static OnClickListener lambdaFactory$(QuickPayFragment quickPayFragment) {
        return new QuickPayFragment$$Lambda$4(quickPayFragment);
    }

    public void onClick(View view) {
        this.arg$1.launchPostalCodeRetryFlow();
    }
}
