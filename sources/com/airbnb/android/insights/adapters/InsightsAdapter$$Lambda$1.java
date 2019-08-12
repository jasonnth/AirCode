package com.airbnb.android.insights.adapters;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.insights.LastInsightEpoxyModel_;

final /* synthetic */ class InsightsAdapter$$Lambda$1 implements OnClickListener {
    private final InsightsAdapter arg$1;
    private final Insight arg$2;
    private final LastInsightEpoxyModel_ arg$3;

    private InsightsAdapter$$Lambda$1(InsightsAdapter insightsAdapter, Insight insight, LastInsightEpoxyModel_ lastInsightEpoxyModel_) {
        this.arg$1 = insightsAdapter;
        this.arg$2 = insight;
        this.arg$3 = lastInsightEpoxyModel_;
    }

    public static OnClickListener lambdaFactory$(InsightsAdapter insightsAdapter, Insight insight, LastInsightEpoxyModel_ lastInsightEpoxyModel_) {
        return new InsightsAdapter$$Lambda$1(insightsAdapter, insight, lastInsightEpoxyModel_);
    }

    public void onClick(View view) {
        InsightsAdapter.lambda$addInsights$0(this.arg$1, this.arg$2, this.arg$3, view);
    }
}
