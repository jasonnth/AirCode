package com.airbnb.android.explore.requests;

import android.location.Location;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.ExploreSeeAllInfo;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.find.ExperienceFilters;
import com.airbnb.android.core.models.find.PlaceFilters;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.responses.ExploreResponse;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.explore.data.ExploreFilters;
import com.airbnb.android.utils.Strap;
import com.airbnb.erf.Experiments;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.TimeZone;
import retrofit2.Query;

public class ExploreRequest extends BaseRequestV2<ExploreResponse> {
    static final String API_FORMAT_CHINA = "for_china_explore_search_native";
    static final String API_FORMAT_DEFAULT = "default";
    static final String API_FORMAT_TRIMMED = "for_explore_search_native";
    static final String ARG_ADULTS = "adults";
    static final String ARG_ALLOW_OVERRIDE = "allow_override";
    static final String ARG_CHECKIN = "checkin";
    static final String ARG_CHECKOUT = "checkout";
    static final String ARG_CHILDREN = "children";
    static final String ARG_EMPTY_STATE_WITH_DESTINATION_SUGGESTIONS = "empty_state_with_destination_suggestions";
    static final String ARG_GPS_LAT = "gps_lat";
    static final String ARG_GPS_LNG = "gps_lng";
    static final String ARG_GUESTS = "guests";
    public static final int ARG_HOMES_PAGE_SIZE_LIST = 8;
    public static final int ARG_HOMES_PAGE_SIZE_MAP = 16;
    static final String ARG_IB_OVERRIDE = "ib";
    static final String ARG_INFANTS = "infants";
    static final String ARG_IS_SEE_ALL = "in_see_all";
    static final String ARG_ITEMS_OFFSET = "items_offset";
    static final String ARG_ITEM_PER_GRID = "items_per_grid";
    static final String ARG_LAST_SEARCH_SESSION_ID = "last_search_session_id";
    static final String ARG_LOCATION = "location";
    static final String ARG_PETS = "pets";
    static final String ARG_PLACE_ID = "place_id";
    static final String ARG_SELECTED_TAB_ID = "selected_tab_id";
    static final String ARG_TIMEZONE = "timezone";
    static final String ARG_TIME_TYPE = "time_type";
    static final String ARG_VERSION = "version";
    static final String CURRENT_API_VERSION = "1.2.1";
    static final long SOFT_TTL = 300000;
    static final String SUPPORTS_FOR_YOU_V3 = "supports_for_you_v3";
    static final long TTL = 1800000;
    private final boolean areTopLevelSearchParametersCleared;
    private final String autocompletePlaceId;
    private final AirDate checkIn;
    private final AirDate checkOut;
    private final QueryStrap exploreParams;
    private final GuestDetails guestData;
    private final boolean inMapMode;
    private final String searchTerm;
    private final ExploreSeeAllInfo seeAllParams;
    private final String selectedTabId;
    private final boolean shouldOverrideInstantBookFilter;
    private final String timeType;
    private final String timeZone;
    private final Location userLocation;

    public static ExploreRequest newInstance(TopLevelSearchParams topLevelSearchParams, ExploreFilters filters, ExploreSeeAllInfo seeAllInfo, String federatedSearchSessionId, String searchSessionId, String selectedTabId2, boolean inMapMode2, Location userLocation2, String searchIntentSource) {
        return new ExploreRequest(topLevelSearchParams, filters.getExperienceSearchFilters(), filters.getHomesSearchFilters(), filters.getPlacesSearchFilters(), seeAllInfo, federatedSearchSessionId, searchSessionId, selectedTabId2, inMapMode2, userLocation2);
    }

