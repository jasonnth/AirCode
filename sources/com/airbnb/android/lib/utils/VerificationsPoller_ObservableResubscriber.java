package com.airbnb.android.lib.utils;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class VerificationsPoller_ObservableResubscriber extends BaseObservableResubscriber {
    public VerificationsPoller_ObservableResubscriber(VerificationsPoller target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestListener, "VerificationsPoller_requestListener");
    }
}
