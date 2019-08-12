package com.airbnb.android.payout.create.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class SelectPayoutCountryFragment$$Lambda$1 implements OnClickListener {
    private final SelectPayoutCountryFragment arg$1;

    private SelectPayoutCountryFragment$$Lambda$1(SelectPayoutCountryFragment selectPayoutCountryFragment) {
        this.arg$1 = selectPayoutCountryFragment;
    }

    public static OnClickListener lambdaFactory$(SelectPayoutCountryFragment selectPayoutCountryFragment) {
        return new SelectPayoutCountryFragment$$Lambda$1(selectPayoutCountryFragment);
    }

    public void onClick(View view) {
        this.arg$1.countryCodeSelectedListener.onBackPressed();
    }
}
