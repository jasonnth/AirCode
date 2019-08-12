package com.airbnb.android.insights.fragments;

import p032rx.functions.Action1;

final /* synthetic */ class InsightsFragment$$Lambda$3 implements Action1 {
    private final InsightsFragment arg$1;

    private InsightsFragment$$Lambda$3(InsightsFragment insightsFragment) {
        this.arg$1 = insightsFragment;
    }

    public static Action1 lambdaFactory$(InsightsFragment insightsFragment) {
        return new InsightsFragment$$Lambda$3(insightsFragment);
    }

    public void call(Object obj) {
        this.arg$1.toggleLoading(false);
    }
}
