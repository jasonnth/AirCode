package com.airbnb.android.insights;

import p032rx.functions.Action1;

final /* synthetic */ class InsightsActivity$$Lambda$7 implements Action1 {
    private final InsightsActivity arg$1;

    private InsightsActivity$$Lambda$7(InsightsActivity insightsActivity) {
        this.arg$1 = insightsActivity;
    }

    public static Action1 lambdaFactory$(InsightsActivity insightsActivity) {
        return new InsightsActivity$$Lambda$7(insightsActivity);
    }

    public void call(Object obj) {
        this.arg$1.toggleLoading(false);
    }
}
