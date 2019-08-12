package com.airbnb.android.lib.payments.quickpay.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ResyQuickPayFragment$$Lambda$1 implements OnClickListener {
    private final ResyQuickPayFragment arg$1;

    private ResyQuickPayFragment$$Lambda$1(ResyQuickPayFragment resyQuickPayFragment) {
        this.arg$1 = resyQuickPayFragment;
    }

    public static OnClickListener lambdaFactory$(ResyQuickPayFragment resyQuickPayFragment) {
        return new ResyQuickPayFragment$$Lambda$1(resyQuickPayFragment);
    }

    public void onClick(View view) {
        this.arg$1.sendBill();
    }
}
