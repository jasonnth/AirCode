package com.airbnb.android.lib.reviews.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class FeedbackSummaryFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public FeedbackSummaryFragment_ObservableResubscriber(FeedbackSummaryFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.submitReviewListener, "FeedbackSummaryFragment_submitReviewListener");
        group.resubscribeAll(target.submitReviewListener);
    }
}
