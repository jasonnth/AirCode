package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.ReservationStatus;

public final class GuestRecoveryActivityIntents {
    public static final String EXTRA_CONFIRMATION_CODE = "confirmation_code";
    public static final String EXTRA_RESERVATION_ID = "reservation_id";
    public static final String EXTRA_RESERVATION_STATUS = "reservation_status";

    private GuestRecoveryActivityIntents() {
    }

    public static Intent intentForReservationId(Context context, long reservationId, ReservationStatus reservationStatus) {
        return new Intent(context, Activities.guestRecovery()).putExtra("reservation_id", reservationId).putExtra(EXTRA_RESERVATION_STATUS, reservationStatus);
    }

    public static Intent intentForConfirmationCode(Context context, String confirmationCode, ReservationStatus reservationStatus) {
        return new Intent(context, Activities.guestRecovery()).putExtra("confirmation_code", confirmationCode).putExtra(EXTRA_RESERVATION_STATUS, reservationStatus);
    }
}
