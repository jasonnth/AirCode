package com.airbnb.android.contentframework.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ArticleRoutingActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public ArticleRoutingActivity_ObservableResubscriber(ArticleRoutingActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.requestListener, "ArticleRoutingActivity_requestListener");
        group.resubscribeAll(target.requestListener);
    }
}
