package com.airbnb.android.insights.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class InsightsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public InsightsFragment_ObservableResubscriber(InsightsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.insightsRequestListener, "InsightsFragment_insightsRequestListener");
        group.resubscribeAll(target.insightsRequestListener);
        setTag((AutoTaggableObserver) target.listingsListener, "InsightsFragment_listingsListener");
        group.resubscribeAll(target.listingsListener);
    }
}
