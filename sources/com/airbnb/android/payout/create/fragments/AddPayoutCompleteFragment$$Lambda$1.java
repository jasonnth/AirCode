package com.airbnb.android.payout.create.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AddPayoutCompleteFragment$$Lambda$1 implements OnClickListener {
    private final AddPayoutCompleteFragment arg$1;

    private AddPayoutCompleteFragment$$Lambda$1(AddPayoutCompleteFragment addPayoutCompleteFragment) {
        this.arg$1 = addPayoutCompleteFragment;
    }

    public static OnClickListener lambdaFactory$(AddPayoutCompleteFragment addPayoutCompleteFragment) {
        return new AddPayoutCompleteFragment$$Lambda$1(addPayoutCompleteFragment);
    }

    public void onClick(View view) {
        AddPayoutCompleteFragment.lambda$onActivityCreated$0(this.arg$1, view);
    }
}
