package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PayoutAchFragment$$Lambda$1 implements OnClickListener {
    private final PayoutAchFragment arg$1;

    private PayoutAchFragment$$Lambda$1(PayoutAchFragment payoutAchFragment) {
        this.arg$1 = payoutAchFragment;
    }

    public static OnClickListener lambdaFactory$(PayoutAchFragment payoutAchFragment) {
        return new PayoutAchFragment$$Lambda$1(payoutAchFragment);
    }

    public void onClick(View view) {
        PayoutAchFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
