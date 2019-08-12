package com.airbnb.android.explore.requests;

import android.location.Location;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.ExploreSeeAllInfo;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.PaginationMetadata;
import com.airbnb.android.core.models.find.ExperienceFilters;
import com.airbnb.android.core.models.find.PlaceFilters;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.android.core.responses.ExploreTabResponse;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.explore.data.ExploreFilters;
import com.airbnb.android.utils.Strap;
import com.airbnb.erf.Experiments;
import com.mparticle.commerce.Product;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import java.util.TimeZone;
import retrofit2.Query;

public class ExploreTabRequest extends BaseRequestV2<ExploreTabResponse> {
    private final boolean areTopLevelSearchParametersCleared;
    private final AirDate checkIn;
    private final AirDate checkOut;
    protected final ExploreRequestParamsBuilder exploreParams;
    private final GuestDetails guestsData;
    private boolean inMapMode;
    private final int offset;
    private final PaginationMetadata paginationMetadata;
    private final String searchTerm;
    private final ExploreSeeAllInfo seeAllParams;
    private final boolean shouldOverrideInstantBookFilter;
    private final String tabId;
    private final String timeType;
    private final String timeZone;
    private final Location userLocation;

    public static ExploreTabRequest forTab(String tabId2, TopLevelSearchParams data, PaginationMetadata paginationMetadata2, ExploreFilters filters, ExploreSeeAllInfo seeAllInfo, String federatedSearchSessionId, String searchSessionId, boolean inMapMode2, Location userLocation2) {
        return new ExploreTabRequest(tabId2, data, paginationMetadata2, filters.getExperienceSearchFilters(), filters.getHomesSearchFilters(), filters.getPlacesSearchFilters(), seeAllInfo, federatedSearchSessionId, searchSessionId, inMapMode2, userLocation2);
    }

    protected ExploreTabRequest(String tabId2, TopLevelSearchParams data, PaginationMetadata paginationMetadata2, ExperienceFilters experienceSearchFilters, SearchFilters homesSearchFilters, PlaceFilters placesSearchFilters, ExploreSeeAllInfo seeAllInfo, String federatedSearchSessionId, String searchSessionId, boolean inMapMode2, Location userLocation2) {
        this.tabId = tabId2;
        this.offset = paginationMetadata2 != null ? paginationMetadata2.getSectionOffset() : 0;
        this.paginationMetadata = paginationMetadata2;
        this.checkIn = data.checkInDate();
        this.checkOut = data.checkOutDate();
        this.searchTerm = data.searchTerm();
        this.guestsData = data.guestDetails();
        this.timeType = data.getTimeTypeValue();
        this.inMapMode = inMapMode2;
        this.areTopLevelSearchParametersCleared = data.isCleared();
        this.seeAllParams = seeAllInfo;
        this.shouldOverrideInstantBookFilter = !homesSearchFilters.hasSetInstantBookOnly();
        this.timeZone = TimeZone.getDefault().getID();
        this.userLocation = userLocation2;
        ExploreRequestParamsBuilder withFacets = ExploreRequestParamsBuilder.from(data, experienceSearchFilters, homesSearchFilters, placesSearchFilters, federatedSearchSessionId, searchSessionId).withFacets(true);
        String str = ChinaUtils.useHomeCardChina() ? "for_china_explore_search_native" : Experiments.useNewFormat() ? "for_explore_search_native" : "default";
        this.exploreParams = withFacets.withFormat(str).withPredictiveFilters().tagAsStandardSearch(true);
        if (data.hasMapBounds()) {
            this.exploreParams.withMapBounds(data.mapBounds());
        }
    }

    public String getTabId() {
        return this.tabId;
    }

    public Type successResponseType() {
        return ExploreTabResponse.class;
    }

    public String getPath() {
        return "explore_tabs";
    }

    public Collection<Query> getQueryParams() {
        String str;
        String str2 = null;
        Strap make = Strap.make();
        String str3 = UpdateReviewRequest.KEY_CHECKIN;
        if (this.checkIn != null) {
            str = this.checkIn.getIsoDateString();
        } else {
            str = null;
        }
        Strap kv = make.mo11639kv(str3, str);
        String str4 = Product.CHECKOUT;
        if (this.checkOut != null) {
            str2 = this.checkOut.getIsoDateString();
        }
        Strap baseParams = kv.mo11639kv(str4, str2).mo11639kv("location", this.searchTerm).mo11639kv("time_type", this.timeType).mo11637kv(FindTweenAnalytics.GUESTS, this.guestsData.totalGuestCount()).mo11637kv(FindTweenAnalytics.ADULTS, this.guestsData.adultsCount()).mo11637kv(FindTweenAnalytics.CHILDREN, this.guestsData.childrenCount()).mo11637kv(FindTweenAnalytics.INFANTS, this.guestsData.infantsCount()).mo11640kv(FindTweenAnalytics.PETS, this.guestsData.isBringingPets()).mo11639kv("timezone", this.timeZone).mo11639kv("version", "1.2.1").mo11640kv("empty_state_with_destination_suggestions", true).mo11637kv("section_offset", this.offset).mo11639kv("tab_id", this.tabId);
        if (this.paginationMetadata != null) {
            baseParams.mo11637kv("items_offset", this.paginationMetadata.getItemsOffset());
            baseParams.mo11639kv("recommendation_item_cursor", this.paginationMetadata.getRecommendationItemCursor());
            if (this.paginationMetadata.hasSearchSessionId()) {
                baseParams.mo11639kv("last_search_session_id", this.paginationMetadata.getSearchSessionId());
            }
        } else {
            baseParams.mo11637kv("items_offset", 0);
        }
        if (this.shouldOverrideInstantBookFilter) {
            baseParams.mo11639kv("allow_override", "ib");
        }
        if (this.seeAllParams != null) {
            baseParams.mo11640kv("in_see_all", true);
            if (this.seeAllParams.hasSearchSessionId()) {
                baseParams.mo11639kv("last_search_session_id", this.seeAllParams.getSearchSessionId());
            }
        }
        if (this.userLocation != null) {
            baseParams.mo11635kv("gps_lat", this.userLocation.getLatitude());
            baseParams.mo11635kv("gps_lng", this.userLocation.getLongitude());
        }
        if (FeatureToggles.shouldShowForYouV3()) {
            baseParams.mo11640kv("supports_for_you_v3", true);
        }
        addPageSizeParams(baseParams);
        return QueryStrap.make().mix((Map<String, String>) baseParams).mix((Collection<Query>) this.exploreParams.build());
    }

    public int getOffset() {
        return this.offset;
    }

    private void addPageSizeParams(Strap baseParams) {
        if (Tab.HOME.isSameAs(this.tabId)) {
            baseParams.mo11637kv("items_per_grid", this.inMapMode ? 16 : 8);
        }
    }

    public long getCacheTimeoutMs() {
        return 1800000;
    }

    public long getCacheOnlyTimeoutMs() {
        return 300000;
    }

    public boolean areTopLevelSearchParametersCleared() {
        return this.areTopLevelSearchParametersCleared;
    }
}
