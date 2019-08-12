package com.airbnb.android.payout.create.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.payout.models.PayoutInfoForm;

final /* synthetic */ class ChoosePayoutMethodFragment$$Lambda$1 implements OnClickListener {
    private final ChoosePayoutMethodFragment arg$1;
    private final PayoutInfoForm arg$2;

    private ChoosePayoutMethodFragment$$Lambda$1(ChoosePayoutMethodFragment choosePayoutMethodFragment, PayoutInfoForm payoutInfoForm) {
        this.arg$1 = choosePayoutMethodFragment;
        this.arg$2 = payoutInfoForm;
    }

    public static OnClickListener lambdaFactory$(ChoosePayoutMethodFragment choosePayoutMethodFragment, PayoutInfoForm payoutInfoForm) {
        return new ChoosePayoutMethodFragment$$Lambda$1(choosePayoutMethodFragment, payoutInfoForm);
    }

    public void onClick(View view) {
        this.arg$1.navigationController.doneWithPayoutMethodInfo(this.arg$2);
    }
}
