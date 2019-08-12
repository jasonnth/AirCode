package com.airbnb.android.lib.fragments.reviews;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ReservationReviewsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ReservationReviewsFragment_ObservableResubscriber(ReservationReviewsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reviewsRequestListener, "ReservationReviewsFragment_reviewsRequestListener");
        group.resubscribeAll(target.reviewsRequestListener);
        setTag((AutoTaggableObserver) target.translationListener, "ReservationReviewsFragment_translationListener");
        group.resubscribeAll(target.translationListener);
    }
}
