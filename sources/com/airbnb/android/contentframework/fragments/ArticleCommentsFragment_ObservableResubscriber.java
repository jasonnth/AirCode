package com.airbnb.android.contentframework.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ArticleCommentsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ArticleCommentsFragment_ObservableResubscriber(ArticleCommentsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.commentsListener, "ArticleCommentsFragment_commentsListener");
        group.resubscribeAll(target.commentsListener);
    }
}
