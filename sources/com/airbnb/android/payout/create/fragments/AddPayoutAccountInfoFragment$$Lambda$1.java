package com.airbnb.android.payout.create.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AddPayoutAccountInfoFragment$$Lambda$1 implements OnClickListener {
    private final AddPayoutAccountInfoFragment arg$1;

    private AddPayoutAccountInfoFragment$$Lambda$1(AddPayoutAccountInfoFragment addPayoutAccountInfoFragment) {
        this.arg$1 = addPayoutAccountInfoFragment;
    }

    public static OnClickListener lambdaFactory$(AddPayoutAccountInfoFragment addPayoutAccountInfoFragment) {
        return new AddPayoutAccountInfoFragment$$Lambda$1(addPayoutAccountInfoFragment);
    }

    public void onClick(View view) {
        AddPayoutAccountInfoFragment.lambda$onActivityCreated$0(this.arg$1, view);
    }
}
