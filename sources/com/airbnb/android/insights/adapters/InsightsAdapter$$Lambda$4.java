package com.airbnb.android.insights.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.insights.InsightEpoxyModel_;

final /* synthetic */ class InsightsAdapter$$Lambda$4 implements OnClickListener {
    private final InsightsAdapter arg$1;
    private final InsightEpoxyModel_ arg$2;

    private InsightsAdapter$$Lambda$4(InsightsAdapter insightsAdapter, InsightEpoxyModel_ insightEpoxyModel_) {
        this.arg$1 = insightsAdapter;
        this.arg$2 = insightEpoxyModel_;
    }

    public static OnClickListener lambdaFactory$(InsightsAdapter insightsAdapter, InsightEpoxyModel_ insightEpoxyModel_) {
        return new InsightsAdapter$$Lambda$4(insightsAdapter, insightEpoxyModel_);
    }

    public void onClick(View view) {
        this.arg$1.listener.onSecondaryButtonClicked(this.arg$2);
    }
}
