package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.Reservation;

public class PostBookingActivityIntents {
    public static final String ARG_RESERVATION = "arg_reservation";

    public static Intent intentForReservation(Context context, Reservation reservation) {
        return new Intent(context, Activities.postBooking()).putExtra(ARG_RESERVATION, reservation);
    }
}
