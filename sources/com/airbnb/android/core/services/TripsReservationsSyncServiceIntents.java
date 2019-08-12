package com.airbnb.android.core.services;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Services;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class TripsReservationsSyncServiceIntents {
    public static final String KEY_SYNC_OPTIONS = "bundle_key_sync_options";
    public static final int SYNC_ALL = 1;
    public static final int SYNC_TRIPS_ONLY = 2;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SyncOptions {
    }

    private TripsReservationsSyncServiceIntents() {
    }

    public static Intent intent(Context context) {
        return new Intent(context, Services.tripsReservationsSync());
    }

    public static Intent intentForTripsOnlySync(Context context) {
        return new Intent(context, Services.tripsReservationsSync()).putExtra(KEY_SYNC_OPTIONS, 2);
    }
}
