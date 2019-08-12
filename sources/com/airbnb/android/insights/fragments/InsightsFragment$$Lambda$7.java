package com.airbnb.android.insights.fragments;

final /* synthetic */ class InsightsFragment$$Lambda$7 implements Runnable {
    private final InsightsFragment arg$1;

    private InsightsFragment$$Lambda$7(InsightsFragment insightsFragment) {
        this.arg$1 = insightsFragment;
    }

    public static Runnable lambdaFactory$(InsightsFragment insightsFragment) {
        return new InsightsFragment$$Lambda$7(insightsFragment);
    }

    public void run() {
        this.arg$1.adapter.addInsights(this.arg$1.insightToLoadingStateMap);
    }
}
