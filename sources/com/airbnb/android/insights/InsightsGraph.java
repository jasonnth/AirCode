package com.airbnb.android.insights;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.insights.fragments.details.InsightsNightlyPriceFragment;

public interface InsightsGraph extends BaseGraph {
    void inject(InsightsDataController insightsDataController);

    void inject(InsightsNightlyPriceFragment insightsNightlyPriceFragment);
}
