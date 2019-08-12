package com.airbnb.android.payout.create.controllers;

import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class AddPayoutMethodDataController$$Lambda$5 implements Action1 {
    private final AddPayoutMethodDataController arg$1;

    private AddPayoutMethodDataController$$Lambda$5(AddPayoutMethodDataController addPayoutMethodDataController) {
        this.arg$1 = addPayoutMethodDataController;
    }

    public static Action1 lambdaFactory$(AddPayoutMethodDataController addPayoutMethodDataController) {
        return new AddPayoutMethodDataController$$Lambda$5(addPayoutMethodDataController);
    }

    public void call(Object obj) {
        this.arg$1.onFetchRedirectUrlSuccess(((PaymentInstrumentResponse) obj).paymentInstrument);
    }
}
