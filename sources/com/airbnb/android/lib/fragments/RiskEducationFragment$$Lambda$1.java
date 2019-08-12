package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class RiskEducationFragment$$Lambda$1 implements OnClickListener {
    private final RiskEducationFragment arg$1;

    private RiskEducationFragment$$Lambda$1(RiskEducationFragment riskEducationFragment) {
        this.arg$1 = riskEducationFragment;
    }

    public static OnClickListener lambdaFactory$(RiskEducationFragment riskEducationFragment) {
        return new RiskEducationFragment$$Lambda$1(riskEducationFragment);
    }

    public void onClick(View view) {
        this.arg$1.getActivity().finish();
    }
}
