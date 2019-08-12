package com.airbnb.android.core.interfaces;

import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.ReservationStatus;

public interface Reservationable extends Parcelable {
    AirDate getCheckinDate();

    AirDate getCheckoutDate();

    long getId();

    ReservationStatus getStatus();
}
