package com.airbnb.android.insights;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class InsightsActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public InsightsActivity_ObservableResubscriber(InsightsActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.minPriceListener, "InsightsActivity_minPriceListener");
        group.resubscribeAll(target.minPriceListener);
        setTag((AutoTaggableObserver) target.updateLongTermPricingRequestListener, "InsightsActivity_updateLongTermPricingRequestListener");
        group.resubscribeAll(target.updateLongTermPricingRequestListener);
        setTag((AutoTaggableObserver) target.listingListener, "InsightsActivity_listingListener");
        group.resubscribeAll(target.listingListener);
    }
}
