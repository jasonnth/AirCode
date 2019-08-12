package com.airbnb.android.payout.create.controllers;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.payout.models.PayoutInfoForm;

final /* synthetic */ class ChoosePayoutMethodEpoxyController$$Lambda$4 implements OnClickListener {
    private final ChoosePayoutMethodEpoxyController arg$1;
    private final PayoutInfoForm arg$2;
    private final String arg$3;

    private ChoosePayoutMethodEpoxyController$$Lambda$4(ChoosePayoutMethodEpoxyController choosePayoutMethodEpoxyController, PayoutInfoForm payoutInfoForm, String str) {
        this.arg$1 = choosePayoutMethodEpoxyController;
        this.arg$2 = payoutInfoForm;
        this.arg$3 = str;
    }

    public static OnClickListener lambdaFactory$(ChoosePayoutMethodEpoxyController choosePayoutMethodEpoxyController, PayoutInfoForm payoutInfoForm, String str) {
        return new ChoosePayoutMethodEpoxyController$$Lambda$4(choosePayoutMethodEpoxyController, payoutInfoForm, str);
    }

    public void onClick(View view) {
        this.arg$1.listener.onClickPaymentMethodTypeRow(this.arg$2, this.arg$3);
    }
}
