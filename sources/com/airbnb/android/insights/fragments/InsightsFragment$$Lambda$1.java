package com.airbnb.android.insights.fragments;

import com.airbnb.android.core.responses.InsightsResponse;
import p032rx.functions.Action1;

final /* synthetic */ class InsightsFragment$$Lambda$1 implements Action1 {
    private final InsightsFragment arg$1;

    private InsightsFragment$$Lambda$1(InsightsFragment insightsFragment) {
        this.arg$1 = insightsFragment;
    }

    public static Action1 lambdaFactory$(InsightsFragment insightsFragment) {
        return new InsightsFragment$$Lambda$1(insightsFragment);
    }

    public void call(Object obj) {
        InsightsFragment.lambda$new$2(this.arg$1, (InsightsResponse) obj);
    }
}
