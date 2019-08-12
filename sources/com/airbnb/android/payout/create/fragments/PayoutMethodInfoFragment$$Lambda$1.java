package com.airbnb.android.payout.create.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PayoutMethodInfoFragment$$Lambda$1 implements OnClickListener {
    private final PayoutMethodInfoFragment arg$1;

    private PayoutMethodInfoFragment$$Lambda$1(PayoutMethodInfoFragment payoutMethodInfoFragment) {
        this.arg$1 = payoutMethodInfoFragment;
    }

    public static OnClickListener lambdaFactory$(PayoutMethodInfoFragment payoutMethodInfoFragment) {
        return new PayoutMethodInfoFragment$$Lambda$1(payoutMethodInfoFragment);
    }

    public void onClick(View view) {
        PayoutMethodInfoFragment.lambda$onActivityCreated$0(this.arg$1, view);
    }
}
