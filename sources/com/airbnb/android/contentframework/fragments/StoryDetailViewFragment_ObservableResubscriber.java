package com.airbnb.android.contentframework.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class StoryDetailViewFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public StoryDetailViewFragment_ObservableResubscriber(StoryDetailViewFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.deleteArticleListener, "StoryDetailViewFragment_deleteArticleListener");
        group.resubscribeAll(target.deleteArticleListener);
        setTag((AutoTaggableObserver) target.getArticleListener, "StoryDetailViewFragment_getArticleListener");
        group.resubscribeAll(target.getArticleListener);
        setTag((AutoTaggableObserver) target.likeListener, "StoryDetailViewFragment_likeListener");
        group.resubscribeAll(target.likeListener);
        setTag((AutoTaggableObserver) target.unlikeListener, "StoryDetailViewFragment_unlikeListener");
        group.resubscribeAll(target.unlikeListener);
        setTag((AutoTaggableObserver) target.getArticleCommentListener, "StoryDetailViewFragment_getArticleCommentListener");
        group.resubscribeAll(target.getArticleCommentListener);
    }
}
