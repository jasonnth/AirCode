package com.airbnb.android.core.host;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HostReactivationFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public HostReactivationFragment_ObservableResubscriber(HostReactivationFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.copyRequestListener, "HostReactivationFragment_copyRequestListener");
        group.resubscribeAll(target.copyRequestListener);
        setTag((AutoTaggableObserver) target.reactivateHostListener, "HostReactivationFragment_reactivateHostListener");
        group.resubscribeAll(target.reactivateHostListener);
    }
}
