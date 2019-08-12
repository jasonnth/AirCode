package com.airbnb.android.core.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LocalAttractionsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LocalAttractionsFragment_ObservableResubscriber(LocalAttractionsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.localAttractionsRequestListener, "LocalAttractionsFragment_localAttractionsRequestListener");
        group.resubscribeAll(target.localAttractionsRequestListener);
    }
}
