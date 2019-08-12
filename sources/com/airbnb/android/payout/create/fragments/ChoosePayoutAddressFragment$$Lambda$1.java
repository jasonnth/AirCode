package com.airbnb.android.payout.create.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ChoosePayoutAddressFragment$$Lambda$1 implements OnClickListener {
    private final ChoosePayoutAddressFragment arg$1;

    private ChoosePayoutAddressFragment$$Lambda$1(ChoosePayoutAddressFragment choosePayoutAddressFragment) {
        this.arg$1 = choosePayoutAddressFragment;
    }

    public static OnClickListener lambdaFactory$(ChoosePayoutAddressFragment choosePayoutAddressFragment) {
        return new ChoosePayoutAddressFragment$$Lambda$1(choosePayoutAddressFragment);
    }

    public void onClick(View view) {
        ChoosePayoutAddressFragment.lambda$addressNextStepClickListener$0(this.arg$1, view);
    }
}
