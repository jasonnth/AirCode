package com.airbnb.android.fixit.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class FixItItemCommentFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public FixItItemCommentFragment_ObservableResubscriber(FixItItemCommentFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.fixItItemListener, "FixItItemCommentFragment_fixItItemListener");
        group.resubscribeAll(target.fixItItemListener);
    }
}
