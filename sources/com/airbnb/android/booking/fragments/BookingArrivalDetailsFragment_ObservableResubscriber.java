package com.airbnb.android.booking.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class BookingArrivalDetailsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public BookingArrivalDetailsFragment_ObservableResubscriber(BookingArrivalDetailsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.arrivalDetailsUpdateListener, "BookingArrivalDetailsFragment_arrivalDetailsUpdateListener");
        group.resubscribeAll(target.arrivalDetailsUpdateListener);
    }
}
