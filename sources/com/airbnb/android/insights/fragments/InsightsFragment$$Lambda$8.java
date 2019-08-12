package com.airbnb.android.insights.fragments;

import com.airbnb.epoxy.EpoxyModel;

final /* synthetic */ class InsightsFragment$$Lambda$8 implements Runnable {
    private final InsightsFragment arg$1;
    private final EpoxyModel arg$2;

    private InsightsFragment$$Lambda$8(InsightsFragment insightsFragment, EpoxyModel epoxyModel) {
        this.arg$1 = insightsFragment;
        this.arg$2 = epoxyModel;
    }

    public static Runnable lambdaFactory$(InsightsFragment insightsFragment, EpoxyModel epoxyModel) {
        return new InsightsFragment$$Lambda$8(insightsFragment, epoxyModel);
    }

    public void run() {
        InsightsFragment.lambda$dismissCard$5(this.arg$1, this.arg$2);
    }
}
