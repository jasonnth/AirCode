package com.airbnb.android.payout.create.controllers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ChoosePayoutAddressEpoxyController$$Lambda$2 implements OnClickListener {
    private final ChoosePayoutAddressEpoxyController arg$1;

    private ChoosePayoutAddressEpoxyController$$Lambda$2(ChoosePayoutAddressEpoxyController choosePayoutAddressEpoxyController) {
        this.arg$1 = choosePayoutAddressEpoxyController;
    }

    public static OnClickListener lambdaFactory$(ChoosePayoutAddressEpoxyController choosePayoutAddressEpoxyController) {
        return new ChoosePayoutAddressEpoxyController$$Lambda$2(choosePayoutAddressEpoxyController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onClickAddNewAddress();
    }
}
