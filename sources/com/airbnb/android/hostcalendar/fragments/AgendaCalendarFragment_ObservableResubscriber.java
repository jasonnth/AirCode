package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AgendaCalendarFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public AgendaCalendarFragment_ObservableResubscriber(AgendaCalendarFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reservationThumbnailListener, "AgendaCalendarFragment_reservationThumbnailListener");
        group.resubscribeAll(target.reservationThumbnailListener);
        setTag((AutoTaggableObserver) target.initialAgendaReservationListener, "AgendaCalendarFragment_initialAgendaReservationListener");
        group.resubscribeAll(target.initialAgendaReservationListener);
        setTag((AutoTaggableObserver) target.listingLoaderListener, "AgendaCalendarFragment_listingLoaderListener");
        group.resubscribeAll(target.listingLoaderListener);
        setTag((AutoTaggableObserver) target.upcomingReservationsListener, "AgendaCalendarFragment_upcomingReservationsListener");
        group.resubscribeAll(target.upcomingReservationsListener);
    }
}
