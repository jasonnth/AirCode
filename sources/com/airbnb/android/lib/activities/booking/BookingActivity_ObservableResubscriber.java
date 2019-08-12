package com.airbnb.android.lib.activities.booking;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class BookingActivity_ObservableResubscriber extends BaseObservableResubscriber {
    public BookingActivity_ObservableResubscriber(BookingActivity target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingListener, "BookingActivity_listingListener");
        group.resubscribeAll(target.listingListener);
        setTag((AutoTaggableObserver) target.createReservationListener, "BookingActivity_createReservationListener");
        group.resubscribeAll(target.createReservationListener);
        setTag((AutoTaggableObserver) target.updateReservationListener, "BookingActivity_updateReservationListener");
        group.resubscribeAll(target.updateReservationListener);
        setTag((AutoTaggableObserver) target.inquiryRequestListener, "BookingActivity_inquiryRequestListener");
        group.resubscribeAll(target.inquiryRequestListener);
    }
}
