package com.airbnb.android.lib.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HostCancellationIntentActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public HostCancellationIntentActivity_ObservableResubscriber(HostCancellationIntentActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reservationListener, "HostCancellationIntentActivity_reservationListener");
        group.resubscribeAll(target.reservationListener);
    }
}
