package com.airbnb.android.insights.fragments;

import com.airbnb.android.insights.InsightsActivity;

final /* synthetic */ class InsightsDetailCardFragment$$Lambda$2 implements Runnable {
    private final InsightsActivity arg$1;

    private InsightsDetailCardFragment$$Lambda$2(InsightsActivity insightsActivity) {
        this.arg$1 = insightsActivity;
    }

    public static Runnable lambdaFactory$(InsightsActivity insightsActivity) {
        return new InsightsDetailCardFragment$$Lambda$2(insightsActivity);
    }

    public void run() {
        InsightsDetailCardFragment.lambda$setActivityToolbar$1(this.arg$1);
    }
}
