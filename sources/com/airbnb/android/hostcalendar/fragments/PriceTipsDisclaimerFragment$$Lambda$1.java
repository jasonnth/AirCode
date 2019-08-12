package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class PriceTipsDisclaimerFragment$$Lambda$1 implements OnClickListener {
    private final PriceTipsDisclaimerFragment arg$1;

    private PriceTipsDisclaimerFragment$$Lambda$1(PriceTipsDisclaimerFragment priceTipsDisclaimerFragment) {
        this.arg$1 = priceTipsDisclaimerFragment;
    }

    public static OnClickListener lambdaFactory$(PriceTipsDisclaimerFragment priceTipsDisclaimerFragment) {
        return new PriceTipsDisclaimerFragment$$Lambda$1(priceTipsDisclaimerFragment);
    }

    public void onClick(View view) {
        this.arg$1.backListener.onBackPressed();
    }
}
