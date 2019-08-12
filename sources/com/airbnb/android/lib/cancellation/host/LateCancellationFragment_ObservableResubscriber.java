package com.airbnb.android.lib.cancellation.host;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LateCancellationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LateCancellationFragment_ObservableResubscriber(LateCancellationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listener, "LateCancellationFragment_listener");
        group.resubscribeAll(target.listener);
    }
}
