package com.airbnb.android.lib.payments.quickpay.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class QuickPayFragment$$Lambda$2 implements OnClickListener {
    private final QuickPayFragment arg$1;

    private QuickPayFragment$$Lambda$2(QuickPayFragment quickPayFragment) {
        this.arg$1 = quickPayFragment;
    }

    public static OnClickListener lambdaFactory$(QuickPayFragment quickPayFragment) {
        return new QuickPayFragment$$Lambda$2(quickPayFragment);
    }

    public void onClick(View view) {
        QuickPayFragment.lambda$payButtonClickListener$1(this.arg$1, view);
    }
}
