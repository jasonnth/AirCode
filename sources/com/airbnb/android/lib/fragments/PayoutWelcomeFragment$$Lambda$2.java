package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.activities.LegacyAddPayoutActivity;

final /* synthetic */ class PayoutWelcomeFragment$$Lambda$2 implements OnClickListener {
    private final PayoutWelcomeFragment arg$1;

    private PayoutWelcomeFragment$$Lambda$2(PayoutWelcomeFragment payoutWelcomeFragment) {
        this.arg$1 = payoutWelcomeFragment;
    }

    public static OnClickListener lambdaFactory$(PayoutWelcomeFragment payoutWelcomeFragment) {
        return new PayoutWelcomeFragment$$Lambda$2(payoutWelcomeFragment);
    }

    public void onClick(View view) {
        ((LegacyAddPayoutActivity) this.arg$1.getActivity()).showFragment(PayoutTrustFragment.forCountryCode(this.arg$1.getArguments().getString("arg_country_code")));
    }
}
