package com.airbnb.android.listyourspacedls.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class LYSRoomBedDetailsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public LYSRoomBedDetailsFragment_ObservableResubscriber(LYSRoomBedDetailsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.newRoomListener, "LYSRoomBedDetailsFragment_newRoomListener");
        group.resubscribeAll(target.newRoomListener);
        setTag((AutoTaggableObserver) target.fetchRoomsListener, "LYSRoomBedDetailsFragment_fetchRoomsListener");
        group.resubscribeAll(target.fetchRoomsListener);
    }
}
