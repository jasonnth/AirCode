package com.airbnb.android.insights.fragments.details;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class InsightsNightlyPriceFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public InsightsNightlyPriceFragment_ObservableResubscriber(InsightsNightlyPriceFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.smartPricingListener, "InsightsNightlyPriceFragment_smartPricingListener");
        group.resubscribeAll(target.smartPricingListener);
        setTag((AutoTaggableObserver) target.basePriceListener, "InsightsNightlyPriceFragment_basePriceListener");
        group.resubscribeAll(target.basePriceListener);
    }
}
