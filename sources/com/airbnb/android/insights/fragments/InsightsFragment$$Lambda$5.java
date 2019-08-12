package com.airbnb.android.insights.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class InsightsFragment$$Lambda$5 implements Action1 {
    private final InsightsFragment arg$1;

    private InsightsFragment$$Lambda$5(InsightsFragment insightsFragment) {
        this.arg$1 = insightsFragment;
    }

    public static Action1 lambdaFactory$(InsightsFragment insightsFragment) {
        return new InsightsFragment$$Lambda$5(insightsFragment);
    }

    public void call(Object obj) {
        InsightsFragment.lambda$new$7(this.arg$1, (AirRequestNetworkException) obj);
    }
}
