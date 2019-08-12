package com.airbnb.android.lib.host.stats;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HostListingSelectorFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public HostListingSelectorFragment_ObservableResubscriber(HostListingSelectorFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestListener, "HostListingSelectorFragment_requestListener");
        group.resubscribeAll(target.requestListener);
    }
}
