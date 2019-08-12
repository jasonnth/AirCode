package com.airbnb.android.lib.payments.quickpay.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class QuickPayFragment$$Lambda$3 implements OnClickListener {
    private final QuickPayFragment arg$1;
    private final String arg$2;

    private QuickPayFragment$$Lambda$3(QuickPayFragment quickPayFragment, String str) {
        this.arg$1 = quickPayFragment;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(QuickPayFragment quickPayFragment, String str) {
        return new QuickPayFragment$$Lambda$3(quickPayFragment, str);
    }

    public void onClick(View view) {
        QuickPayFragment.lambda$onCurrencyMismatchError$2(this.arg$1, this.arg$2, view);
    }
}
