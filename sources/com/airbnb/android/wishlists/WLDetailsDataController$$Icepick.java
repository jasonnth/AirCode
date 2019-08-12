package com.airbnb.android.wishlists;

import android.os.Bundle;
import com.airbnb.android.wishlists.WLDetailsDataController;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class WLDetailsDataController$$Icepick<T extends WLDetailsDataController> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f2675H = new Helper("com.airbnb.android.wishlists.WLDetailsDataController$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.availableListings = f2675H.getParcelableArrayList(state, "availableListings");
            target.unavailableListings = f2675H.getParcelableArrayList(state, "unavailableListings");
            target.availableTripImmersions = f2675H.getParcelableArrayList(state, "availableTripImmersions");
            target.availableTripExperiences = f2675H.getParcelableArrayList(state, "availableTripExperiences");
            target.unavailableTrips = f2675H.getParcelableArrayList(state, "unavailableTrips");
            target.places = f2675H.getParcelableArrayList(state, "places");
            target.articles = f2675H.getParcelableArrayList(state, "articles");
            target.placeActivities = f2675H.getParcelableArrayList(state, "placeActivities");
            target.hasStartedLoad = f2675H.getBoolean(state, "hasStartedLoad");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f2675H.putParcelableArrayList(state, "availableListings", target.availableListings);
        f2675H.putParcelableArrayList(state, "unavailableListings", target.unavailableListings);
        f2675H.putParcelableArrayList(state, "availableTripImmersions", target.availableTripImmersions);
        f2675H.putParcelableArrayList(state, "availableTripExperiences", target.availableTripExperiences);
        f2675H.putParcelableArrayList(state, "unavailableTrips", target.unavailableTrips);
        f2675H.putParcelableArrayList(state, "places", target.places);
        f2675H.putParcelableArrayList(state, "articles", target.articles);
        f2675H.putParcelableArrayList(state, "placeActivities", target.placeActivities);
        f2675H.putBoolean(state, "hasStartedLoad", target.hasStartedLoad);
    }
}
