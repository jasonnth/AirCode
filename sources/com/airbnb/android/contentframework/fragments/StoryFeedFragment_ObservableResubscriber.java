package com.airbnb.android.contentframework.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class StoryFeedFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public StoryFeedFragment_ObservableResubscriber(StoryFeedFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.storyFeedRequestListener, "StoryFeedFragment_storyFeedRequestListener");
        group.resubscribeAll(target.storyFeedRequestListener);
    }
}
