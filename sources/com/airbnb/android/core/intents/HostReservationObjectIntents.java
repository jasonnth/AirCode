package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.utils.Check;

public class HostReservationObjectIntents {

    public interface ArgumentConstants {
        public static final String ARG_CONFIRMATION_CODE = "confirmation_code";
        public static final String ARG_LAUNCH_SOURCE = "launch_source";
        public static final String ARG_RESERVATION = "reservation";
        public static final String ARG_RESERVATION_ID = "reservation_id";
        public static final String ARG_THREAD_ID = "thread_id";
    }

    public static Intent intentForConfirmationCode(Context context, String code, ROLaunchSource source) {
        Check.notNull(code);
        return new Intent(context, Activities.hostReservationObject()).putExtra("launch_source", source).putExtra("confirmation_code", code);
    }

    public static Intent intentForReservation(Context context, Reservation reservation, ROLaunchSource source) {
        Check.notNull(reservation);
        return new Intent(context, Activities.hostReservationObject()).putExtra("reservation", reservation);
    }

    public static Intent intentForReservationId(Context context, long id, ROLaunchSource source) {
        Check.state(id != -1);
        return new Intent(context, Activities.hostReservationObject()).putExtra("launch_source", source).putExtra("reservation_id", id);
    }
}
