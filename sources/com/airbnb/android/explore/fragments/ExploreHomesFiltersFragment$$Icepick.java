package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.explore.fragments.ExploreHomesFiltersFragment;
import icepick.Bundler;
import icepick.Injector.Helper;
import icepick.Injector.Object;
import java.util.HashMap;
import java.util.Map;

public class ExploreHomesFiltersFragment$$Icepick<T extends ExploreHomesFiltersFragment> extends Object<T> {
    private static final Map<String, Bundler<?>> BUNDLERS = new HashMap();

    /* renamed from: H */
    private static final Helper f8580H = new Helper("com.airbnb.android.explore.fragments.ExploreHomesFiltersFragment$$Icepick.", BUNDLERS);

    public void restore(T target, Bundle state) {
        if (state != null) {
            target.hasClickedSearchButton = f8580H.getBoolean(state, "hasClickedSearchButton");
            target.searchFilters = (SearchFilters) f8580H.getParcelable(state, "searchFilters");
            target.selectedWheelchairAccessible = f8580H.getBoolean(state, "selectedWheelchairAccessible");
            target.amenitiesExpanded = f8580H.getBoolean(state, "amenitiesExpanded");
            target.facilitiesExpanded = f8580H.getBoolean(state, "facilitiesExpanded");
            target.houseRulesExpanded = f8580H.getBoolean(state, "houseRulesExpanded");
            target.hasToggledBusinessTravelReadyFilter = f8580H.getBoolean(state, "hasToggledBusinessTravelReadyFilter");
            super.restore(target, state);
        }
    }

    public void save(T target, Bundle state) {
        super.save(target, state);
        f8580H.putBoolean(state, "hasClickedSearchButton", target.hasClickedSearchButton);
        f8580H.putParcelable(state, "searchFilters", target.searchFilters);
        f8580H.putBoolean(state, "selectedWheelchairAccessible", target.selectedWheelchairAccessible);
        f8580H.putBoolean(state, "amenitiesExpanded", target.amenitiesExpanded);
        f8580H.putBoolean(state, "facilitiesExpanded", target.facilitiesExpanded);
        f8580H.putBoolean(state, "houseRulesExpanded", target.houseRulesExpanded);
        f8580H.putBoolean(state, "hasToggledBusinessTravelReadyFilter", target.hasToggledBusinessTravelReadyFilter);
    }
}
