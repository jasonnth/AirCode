package com.airbnb.android.payout.create.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AddPayoutConfirmationFragment$$Lambda$1 implements OnClickListener {
    private final AddPayoutConfirmationFragment arg$1;

    private AddPayoutConfirmationFragment$$Lambda$1(AddPayoutConfirmationFragment addPayoutConfirmationFragment) {
        this.arg$1 = addPayoutConfirmationFragment;
    }

    public static OnClickListener lambdaFactory$(AddPayoutConfirmationFragment addPayoutConfirmationFragment) {
        return new AddPayoutConfirmationFragment$$Lambda$1(addPayoutConfirmationFragment);
    }

    public void onClick(View view) {
        AddPayoutConfirmationFragment.lambda$onActivityCreated$0(this.arg$1, view);
    }
}
