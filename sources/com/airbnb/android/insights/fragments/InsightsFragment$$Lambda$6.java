package com.airbnb.android.insights.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class InsightsFragment$$Lambda$6 implements OnClickListener {
    private final InsightsFragment arg$1;

    private InsightsFragment$$Lambda$6(InsightsFragment insightsFragment) {
        this.arg$1 = insightsFragment;
    }

    public static OnClickListener lambdaFactory$(InsightsFragment insightsFragment) {
        return new InsightsFragment$$Lambda$6(insightsFragment);
    }

    public void onClick(View view) {
        ((InsightsParentFragment) this.arg$1.getParentFragment()).onBackPressed();
    }
}
