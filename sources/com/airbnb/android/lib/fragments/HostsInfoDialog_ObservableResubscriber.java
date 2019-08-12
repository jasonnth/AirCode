package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HostsInfoDialog_ObservableResubscriber extends BaseObservableResubscriber {
    public HostsInfoDialog_ObservableResubscriber(HostsInfoDialog target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.userRequestListener, "HostsInfoDialog_userRequestListener");
        group.resubscribeAll(target.userRequestListener);
    }
}
