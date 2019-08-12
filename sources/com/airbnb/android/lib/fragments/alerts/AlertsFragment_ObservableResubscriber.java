package com.airbnb.android.lib.fragments.alerts;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AlertsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AlertsFragment_ObservableResubscriber(AlertsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.alertsListener, "AlertsFragment_alertsListener");
        group.resubscribeAll(target.alertsListener);
    }
}
