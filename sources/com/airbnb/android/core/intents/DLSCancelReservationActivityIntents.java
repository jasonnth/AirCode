package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.deeplinks.WebLink;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.utils.Check;

public final class DLSCancelReservationActivityIntents {
    public static final String ARG_CONFIRMATION_CODE = "confirmation_code";
    public static final String ARG_IS_RETRACTING = "is_retracting";
    public static final String ARG_RESERVATION = "reservation";

    private DLSCancelReservationActivityIntents() {
    }

    public static Intent intent(Context context, Reservation reservation) {
        return new Intent(context, Activities.dlsCancelReservation()).putExtra("reservation", reservation);
    }

    public static Intent intent(Context context, String confirmationCode) {
        Check.notEmpty(confirmationCode);
        return new Intent(context, Activities.dlsCancelReservation()).putExtra("confirmation_code", confirmationCode);
    }

    @WebLink({"alterations/{deeplink_code}"})
    public static Intent intentForDeeplink(Context context, Bundle bundle) {
        return intent(context, bundle.getString("deeplink_code"));
    }
}
