package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSTextSettingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSTextSettingFragment_ObservableResubscriber(LYSTextSettingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateListingListener, "LYSTextSettingFragment_updateListingListener");
        group.resubscribeAll(target.updateListingListener);
    }
}
