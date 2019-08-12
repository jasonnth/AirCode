package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PayoutAddedFragment$$Lambda$1 implements OnClickListener {
    private final PayoutAddedFragment arg$1;

    private PayoutAddedFragment$$Lambda$1(PayoutAddedFragment payoutAddedFragment) {
        this.arg$1 = payoutAddedFragment;
    }

    public static OnClickListener lambdaFactory$(PayoutAddedFragment payoutAddedFragment) {
        return new PayoutAddedFragment$$Lambda$1(payoutAddedFragment);
    }

    public void onClick(View view) {
        this.arg$1.getNavigationController().doneAddingPayout();
    }
}
