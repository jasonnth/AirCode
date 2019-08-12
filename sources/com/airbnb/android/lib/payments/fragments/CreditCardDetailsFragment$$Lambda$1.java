package com.airbnb.android.lib.payments.fragments;

import com.airbnb.android.lib.payments.creditcard.brazil.networking.response.BrazilCepResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CreditCardDetailsFragment$$Lambda$1 implements Action1 {
    private final CreditCardDetailsFragment arg$1;

    private CreditCardDetailsFragment$$Lambda$1(CreditCardDetailsFragment creditCardDetailsFragment) {
        this.arg$1 = creditCardDetailsFragment;
    }

    public static Action1 lambdaFactory$(CreditCardDetailsFragment creditCardDetailsFragment) {
        return new CreditCardDetailsFragment$$Lambda$1(creditCardDetailsFragment);
    }

    public void call(Object obj) {
        CreditCardDetailsFragment.lambda$new$0(this.arg$1, (BrazilCepResponse) obj);
    }
}
