package com.airbnb.android.booking.activities;

import com.airbnb.android.core.responses.PaymentInstrumentResponse;
import p032rx.functions.Action1;

final /* synthetic */ class LegacyAddPaymentMethodActivity$$Lambda$1 implements Action1 {
    private final LegacyAddPaymentMethodActivity arg$1;

    private LegacyAddPaymentMethodActivity$$Lambda$1(LegacyAddPaymentMethodActivity legacyAddPaymentMethodActivity) {
        this.arg$1 = legacyAddPaymentMethodActivity;
    }

    public static Action1 lambdaFactory$(LegacyAddPaymentMethodActivity legacyAddPaymentMethodActivity) {
        return new LegacyAddPaymentMethodActivity$$Lambda$1(legacyAddPaymentMethodActivity);
    }

    public void call(Object obj) {
        LegacyAddPaymentMethodActivity.lambda$new$0(this.arg$1, (PaymentInstrumentResponse) obj);
    }
}
