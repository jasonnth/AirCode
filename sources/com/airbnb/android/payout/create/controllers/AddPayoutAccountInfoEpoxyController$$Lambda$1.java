package com.airbnb.android.payout.create.controllers;

import com.airbnb.android.payout.models.PayoutFormField;
import com.airbnb.p027n2.components.InlineInputRow.OnInputChangedListener;

final /* synthetic */ class AddPayoutAccountInfoEpoxyController$$Lambda$1 implements OnInputChangedListener {
    private final AddPayoutAccountInfoEpoxyController arg$1;
    private final PayoutFormField arg$2;

    private AddPayoutAccountInfoEpoxyController$$Lambda$1(AddPayoutAccountInfoEpoxyController addPayoutAccountInfoEpoxyController, PayoutFormField payoutFormField) {
        this.arg$1 = addPayoutAccountInfoEpoxyController;
        this.arg$2 = payoutFormField;
    }

    public static OnInputChangedListener lambdaFactory$(AddPayoutAccountInfoEpoxyController addPayoutAccountInfoEpoxyController, PayoutFormField payoutFormField) {
        return new AddPayoutAccountInfoEpoxyController$$Lambda$1(addPayoutAccountInfoEpoxyController, payoutFormField);
    }

    public void onInputChanged(String str) {
        this.arg$1.listener.onInputChanged(this.arg$2, str);
    }
}
