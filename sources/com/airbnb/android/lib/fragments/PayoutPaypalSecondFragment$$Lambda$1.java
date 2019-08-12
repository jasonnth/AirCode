package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PayoutPaypalSecondFragment$$Lambda$1 implements OnClickListener {
    private final PayoutPaypalSecondFragment arg$1;

    private PayoutPaypalSecondFragment$$Lambda$1(PayoutPaypalSecondFragment payoutPaypalSecondFragment) {
        this.arg$1 = payoutPaypalSecondFragment;
    }

    public static OnClickListener lambdaFactory$(PayoutPaypalSecondFragment payoutPaypalSecondFragment) {
        return new PayoutPaypalSecondFragment$$Lambda$1(payoutPaypalSecondFragment);
    }

    public void onClick(View view) {
        PayoutPaypalSecondFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
