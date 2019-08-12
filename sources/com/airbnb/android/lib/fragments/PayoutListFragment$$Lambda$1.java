package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.activities.LegacyAddPayoutActivity;

final /* synthetic */ class PayoutListFragment$$Lambda$1 implements OnClickListener {
    private final PayoutListFragment arg$1;

    private PayoutListFragment$$Lambda$1(PayoutListFragment payoutListFragment) {
        this.arg$1 = payoutListFragment;
    }

    public static OnClickListener lambdaFactory$(PayoutListFragment payoutListFragment) {
        return new PayoutListFragment$$Lambda$1(payoutListFragment);
    }

    public void onClick(View view) {
        ((LegacyAddPayoutActivity) this.arg$1.getActivity()).showFragment(PayoutWelcomeFragment.withCountryCode(this.arg$1.getDefaultUserCOR()));
    }
}
