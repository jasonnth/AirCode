package com.airbnb.android.insights.fragments.details;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class InsightsNightlyPriceFragment$$Lambda$9 implements OnClickListener {
    private final InsightsNightlyPriceFragment arg$1;

    private InsightsNightlyPriceFragment$$Lambda$9(InsightsNightlyPriceFragment insightsNightlyPriceFragment) {
        this.arg$1 = insightsNightlyPriceFragment;
    }

    public static OnClickListener lambdaFactory$(InsightsNightlyPriceFragment insightsNightlyPriceFragment) {
        return new InsightsNightlyPriceFragment$$Lambda$9(insightsNightlyPriceFragment);
    }

    public void onClick(View view) {
        this.arg$1.getParentFragment().getFragmentManager().popBackStack();
    }
}
