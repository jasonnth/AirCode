package com.airbnb.android.insights.fragments.details;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class InsightsNightlyPriceFragment$$Lambda$6 implements Action1 {
    private final InsightsNightlyPriceFragment arg$1;

    private InsightsNightlyPriceFragment$$Lambda$6(InsightsNightlyPriceFragment insightsNightlyPriceFragment) {
        this.arg$1 = insightsNightlyPriceFragment;
    }

    public static Action1 lambdaFactory$(InsightsNightlyPriceFragment insightsNightlyPriceFragment) {
        return new InsightsNightlyPriceFragment$$Lambda$6(insightsNightlyPriceFragment);
    }

    public void call(Object obj) {
        this.arg$1.onError((AirRequestNetworkException) obj);
    }
}
