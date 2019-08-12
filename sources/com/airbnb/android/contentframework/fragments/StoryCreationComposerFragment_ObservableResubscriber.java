package com.airbnb.android.contentframework.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class StoryCreationComposerFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public StoryCreationComposerFragment_ObservableResubscriber(StoryCreationComposerFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.suggestedPlaceTagsListener, "StoryCreationComposerFragment_suggestedPlaceTagsListener");
        group.resubscribeAll(target.suggestedPlaceTagsListener);
    }
}
