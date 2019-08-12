package com.airbnb.android.core.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DLSHouseRulesFragment$$Lambda$3 implements OnClickListener {
    private final DLSHouseRulesFragment arg$1;

    private DLSHouseRulesFragment$$Lambda$3(DLSHouseRulesFragment dLSHouseRulesFragment) {
        this.arg$1 = dLSHouseRulesFragment;
    }

    public static OnClickListener lambdaFactory$(DLSHouseRulesFragment dLSHouseRulesFragment) {
        return new DLSHouseRulesFragment$$Lambda$3(dLSHouseRulesFragment);
    }

    public void onClick(View view) {
        this.arg$1.fetchTranslation();
    }
}
