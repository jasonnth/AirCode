package com.airbnb.android.lib.payments.quickpay.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class HomesQuickPayFragment$$Lambda$3 implements OnClickListener {
    private final HomesQuickPayFragment arg$1;

    private HomesQuickPayFragment$$Lambda$3(HomesQuickPayFragment homesQuickPayFragment) {
        this.arg$1 = homesQuickPayFragment;
    }

    public static OnClickListener lambdaFactory$(HomesQuickPayFragment homesQuickPayFragment) {
        return new HomesQuickPayFragment$$Lambda$3(homesQuickPayFragment);
    }

    public void onClick(View view) {
        this.arg$1.finishWithIdentityError();
    }
}