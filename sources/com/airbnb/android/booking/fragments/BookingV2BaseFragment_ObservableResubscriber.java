package com.airbnb.android.booking.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class BookingV2BaseFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public BookingV2BaseFragment_ObservableResubscriber(BookingV2BaseFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.createReservationListener, "BookingV2BaseFragment_createReservationListener");
        group.resubscribeAll(target.createReservationListener);
        setTag((AutoTaggableObserver) target.guestDetailsUpdateListener, "BookingV2BaseFragment_guestDetailsUpdateListener");
        group.resubscribeAll(target.guestDetailsUpdateListener);
        setTag((AutoTaggableObserver) target.pricingQuotesRequestListener, "BookingV2BaseFragment_pricingQuotesRequestListener");
        group.resubscribeAll(target.pricingQuotesRequestListener);
    }
}
