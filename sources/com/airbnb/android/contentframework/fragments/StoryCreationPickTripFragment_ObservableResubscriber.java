package com.airbnb.android.contentframework.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class StoryCreationPickTripFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public StoryCreationPickTripFragment_ObservableResubscriber(StoryCreationPickTripFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reservationsResponseRequestListener, "StoryCreationPickTripFragment_reservationsResponseRequestListener");
        group.resubscribeAll(target.reservationsResponseRequestListener);
    }
}
