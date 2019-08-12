package com.airbnb.android.booking.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class BookingSummaryFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public BookingSummaryFragment_ObservableResubscriber(BookingSummaryFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.fetchGovernmentIdResultsListenerForSubmit, "BookingSummaryFragment_fetchGovernmentIdResultsListenerForSubmit");
        group.resubscribeAll(target.fetchGovernmentIdResultsListenerForSubmit);
        setTag((AutoTaggableObserver) target.fetchGovernmentIdResultsListenerForPending, "BookingSummaryFragment_fetchGovernmentIdResultsListenerForPending");
        group.resubscribeAll(target.fetchGovernmentIdResultsListenerForPending);
        setTag((AutoTaggableObserver) target.bookingRequestListener, "BookingSummaryFragment_bookingRequestListener");
        group.resubscribeAll(target.bookingRequestListener);
        setTag((AutoTaggableObserver) target.cancelReservationListener, "BookingSummaryFragment_cancelReservationListener");
        group.resubscribeAll(target.cancelReservationListener);
    }
}
