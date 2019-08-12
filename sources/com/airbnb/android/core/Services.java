package com.airbnb.android.core;

import android.app.Service;

public final class Services extends ClassRegistry {
    private Services() {
    }

    private static Class<? extends Service> maybeLoadServiceClass(String className) {
        return maybeLoadClass(className);
    }

    public static Class<? extends Service> viewedListingsPersistence() {
        return maybeLoadServiceClass("com.airbnb.android.lib.services.ViewedListingsPersistenceService");
    }

    public static Class<? extends Service> tripsReservationsSync() {
        return maybeLoadServiceClass("com.airbnb.android.lib.services.TripsReservationsSyncService");
    }

    public static Class<? extends Service> checkInDataSync() {
        return maybeLoadServiceClass("com.airbnb.android.checkin.data.CheckInDataSyncService");
    }

    public static Class<? extends Service> atlantis() {
        return maybeLoadServiceClass("com.airbnb.android.atlantis.AtlantisService");
    }

    public static Class<? extends Service> push() {
        return maybeLoadServiceClass("com.airbnb.android.lib.services.PushIntentService");
    }
}
