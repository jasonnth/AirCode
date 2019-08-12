package com.airbnb.android.core.responses;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.models.SavedSearchMetadata;
import com.airbnb.android.utils.Strap;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class GetSavedSearchesResponse extends BaseResponse {
    @JsonProperty("metadata")
    public SavedSearchMetadata metadata;
    @JsonProperty("saved_searches")
    public ArrayList<SavedSearch> searches;

    public void dedupeSearches() {
        LinkedHashMap<String, SavedSearch> dedupedSearches = new LinkedHashMap<>();
        Iterator it = this.searches.iterator();
        while (it.hasNext()) {
            SavedSearch savedSearch = (SavedSearch) it.next();
            String location = savedSearch.getSearchParams().getLocation();
            if (TextUtils.isEmpty(location)) {
                dedupedSearches.put(savedSearch.getSavedSearchId(), savedSearch);
            } else if (!dedupedSearches.containsKey(location)) {
                dedupedSearches.put(location, savedSearch);
            } else if (!((SavedSearch) dedupedSearches.get(location)).hasDates() && savedSearch.hasDates()) {
                dedupedSearches.put(location, savedSearch);
            }
        }
        this.searches.clear();
        this.searches.addAll(dedupedSearches.values());
    }

    public void removeSearchesWithEmptyLocation() {
        Iterator<SavedSearch> it = this.searches.iterator();
        while (it.hasNext()) {
            SavedSearch ss = (SavedSearch) it.next();
            if (TextUtils.isEmpty(ss.getSearchParams().getLocation())) {
                it.remove();
                AirbnbEventLogger.track(AirbnbEventLogger.EVENT_ENGINEERING_LOG_2, Strap.make().mo11639kv("empty_search", ss.getSavedSearchId()));
            }
        }
    }

    public String getUserMarket() {
        if (this.metadata != null) {
            return this.metadata.getMarket();
        }
        return null;
    }
}
