package com.airbnb.android.itinerary.data.models;

import android.os.Parcelable;

public interface BaseReservationObject extends Parcelable {

    public enum ReservationObjectType {
        HOME,
        EXPERIENCE,
        PLACE
    }

    String getReservation();

    ReservationObjectType getReservationObjectType();
}
