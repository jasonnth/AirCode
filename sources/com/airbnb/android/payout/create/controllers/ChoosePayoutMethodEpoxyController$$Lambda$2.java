package com.airbnb.android.payout.create.controllers;

import com.airbnb.android.payout.models.PayoutInfoForm;
import com.google.common.base.Function;

final /* synthetic */ class ChoosePayoutMethodEpoxyController$$Lambda$2 implements Function {
    private final ChoosePayoutMethodEpoxyController arg$1;
    private final String arg$2;
    private final boolean arg$3;

    private ChoosePayoutMethodEpoxyController$$Lambda$2(ChoosePayoutMethodEpoxyController choosePayoutMethodEpoxyController, String str, boolean z) {
        this.arg$1 = choosePayoutMethodEpoxyController;
        this.arg$2 = str;
        this.arg$3 = z;
    }

    public static Function lambdaFactory$(ChoosePayoutMethodEpoxyController choosePayoutMethodEpoxyController, String str, boolean z) {
        return new ChoosePayoutMethodEpoxyController$$Lambda$2(choosePayoutMethodEpoxyController, str, z);
    }

    public Object apply(Object obj) {
        return this.arg$1.buildPayoutMethodRow((PayoutInfoForm) obj, this.arg$2, this.arg$3);
    }
}
