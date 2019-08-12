package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSHouseRulesFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSHouseRulesFragment_ObservableResubscriber(LYSHouseRulesFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateGuestControlsListener, "LYSHouseRulesFragment_updateGuestControlsListener");
        group.resubscribeAll(target.updateGuestControlsListener);
    }
}
