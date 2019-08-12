package com.airbnb.android.lib.postbooking;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.utils.Strap;
import java.util.Iterator;
import org.json.JSONArray;

public class PostBookLogger extends BaseAnalytics {
    private final PostBookingFlowController controller;
    private final String experienceIds;

    public PostBookLogger(PostBookingFlowController controller2) {
        this.controller = controller2;
        JSONArray ids = new JSONArray();
        if (controller2.getTripSuggestions() != null) {
            Iterator it = controller2.getTripSuggestions().iterator();
            while (it.hasNext()) {
                ids.put(String.valueOf(((TripTemplate) it.next()).getId()));
            }
        }
        this.experienceIds = ids.toString();
    }

    public Strap tripsUpsellParams() {
        return Strap.make().mo11639kv("page", "p5_trips_upsell").mo11639kv("experience_ids", this.experienceIds).mo11638kv("reservation_id", this.controller.getReservation().getId());
    }

    public void trackTapOnAnUpsell(TripTemplate tripTemplate, int index) {
        track(Strap.make().mo11639kv(BaseAnalytics.TARGET, "experience_card").mo11639kv(BaseAnalytics.OPERATION, "tap").mo11638kv("experience_id", tripTemplate.getId()).mo11637kv("card_index", index));
    }

    public void trackUpsellScroll(boolean userSwipedLeft) {
        track(Strap.make().mo11639kv(BaseAnalytics.TARGET, "experience_carousel").mo11639kv(BaseAnalytics.OPERATION, userSwipedLeft ? BaseAnalytics.SWIPE_LEFT : BaseAnalytics.SWIPE_RIGHT));
    }

    private void track(Strap strap) {
        AirbnbEventLogger.track("p5_trips_upsell", strap.mix(tripsUpsellParams()));
    }
}
