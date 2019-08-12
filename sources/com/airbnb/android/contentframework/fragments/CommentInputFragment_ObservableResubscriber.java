package com.airbnb.android.contentframework.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CommentInputFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CommentInputFragment_ObservableResubscriber(CommentInputFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.postCommentListener, "CommentInputFragment_postCommentListener");
        group.resubscribeAll(target.postCommentListener);
    }
}
