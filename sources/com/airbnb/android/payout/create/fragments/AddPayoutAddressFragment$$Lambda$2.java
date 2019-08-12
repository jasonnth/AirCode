package com.airbnb.android.payout.create.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AddPayoutAddressFragment$$Lambda$2 implements OnClickListener {
    private final AddPayoutAddressFragment arg$1;

    private AddPayoutAddressFragment$$Lambda$2(AddPayoutAddressFragment addPayoutAddressFragment) {
        this.arg$1 = addPayoutAddressFragment;
    }

    public static OnClickListener lambdaFactory$(AddPayoutAddressFragment addPayoutAddressFragment) {
        return new AddPayoutAddressFragment$$Lambda$2(addPayoutAddressFragment);
    }

    public void onClick(View view) {
        AddPayoutAddressFragment.lambda$addressNextStepClickListener$1(this.arg$1, view);
    }
}
