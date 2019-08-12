package com.airbnb.android.payout.create.fragments;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class SelectPayoutCountryFragment$$Lambda$2 implements OnEditorActionListener {
    private final SelectPayoutCountryFragment arg$1;

    private SelectPayoutCountryFragment$$Lambda$2(SelectPayoutCountryFragment selectPayoutCountryFragment) {
        this.arg$1 = selectPayoutCountryFragment;
    }

    public static OnEditorActionListener lambdaFactory$(SelectPayoutCountryFragment selectPayoutCountryFragment) {
        return new SelectPayoutCountryFragment$$Lambda$2(selectPayoutCountryFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return SelectPayoutCountryFragment.lambda$setupInputMarquee$1(this.arg$1, textView, i, keyEvent);
    }
}
