package com.airbnb.android.contentframework.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class StoryCreationPlaceTaggingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public StoryCreationPlaceTaggingFragment_ObservableResubscriber(StoryCreationPlaceTaggingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.searchPlaceListener, "StoryCreationPlaceTaggingFragment_searchPlaceListener");
        group.resubscribeAll(target.searchPlaceListener);
    }
}
