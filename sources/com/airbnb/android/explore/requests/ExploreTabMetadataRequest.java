package com.airbnb.android.explore.requests;

import com.airbnb.android.core.models.ExploreSeeAllInfo;
import com.airbnb.android.core.models.find.ExperienceFilters;
import com.airbnb.android.core.models.find.PlaceFilters;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.core.models.find.TopLevelSearchParams;

public class ExploreTabMetadataRequest extends ExploreTabRequest {
    public ExploreTabMetadataRequest(String tabId, TopLevelSearchParams data, ExperienceFilters experienceSearchFilters, SearchFilters homesSearchFilters, PlaceFilters placesSearchFilters, ExploreSeeAllInfo seeAllInfo, String federatedSearchSessionId, String searchSessionId, String searchIntentSource) {
        super(tabId, data, null, experienceSearchFilters, homesSearchFilters, placesSearchFilters, seeAllInfo, federatedSearchSessionId, searchSessionId, false, null);
        this.exploreParams.metaDataOnly().tagAsStandardSearch(false);
        setPrefetch(true);
    }
}
