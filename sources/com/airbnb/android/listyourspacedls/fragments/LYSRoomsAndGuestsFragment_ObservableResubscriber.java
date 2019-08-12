package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSRoomsAndGuestsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSRoomsAndGuestsFragment_ObservableResubscriber(LYSRoomsAndGuestsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.updateRoomsAndGuestsListener, "LYSRoomsAndGuestsFragment_updateRoomsAndGuestsListener");
        group.resubscribeAll(target.updateRoomsAndGuestsListener);
    }
}
