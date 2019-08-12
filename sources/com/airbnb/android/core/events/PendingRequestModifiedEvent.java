package com.airbnb.android.core.events;

import com.airbnb.android.core.models.Reservation;

public class PendingRequestModifiedEvent {
    private final Reservation mReservation;

    public PendingRequestModifiedEvent(Reservation reservation) {
        this.mReservation = reservation;
    }

    public Reservation getReservation() {
        return this.mReservation;
    }
}
