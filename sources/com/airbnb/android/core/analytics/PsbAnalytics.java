package com.airbnb.android.core.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.utils.Strap;

public class PsbAnalytics {
    private static final String ACTION = "action";
    private static final String EVENT_NAME = "psb";

    private enum Page {
        P4("p4"),
        ManageIdentities("manage_identifications"),
        Createidentification("create_identification");
        
        final String key;

        private Page(String key2) {
            this.key = key2;
        }
    }

    public static void trackCreateIdentificationAction(String action) {
        track(Page.Createidentification, Strap.make().mo11639kv("action", action));
    }

    public static void trackManageIdentitiesAction(String action) {
        track(Page.ManageIdentities, Strap.make().mo11639kv("action", action));
    }

    public static void trackManageIdentitiesToggleSelection(boolean selected) {
        track(Page.ManageIdentities, Strap.make().mo11639kv("action", "identification_selection_toggled").mo11640kv("selected", selected));
    }

    public static void trackP4Action(String action) {
        track(Page.P4, Strap.make().mo11639kv("action", action));
    }

    public static void trackP4Impression(Reservation reservation, int identificationCount) {
        track(Page.P4, Strap.make().mo11639kv("action", "p4_impression").mo11637kv("identification_count", identificationCount).mo11637kv("guest_count", reservation.getGuestCount()).mo11638kv("reservation_id", reservation.getId()));
    }

    public static void trackManageIdentitiesDoneClick(int selectedIdentities) {
        track(Page.ManageIdentities, Strap.make().mo11639kv("action", "done_click").mo11637kv("selected_identities", selectedIdentities));
    }

    private static void track(Page page, Strap strap) {
        AirbnbEventLogger.track(EVENT_NAME, Strap.make().mo11639kv("page", page.key).mix(strap));
    }
}
