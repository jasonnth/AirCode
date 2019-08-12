package com.airbnb.android.payout.create.controllers;

import android.view.View;
import com.airbnb.p027n2.utils.AirTextBuilder.OnLinkClickListener;

final /* synthetic */ class ChoosePayoutMethodEpoxyController$$Lambda$3 implements OnLinkClickListener {
    private final ChoosePayoutMethodEpoxyController arg$1;

    private ChoosePayoutMethodEpoxyController$$Lambda$3(ChoosePayoutMethodEpoxyController choosePayoutMethodEpoxyController) {
        this.arg$1 = choosePayoutMethodEpoxyController;
    }

    public static OnLinkClickListener lambdaFactory$(ChoosePayoutMethodEpoxyController choosePayoutMethodEpoxyController) {
        return new ChoosePayoutMethodEpoxyController$$Lambda$3(choosePayoutMethodEpoxyController);
    }

    public void onClick(View view, CharSequence charSequence) {
        this.arg$1.listener.onClickChangeCountry();
    }
}
