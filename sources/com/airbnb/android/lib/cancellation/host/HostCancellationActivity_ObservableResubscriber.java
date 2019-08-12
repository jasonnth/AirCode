package com.airbnb.android.lib.cancellation.host;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HostCancellationActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public HostCancellationActivity_ObservableResubscriber(HostCancellationActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reservationListener, "HostCancellationActivity_reservationListener");
        group.resubscribeAll(target.reservationListener);
    }
}