    private ExploreRequest(TopLevelSearchParams topLevelSearchParams, ExperienceFilters experienceSearchFilters, SearchFilters homesSearchFilters, PlaceFilters placesSearchFilters, ExploreSeeAllInfo seeAllInfo, String federatedSearchSessionId, String searchSessionId, String selectedTabId2, boolean inMapMode2, Location userLocation2) {
        this.checkIn = topLevelSearchParams.checkInDate();
        this.checkOut = topLevelSearchParams.checkOutDate();
        this.guestData = topLevelSearchParams.guestDetails();
        this.searchTerm = topLevelSearchParams.searchTerm();
        this.timeType = topLevelSearchParams.getTimeTypeValue();
        this.shouldOverrideInstantBookFilter = !homesSearchFilters.hasSetInstantBookOnly();
        this.autocompletePlaceId = topLevelSearchParams.autocompletePlaceId();
        this.areTopLevelSearchParametersCleared = topLevelSearchParams.isCleared();
        this.timeZone = TimeZone.getDefault().getID();
        this.userLocation = userLocation2;
        this.inMapMode = inMapMode2;
        ExploreRequestParamsBuilder withFacets = ExploreRequestParamsBuilder.from(topLevelSearchParams, experienceSearchFilters, homesSearchFilters, placesSearchFilters, federatedSearchSessionId, searchSessionId).withFacets(true);
        String str = ChinaUtils.useHomeCardChina() ? API_FORMAT_CHINA : Experiments.useNewFormat() ? API_FORMAT_TRIMMED : API_FORMAT_DEFAULT;
        ExploreRequestParamsBuilder builder = withFacets.withFormat(str).withPredictiveFilters().tagAsStandardSearch(true);
        if (topLevelSearchParams.hasMapBounds()) {
            builder.withMapBounds(topLevelSearchParams.mapBounds());
        }
        this.exploreParams = builder.build();
        this.seeAllParams = seeAllInfo;
        this.selectedTabId = selectedTabId2;
    }

    public Type successResponseType() {
        return ExploreResponse.class;
    }

    public String getPath() {
        return "explore_tabs";
    }

    public Collection<Query> getQueryParams() {
        String str;
        String str2 = null;
        Strap make = Strap.make();
        String str3 = "checkin";
        if (this.checkIn != null) {
            str = this.checkIn.getIsoDateString();
        } else {
            str = null;
        }
        Strap kv = make.mo11639kv(str3, str);
        String str4 = "checkout";
        if (this.checkOut != null) {
            str2 = this.checkOut.getIsoDateString();
        }
        Strap baseParams = kv.mo11639kv(str4, str2).mo11639kv("location", this.searchTerm).mo11639kv(ARG_TIME_TYPE, this.timeType).mo11639kv(ARG_PLACE_ID, this.autocompletePlaceId).mo11637kv("guests", this.guestData.totalGuestCount()).mo11637kv("adults", this.guestData.adultsCount()).mo11637kv("children", this.guestData.childrenCount()).mo11637kv("infants", this.guestData.infantsCount()).mo11640kv("pets", this.guestData.isBringingPets()).mo11639kv(ARG_TIMEZONE, this.timeZone).mo11639kv("version", CURRENT_API_VERSION).mo11637kv(ARG_ITEMS_OFFSET, 0).mo11640kv(ARG_EMPTY_STATE_WITH_DESTINATION_SUGGESTIONS, true);
        if (this.shouldOverrideInstantBookFilter) {
            baseParams.mo11639kv(ARG_ALLOW_OVERRIDE, ARG_IB_OVERRIDE);
        }
        if (this.userLocation != null) {
            baseParams.mo11635kv(ARG_GPS_LAT, this.userLocation.getLatitude());
            baseParams.mo11635kv(ARG_GPS_LNG, this.userLocation.getLongitude());
        }
        if (this.selectedTabId != null) {
            baseParams.mo11639kv(ARG_SELECTED_TAB_ID, this.selectedTabId);
        }
        if (this.seeAllParams != null) {
            baseParams.mo11640kv(ARG_IS_SEE_ALL, true);
            if (this.seeAllParams.hasSearchSessionId()) {
                baseParams.mo11639kv(ARG_LAST_SEARCH_SESSION_ID, this.seeAllParams.getSearchSessionId());
            }
        }
        baseParams.mo11637kv(ARG_ITEM_PER_GRID, this.inMapMode ? 16 : 8);
        if (FeatureToggles.shouldShowForYouV3()) {
            baseParams.mo11640kv(SUPPORTS_FOR_YOU_V3, true);
        }
        return QueryStrap.make().mix((Map<String, String>) baseParams).mix((Collection<Query>) this.exploreParams);
    }

    public long getCacheTimeoutMs() {
        return TTL;
    }

    public long getCacheOnlyTimeoutMs() {
        return SOFT_TTL;
    }

    public boolean areTopLevelSearchParametersCleared() {
        return this.areTopLevelSearchParametersCleared;
    }
}
