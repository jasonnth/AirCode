package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.deeplinks.WebLink;

public final class ReservationObjectDeepLinkActivityIntents {
    public static final String EXTRA_CONFIRMATION_CODE = "confirmation_code";
    public static final String EXTRA_RESERVATION_ID = "reservation_id";

    private ReservationObjectDeepLinkActivityIntents() {
    }

    public static Intent forConfirmationCode(Context context, String confirmationCode) {
        return new Intent(context, Activities.reservationObjectDeepLink()).putExtra("confirmation_code", confirmationCode);
    }

    public static Intent forReservationId(Context context, long reservationId) {
        return new Intent(context, Activities.reservationObjectDeepLink()).putExtra("reservation_id", reservationId);
    }

    @WebLink({"reservations/{deeplink_res_id}"})
    public static Intent forReservationIdDeeplink(Context context, Bundle bundle) {
        return forReservationId(context, Long.valueOf(bundle.getLong("deeplink_res_id", 0)).longValue());
    }

    @WebLink({"reservations/{deeplink_confirmation_code}"})
    public static Intent forConfirmationCodeDeeplink(Context context, Bundle bundle) {
        String code = bundle.getString("deeplink_confirmation_code");
        if (code.endsWith(".")) {
            code = code.substring(0, code.length() - 1);
        }
        return forConfirmationCode(context, code);
    }
}
