package com.airbnb.android.lib.payments.creditcard.brazil.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class BrazilCreditCardDetailsFragment$$Lambda$3 implements OnClickListener {
    private final BrazilCreditCardDetailsFragment arg$1;

    private BrazilCreditCardDetailsFragment$$Lambda$3(BrazilCreditCardDetailsFragment brazilCreditCardDetailsFragment) {
        this.arg$1 = brazilCreditCardDetailsFragment;
    }

    public static OnClickListener lambdaFactory$(BrazilCreditCardDetailsFragment brazilCreditCardDetailsFragment) {
        return new BrazilCreditCardDetailsFragment$$Lambda$3(brazilCreditCardDetailsFragment);
    }

    public void onClick(View view) {
        this.arg$1.launchBirthdayPicker();
    }
}
