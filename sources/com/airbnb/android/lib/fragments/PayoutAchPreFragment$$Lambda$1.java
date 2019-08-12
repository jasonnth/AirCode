package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PayoutAchPreFragment$$Lambda$1 implements OnClickListener {
    private final PayoutAchPreFragment arg$1;

    private PayoutAchPreFragment$$Lambda$1(PayoutAchPreFragment payoutAchPreFragment) {
        this.arg$1 = payoutAchPreFragment;
    }

    public static OnClickListener lambdaFactory$(PayoutAchPreFragment payoutAchPreFragment) {
        return new PayoutAchPreFragment$$Lambda$1(payoutAchPreFragment);
    }

    public void onClick(View view) {
        PayoutAchPreFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
