package com.airbnb.android.lib.host;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.lib.activities.HostReservationObjectActivity;

public class HostReservationIntentFactory {
    public static Intent forConfirmationCode(Context context, String confirmationCode, ROLaunchSource source) {
        return HostReservationObjectActivity.intentForConfirmationCode(context, confirmationCode, source);
    }

    public static Intent forThreadId(Context context, long threadId, ROLaunchSource source) {
        return HostReservationObjectActivity.intentForThread(context, threadId, source);
    }

    @Deprecated
    public static Intent forReservationId(Context context, long reservationId, ROLaunchSource source) {
        return HostReservationObjectActivity.intentForReservationId(context, reservationId, source);
    }
}
