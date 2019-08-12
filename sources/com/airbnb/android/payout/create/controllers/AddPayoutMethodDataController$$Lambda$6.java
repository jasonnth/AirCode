package com.airbnb.android.payout.create.controllers;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class AddPayoutMethodDataController$$Lambda$6 implements Action1 {
    private final AddPayoutMethodDataController arg$1;

    private AddPayoutMethodDataController$$Lambda$6(AddPayoutMethodDataController addPayoutMethodDataController) {
        this.arg$1 = addPayoutMethodDataController;
    }

    public static Action1 lambdaFactory$(AddPayoutMethodDataController addPayoutMethodDataController) {
        return new AddPayoutMethodDataController$$Lambda$6(addPayoutMethodDataController);
    }

    public void call(Object obj) {
        this.arg$1.onFetchRedirectUrlError((AirRequestNetworkException) obj);
    }
}
