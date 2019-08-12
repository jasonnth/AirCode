package com.airbnb.android.booking.activities;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class LegacyAddPaymentMethodActivity$$Lambda$2 implements Action1 {
    private final LegacyAddPaymentMethodActivity arg$1;

    private LegacyAddPaymentMethodActivity$$Lambda$2(LegacyAddPaymentMethodActivity legacyAddPaymentMethodActivity) {
        this.arg$1 = legacyAddPaymentMethodActivity;
    }

    public static Action1 lambdaFactory$(LegacyAddPaymentMethodActivity legacyAddPaymentMethodActivity) {
        return new LegacyAddPaymentMethodActivity$$Lambda$2(legacyAddPaymentMethodActivity);
    }

    public void call(Object obj) {
        LegacyAddPaymentMethodActivity.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
