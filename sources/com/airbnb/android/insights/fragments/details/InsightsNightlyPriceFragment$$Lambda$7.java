package com.airbnb.android.insights.fragments.details;

import com.airbnb.android.core.responses.SimpleListingResponse;
import p032rx.functions.Action1;

final /* synthetic */ class InsightsNightlyPriceFragment$$Lambda$7 implements Action1 {
    private final InsightsNightlyPriceFragment arg$1;

    private InsightsNightlyPriceFragment$$Lambda$7(InsightsNightlyPriceFragment insightsNightlyPriceFragment) {
        this.arg$1 = insightsNightlyPriceFragment;
    }

    public static Action1 lambdaFactory$(InsightsNightlyPriceFragment insightsNightlyPriceFragment) {
        return new InsightsNightlyPriceFragment$$Lambda$7(insightsNightlyPriceFragment);
    }

    public void call(Object obj) {
        InsightsNightlyPriceFragment.lambda$new$3(this.arg$1, (SimpleListingResponse) obj);
    }
}
