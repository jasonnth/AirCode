package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.activities.LegacyAddPayoutActivity;

final /* synthetic */ class PayoutPaypalFragment$$Lambda$2 implements OnClickListener {
    private final PayoutPaypalFragment arg$1;

    private PayoutPaypalFragment$$Lambda$2(PayoutPaypalFragment payoutPaypalFragment) {
        this.arg$1 = payoutPaypalFragment;
    }

    public static OnClickListener lambdaFactory$(PayoutPaypalFragment payoutPaypalFragment) {
        return new PayoutPaypalFragment$$Lambda$2(payoutPaypalFragment);
    }

    public void onClick(View view) {
        ((LegacyAddPayoutActivity) this.arg$1.getActivity()).showFragment(PayoutPaypalSecondFragment.newInstance(false));
    }
}
