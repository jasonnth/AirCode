package com.airbnb.android.lib.payments.creditcard.brazil.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class BrazilCreditCardDetailsFragment$$Lambda$2 implements Action1 {
    private final BrazilCreditCardDetailsFragment arg$1;

    private BrazilCreditCardDetailsFragment$$Lambda$2(BrazilCreditCardDetailsFragment brazilCreditCardDetailsFragment) {
        this.arg$1 = brazilCreditCardDetailsFragment;
    }

    public static Action1 lambdaFactory$(BrazilCreditCardDetailsFragment brazilCreditCardDetailsFragment) {
        return new BrazilCreditCardDetailsFragment$$Lambda$2(brazilCreditCardDetailsFragment);
    }

    public void call(Object obj) {
        BrazilCreditCardDetailsFragment.lambda$new$1(this.arg$1, (AirRequestNetworkException) obj);
    }
}
