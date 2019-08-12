package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSPublishFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSPublishFragment_ObservableResubscriber(LYSPublishFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.publishListingRequestListener, "LYSPublishFragment_publishListingRequestListener");
        group.resubscribeAll(target.publishListingRequestListener);
    }
}
