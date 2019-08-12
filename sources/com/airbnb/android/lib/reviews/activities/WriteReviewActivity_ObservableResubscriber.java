package com.airbnb.android.lib.reviews.activities;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class WriteReviewActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public WriteReviewActivity_ObservableResubscriber(WriteReviewActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reviewListener, "WriteReviewActivity_reviewListener");
        group.resubscribeAll(target.reviewListener);
    }
}
