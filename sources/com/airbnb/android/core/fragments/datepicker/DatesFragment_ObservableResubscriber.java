package com.airbnb.android.core.fragments.datepicker;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class DatesFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public DatesFragment_ObservableResubscriber(DatesFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listener, "DatesFragment_listener");
        group.resubscribeAll(target.listener);
    }
}
