package com.airbnb.android.insights.fragments;

final /* synthetic */ class InsightsDetailCardFragment$$Lambda$1 implements Runnable {
    private final InsightsDetailCardFragment arg$1;

    private InsightsDetailCardFragment$$Lambda$1(InsightsDetailCardFragment insightsDetailCardFragment) {
        this.arg$1 = insightsDetailCardFragment;
    }

    public static Runnable lambdaFactory$(InsightsDetailCardFragment insightsDetailCardFragment) {
        return new InsightsDetailCardFragment$$Lambda$1(insightsDetailCardFragment);
    }

    public void run() {
        this.arg$1.toggleInfoContainer(true);
    }
}
