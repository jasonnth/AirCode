package com.airbnb.android.booking.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class BookingReviewFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public BookingReviewFragment_ObservableResubscriber(BookingReviewFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.businessTravelDetailsUpdateListener, "BookingReviewFragment_businessTravelDetailsUpdateListener");
        group.resubscribeAll(target.businessTravelDetailsUpdateListener);
        setTag((AutoTaggableObserver) target.dateUpdateListener, "BookingReviewFragment_dateUpdateListener");
        group.resubscribeAll(target.dateUpdateListener);
    }
}
