package com.airbnb.android.lib.payments.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CreditCardDetailsFragment$$Lambda$2 implements Action1 {
    private final CreditCardDetailsFragment arg$1;

    private CreditCardDetailsFragment$$Lambda$2(CreditCardDetailsFragment creditCardDetailsFragment) {
        this.arg$1 = creditCardDetailsFragment;
    }

    public static Action1 lambdaFactory$(CreditCardDetailsFragment creditCardDetailsFragment) {
        return new CreditCardDetailsFragment$$Lambda$2(creditCardDetailsFragment);
    }

    public void call(Object obj) {
        CreditCardDetailsFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
